package com.pharm.track.mapper;

import com.pharm.track.model.Medicine;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param; // 💡 이 import가 추가되어야 합니다.
import java.util.List;

@Mapper
public interface MedicineMapper {

    List<Medicine> findAll();

    List<Medicine> findExpiringSoon();

    List<Medicine> findExpired();

    Medicine findById(Long id);

    // 🌟 수정된 부분: @Param 어노테이션을 추가했습니다.
    void updateStatus(@Param("id") Long id, @Param("status") String status);

    void insert(Medicine medicine);

    void update(Medicine medicine);

    void delete(Long id);
}