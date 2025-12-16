package com.pharm.track.model;

import lombok.Data;

@Data
public class SaleItem {
    private Long saleItemId;
    private Long saleId;
    private Long medicineId;
    private Integer quantity;
    private Integer unitPrice;
    private Integer subtotalPrice;
}
