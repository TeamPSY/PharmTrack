package com.pharm.track.dtos;

import lombok.Data;
import java.time.LocalDate;

@Data
public class MedicineDto {
    private Long categoryId;
    private String name;
    private String manufacturer;
    private Integer price;
    private Integer stock;
    private String barcode;
    private LocalDate expirationDate;
}
