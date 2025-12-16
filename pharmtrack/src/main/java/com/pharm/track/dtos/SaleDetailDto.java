package com.pharm.track.dtos;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;

@Data
public class SaleDetailDto {
    private Long saleId;
    private Long userId;
    private Integer totalPrice;
    private LocalDateTime saleTime;

    private List<SaleItemResponseDto> items;
}
