package com.pharm.track.dtos;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class InventoryHistoryDto {

    private Long historyId;
    private Long medicineId;
    private Long userId;

    private String changeType;
    private int amount;

    private Integer beforeStock;
    private Integer afterStock;

    private String reason;
    private LocalDateTime createdAt;
}
