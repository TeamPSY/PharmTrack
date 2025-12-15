package com.pharm.track.service;

import com.pharm.track.mapper.InventoryHistoryMapper;
import com.pharm.track.model.InventoryHistory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryHistoryService {

    private final InventoryHistoryMapper inventoryHistoryMapper;

    /** 약품별 이력 조회 */
    public List<InventoryHistory> getByMedicine(Long medicineId) {
        return inventoryHistoryMapper.findByMedicineId(medicineId);
    }

    /** 전체 이력 조회 */
    public List<InventoryHistory> getAll() {
        return inventoryHistoryMapper.findAll();
    }
}
