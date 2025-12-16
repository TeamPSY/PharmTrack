package com.pharm.track.dtos;

import lombok.Data;
import java.time.LocalDate;

@Data
public class MedicineLotDto {
    private Long medicineId;
    private String lotNo;
    private LocalDate expiryDate; // 🔥 String → LocalDate
    private int quantity;
}
