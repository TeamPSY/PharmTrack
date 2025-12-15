package com.pharm.track.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MedicineSalesDto {
    private String medicineName;
    private int quantity;
    private int totalAmount;
}
