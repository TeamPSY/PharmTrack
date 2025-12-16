package com.pharm.track.model;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
public class MedicineLot {

    private Long lotId;
    private Long medicineId;

    private String lotNo;           // ✅ XML과 맞춤
    private LocalDate expiryDate;   // ✅ XML과 맞춤

    private int quantity;
    private String createdAt;
}
