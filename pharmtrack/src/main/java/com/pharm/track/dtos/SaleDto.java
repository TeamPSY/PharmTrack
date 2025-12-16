package com.pharm.track.dtos;

import java.util.List;
import lombok.Data;

@Data
public class SaleDto {
    private Long userId;
    private List<SaleItemDto> items;
}
