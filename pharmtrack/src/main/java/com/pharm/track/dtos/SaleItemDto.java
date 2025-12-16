package com.pharm.track.dtos;

import lombok.Data;

@Data
public class SaleItemDto {
    private Long medicineId;
    private Integer quantity;
    private Integer unitprice; // 없으면 Medicine.price 사용
}
