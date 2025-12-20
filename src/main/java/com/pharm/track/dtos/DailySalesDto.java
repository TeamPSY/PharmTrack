package com.pharm.track.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DailySalesDto {
    private String date;        // 2025-01-01
    private int totalAmount;    // 150000
}
