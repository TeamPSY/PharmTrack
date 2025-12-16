package com.pharm.track.controller;

import com.pharm.track.model.Medicine;
import com.pharm.track.service.MedicineService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final MedicineService medicineService;

    // 재고 전체 조회
    @GetMapping
    public List<Medicine> getInventoryList() {
        return medicineService.getAll();
    }

    // 수량 직접 수정
    @PutMapping("/{id}")
    public ResponseEntity<String> updateStock(
            @PathVariable Long id,
            @RequestBody Medicine updateData
    ) {
        Medicine medicine = medicineService.getById(id);
        medicine.setStock(updateData.getStock());
        medicineService.update(medicine);
        return ResponseEntity.ok("재고 수정 완료");
    }

    // 재고 +1
    @PutMapping("/{id}/plus")
    public ResponseEntity<String> increaseStock(@PathVariable Long id) {
        Medicine medicine = medicineService.getById(id);
        medicine.setStock(medicine.getStock() + 1);
        medicineService.update(medicine);
        return ResponseEntity.ok("입고(+1) 완료");
    }

    // 재고 -1
    @PutMapping("/{id}/minus")
    public ResponseEntity<String> decreaseStock(@PathVariable Long id) {
        Medicine medicine = medicineService.getById(id);
        if (medicine.getStock() > 0) {
            medicine.setStock(medicine.getStock() - 1);
            medicineService.update(medicine);
            return ResponseEntity.ok("출고(-1) 완료");
        } else {
            return ResponseEntity.badRequest().body("재고가 0입니다.");
        }
    }
}

