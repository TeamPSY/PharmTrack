package com.pharm.track.mapper;

import com.pharm.track.model.InventoryHistory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface InventoryHistoryMapper {

    // 🔹 재고 이력 저장
    void insertHistory(InventoryHistory history);

    // 🔹 약품별 이력 조회
    List<InventoryHistory> findByMedicineId(Long medicineId);

    // 🔹 전체 이력 조회
    List<InventoryHistory> findAll();
}



