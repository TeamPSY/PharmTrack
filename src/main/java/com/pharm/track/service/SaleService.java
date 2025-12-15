package com.pharm.track.service;

import com.pharm.track.dtos.*;
import com.pharm.track.mapper.InventoryHistoryMapper;
import com.pharm.track.mapper.MedicineMapper;
import com.pharm.track.mapper.SaleMapper;
import com.pharm.track.model.InventoryHistory;
import com.pharm.track.model.Medicine;
import com.pharm.track.model.Sale;
import com.pharm.track.model.SaleItem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SaleService {

    private final SaleMapper saleMapper;
    private final MedicineMapper medicineMapper;
    private final InventoryHistoryMapper inventoryHistoryMapper;

    /** 🔹 판매 등록 + 재고 차감 + 이력 저장 */
    @Transactional
    public Long createSale(SaleDto saleDto) {

        if (saleDto.getItems() == null || saleDto.getItems().isEmpty()) {
            throw new IllegalArgumentException("판매 항목(items)이 없습니다.");
        }

        // 1️⃣ 총 금액 계산
        int totalPrice = saleDto.getItems().stream()
                .mapToInt(i -> i.getUnitPrice() * i.getQuantity())
                .sum();

        // 2️⃣ 판매 저장
        Sale sale = new Sale();
        sale.setUserId(saleDto.getUserId());
        sale.setTotalPrice(totalPrice);
        saleMapper.insertSale(sale);

        // 3️⃣ 판매 상세 + 재고 차감 + 이력 저장
        for (SaleItemDto dto : saleDto.getItems()) {

            SaleItem item = new SaleItem();
            item.setSaleId(sale.getSaleId());
            item.setMedicineId(dto.getMedicineId());
            item.setQuantity(dto.getQuantity());
            item.setUnitPrice(dto.getUnitPrice());
            item.setSubtotalPrice(dto.getQuantity() * dto.getUnitPrice());

            saleMapper.insertSaleItem(item);

            // ⭐ 재고 차감 + 이력 기록
            decreaseStockAndSaveHistory(
                    dto.getMedicineId(),
                    dto.getQuantity(),
                    sale.getUserId()
            );
        }

        return sale.getSaleId();
    }

    /** 🔹 재고 차감 + 재고 변경 이력 저장 */
    private void decreaseStockAndSaveHistory(Long medicineId, int qty, Long userId) {

        Medicine medicine = medicineMapper.findById(medicineId);
        if (medicine == null) return;

        int beforeStock = medicine.getStock();
        int afterStock = beforeStock - qty;
        if (afterStock < 0) afterStock = 0;

        // 1️⃣ 재고 업데이트
        medicine.setStock(afterStock);
        medicineMapper.update(medicine);

        // 2️⃣ 재고 변경 이력 저장
        InventoryHistory history = new InventoryHistory();
        history.setMedicineId(medicineId);
        history.setUserId(userId);
        history.setChangeType("SALE");
        history.setAmount(-qty);                // 출고는 음수
        history.setBeforeStock(beforeStock);
        history.setAfterStock(afterStock);
        history.setReason("판매로 인한 출고");

        inventoryHistoryMapper.insertHistory(history);
    }

    /** 🔹 판매 상세 조회 */
    public SaleDetailDto getSaleDetail(Long saleId) {
        SaleDetailDto sale = saleMapper.getSaleDetail(saleId);
        if (sale == null) return null;

        sale.setItems(saleMapper.getSaleItems(saleId));
        return sale;
    }

    /** 🔹 전체 판매 목록 조회 */
    public List<SaleDetailDto> getSaleList() {
        List<SaleDetailDto> list = saleMapper.getSaleList();

        for (SaleDetailDto sale : list) {
            sale.setItems(saleMapper.getSaleItems(sale.getSaleId()));
        }

        return list;
    }
}
