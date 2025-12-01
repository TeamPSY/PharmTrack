package com.pharm.track.controller;

import com.pharm.track.model.Medicine;
import com.pharm.track.service.MedicineService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medicines")
@RequiredArgsConstructor
public class MedicineController {

    private final MedicineService medicineService;

    @GetMapping
    public List<Medicine> getAll() {
        return medicineService.getAll();
    }

    @GetMapping("/{id}")
    public Medicine getById(@PathVariable Long id) {
        return medicineService.getById(id);
    }

    @PostMapping
    public void create(@RequestBody Medicine medicine) {
        medicineService.create(medicine);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody Medicine medicine) {
        medicine.setMedicineId(id);
        medicineService.update(medicine);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        medicineService.delete(id);
    }
}
