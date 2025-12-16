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

    // ⭐ 유통기한 7일 이하 조회
    public List<Medicine> getExpiringSoon() {
        return medicineMapper.findExpiringSoon();
    }

    // ⭐ 만료된 약 조회
    public List<Medicine> getExpired() {
        return medicineMapper.findExpired();
    }

    // ⭐ 상태 변경 (NORMAL, LOW, ALERT, EXPIRED)
    public void updateStatus(Long id, String status) {
        medicineMapper.updateStatus(id, status);
    }

    // =======================================================
    // 🔔 스케줄러가 호출하는 메서드 (추가 필요) 🔔
    // =======================================================

    /**
     * 유통기한이 7일 이하로 임박한 약의 상태를 ALERT로 업데이트합니다.
     */
    public void updateExpiringStatus() {
        // 1. 유통기한이 7일 이내인 약 목록을 가져옵니다. (MedicineMapper.findExpiringSoon() 사용)
        List<Medicine> expiringMedicines = medicineMapper.findExpiringSoon();

        // 2. 각 약의 상태를 ALERT로 변경합니다.
        for (Medicine medicine : expiringMedicines) {
            // 이미 EXPIRED 상태인 약은 제외 (쿼리에서 이미 처리되었을 수 있지만, 서비스 레이어에서 한 번 더 확인 가능)
            if (!"EXPIRED".equals(medicine.getStatus())) {
                medicineMapper.updateStatus(medicine.getMedicineId(), "ALERT");
            }
        }
    }

    /**
     * 유통기한이 지난 약의 상태를 EXPIRED로 업데이트합니다.
     */
    public void updateExpiredStatus() {
        // 1. 유통기한이 지난(만료된) 약 목록을 가져옵니다. (MedicineMapper.findExpired() 사용)
        List<Medicine> expiredMedicines = medicineMapper.findExpired();

        // 2. 각 약의 상태를 EXPIRED로 변경합니다.
        for (Medicine medicine : expiredMedicines) {
            medicineMapper.updateStatus(medicine.getMedicineId(), "EXPIRED");
        }
    }
}