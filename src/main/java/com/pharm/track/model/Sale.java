package com.pharm.track.model;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Sale {
    private Long saleId;
    private Long userId;
    private Integer totalPrice;
    private LocalDateTime saleTime;
}
