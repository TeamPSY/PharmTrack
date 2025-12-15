package com.pharm.track.dtos;

import lombok.Data;

@Data
public class SaleItemDto {
    private Long medicineId;
    private Integer quantity;
    private Integer unitPrice; // 없으면 Medicine.price 사용
}
