package com.pharm.track.model;

import lombok.Data;
import java.time.LocalDateTime;
import java.time.LocalDate;

@Data
public class Medicine {
    private Long medicineId;
    private Long categoryId;
    private String name;
    private String manufacturer;
    private Integer price;
    private Integer stock;
    private String barcode;
    private LocalDate expirationDate;
    private String status;
    private LocalDateTime createdAt;
}
