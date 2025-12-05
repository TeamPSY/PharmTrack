package com.pharm.track.service;

import com.pharm.track.dtos.*;
import com.pharm.track.mapper.SaleMapper;
import com.pharm.track.model.Sale;
import com.pharm.track.model.SaleItem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SaleService {

    private final SaleMapper saleMapper;

    /** 🔹판매 등록 */
    public Long createSale(SaleDto saleDto) {

        int totalPrice = saleDto.getItems().stream()
                .mapToInt(i -> i.getUnitPrice() * i.getQuantity())
                .sum();

        Sale sale = new Sale();
        sale.setUserId(saleDto.getUserId());
        sale.setTotalPrice(totalPrice);

        saleMapper.insertSale(sale);

        for (SaleItemDto dto : saleDto.getItems()) {
            SaleItem item = new SaleItem();
            item.setSaleId(sale.getSaleId());
            item.setMedicineId(dto.getMedicineId());
            item.setQuantity(dto.getQuantity());
            item.setUnitPrice(dto.getUnitPrice());
            item.setSubtotalPrice(dto.getQuantity() * dto.getUnitPrice());

            saleMapper.insertSaleItem(item);
        }

        return sale.getSaleId();
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
