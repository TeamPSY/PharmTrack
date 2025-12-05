package com.pharm.track.dtos;

import lombok.Data;

@Data
public class SaleItemResponseDto {
    private Long saleItemId;
    private Long medicineId;
    private String medicineName;
    private Integer quantity;
    private Integer unitPrice;
    private Integer subtotalPrice;
}
