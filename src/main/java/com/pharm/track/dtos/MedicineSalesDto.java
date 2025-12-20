package com.pharm.track.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MedicineSalesDto {
    private String medicineName;
    private int quantity;
    private int totalAmount;
}
