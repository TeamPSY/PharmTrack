package com.pharm.track.controller;

import com.pharm.track.model.Medicine;
import com.pharm.track.service.MedicineService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medicines")
@RequiredArgsConstructor
public class MedicineController {

    private final MedicineService medicineService;

    // 전체 조회
    @GetMapping
    public List<Medicine> getAll() {
        return medicineService.getAll();
    }

    // 단건 조회
    @GetMapping("/{id}")
    public ResponseEntity<Medicine> getById(@PathVariable Long id) {
        Medicine medicine = medicineService.getById(id);
        if (medicine == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(medicine);
    }

    // 등록
    @PostMapping
    public ResponseEntity<String> create(@RequestBody Medicine medicine) {
        medicineService.create(medicine);
        return ResponseEntity.ok("등록 완료");
    }

    // 수정
    @PutMapping("/{id}")
    public ResponseEntity<String> update(
            @PathVariable Long id,
            @RequestBody Medicine medicine
    ) {
        medicine.setMedicineId(id);
        medicineService.update(medicine);
        return ResponseEntity.ok("수정 완료");
    }

    // 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        medicineService.delete(id);
        return ResponseEntity.ok("삭제 완료");
    }
}
