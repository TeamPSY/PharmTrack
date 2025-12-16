package com.pharm.track.controller;

import com.pharm.track.model.InventoryHistory;
import com.pharm.track.service.InventoryHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory-history")
@RequiredArgsConstructor
public class InventoryHistoryController {

    private final InventoryHistoryService historyService;

    /** 약품별 이력 조회 */
    @GetMapping("/medicine/{medicineId}")
    public ResponseEntity<List<InventoryHistory>> getByMedicine(
            @PathVariable Long medicineId) {

        return ResponseEntity.ok(historyService.getByMedicine(medicineId));
    }
    
    /** 전체 재고 변경 이력 조회 */
    @GetMapping
    public ResponseEntity<List<InventoryHistory>> getAllHistory() {
        return ResponseEntity.ok(historyService.getAll());
    }

}
