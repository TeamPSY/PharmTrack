package com.pharm.track.service;

import com.pharm.track.model.Medicine;
import com.pharm.track.mapper.MedicineMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MedicineService {
    private final MedicineMapper medicineMapper;

    public List<Medicine> getAll() {
        return medicineMapper.findAll();
    }

    public Medicine getById(Long id) {
        return medicineMapper.findById(id);
    }

    public void create(Medicine medicine) {
        medicineMapper.insert(medicine);
    }

    public void update(Medicine medicine) {
        medicineMapper.update(medicine);
    }

    public void delete(Long id) {
        medicineMapper.delete(id);
    }
}
