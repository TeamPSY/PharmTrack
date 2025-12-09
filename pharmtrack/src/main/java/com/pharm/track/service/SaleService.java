package com.pharm.track.service;

import com.pharm.track.dtos.*;
import com.pharm.track.mapper.SaleMapper;
import com.pharm.track.mapper.MedicineMapper;
import com.pharm.track.model.Medicine;
import com.pharm.track.model.Sale;
import com.pharm.track.model.SaleItem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SaleService {

    private final SaleMapper saleMapper;

    // ⭐ 재고 업데이트를 위해 필요
    private final MedicineMapper medicineMapper;

    /** 🔹판매 등록 + 재고 차감 */
    public Long createSale(SaleDto saleDto) {

        int totalPrice = saleDto.getItems().stream()
                .mapToInt(i -> i.getUnitPrice() * i.getQuantity())
                .sum();

        Sale sale = new Sale();
        sale.setUserId(saleDto.getUserId());
        sale.setTotalPrice(totalPrice);

        // 판매 저장
        saleMapper.insertSale(sale);

        // 판매 상세 항목 저장
        for (SaleItemDto dto : saleDto.getItems()) {
            SaleItem item = new SaleItem();
            item.setSaleId(sale.getSaleId());
            item.setMedicineId(dto.getMedicineId());
            item.setQuantity(dto.getQuantity());
            item.setUnitPrice(dto.getUnitPrice());
            item.setSubtotalPrice(dto.getQuantity() * dto.getUnitPrice());

            saleMapper.insertSaleItem(item);
        }

        // ⭐ 판매 후 재고 차감
        updateInventoryAfterSale(saleDto);

        return sale.getSaleId();
    }

    /** 🔹판매 후 재고 차감 */
    public void updateInventoryAfterSale(SaleDto saleDto) {

        for (SaleItemDto item : saleDto.getItems()) {

            Long medicineId = item.getMedicineId();
            int qty = item.getQuantity();

            // 현재 재고 조회
            Medicine medicine = medicineMapper.findById(medicineId);
            if (medicine == null) continue;

            int newStock = medicine.getStock() - qty;
            if (newStock < 0) newStock = 0;

            // 재고 업데이트
            medicine.setStock(newStock);
            medicineMapper.update(medicine);
        }
    }

    /** 🔹판매 상세 조회 */
    public SaleDetailDto getSaleDetail(Long saleId) {
        SaleDetailDto sale = saleMapper.getSaleDetail(saleId);
        if (sale == null) return null;

        sale.setItems(saleMapper.getSaleItems(saleId));
        return sale;
    }

    /** 🔹전체 판매 목록 조회 */
    public List<SaleDetailDto> getSaleList() {
        List<SaleDetailDto> list = saleMapper.getSaleList();

        for (SaleDetailDto sale : list) {
            sale.setItems(saleMapper.getSaleItems(sale.getSaleId()));
        }

        return list;
    }
}
