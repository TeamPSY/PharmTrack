package com.pharm.track.mapper;

import com.pharm.track.model.Medicine;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MedicineMapper {
    List<Medicine> findAll();
    Medicine findById(Long id);
    void insert(Medicine medicine);
    void update(Medicine medicine);
    void delete(Long id);
}
