package com.pharm.track.dtos;

import lombok.Data;
import java.time.LocalDate;

@Data
public class MedicineDto {

    private Long medicineId;        // ⭐ PK
    private Long categoryId;
    private String categoryName;    // ⭐ Join된 카테고리명

    private String name;
    private String manufacturer;
    private Integer price;
    private Integer stock;
    private String barcode;
    private LocalDate expirationDate;

    private String status;          // ⭐ NORMAL / ALERT / EXPIRED
}
