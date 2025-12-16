package com.pharm.track.model;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class InventoryHistory {
    private Long historyId;
    private Long medicineId;
    private Long userId;

    private String changeType;
    private int amount;

    private int beforeStock;
    private int afterStock;

    private String reason;
    private LocalDateTime createdAt;
}

