package com.pharm.track.service;

import com.pharm.track.dtos.MedicineLotDto;
import com.pharm.track.mapper.MedicineLotMapper;
import com.pharm.track.model.MedicineLot;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LotService {

    private final MedicineLotMapper lotMapper;

    // 약품별 LOT 조회
    public List<MedicineLot> getLots(Long medicineId) {
        return lotMapper.findLotsByMedicine(medicineId);
    }

    // LOT 등록 (DTO → Entity 변환)
    @Transactional
    public void addLot(MedicineLotDto dto) {
        MedicineLot lot = new MedicineLot();

        lot.setMedicineId(dto.getMedicineId());
        lot.setLotNo(dto.getLotNo());                 // ✅ 필드명 수정
        lot.setExpiryDate(dto.getExpiryDate());       // ✅ 타입/이름 일치
        lot.setQuantity(dto.getQuantity());

        lotMapper.insertLot(lot);
    }

    // FIFO 재고 차감
    @Transactional
    public void decreaseStock(Long medicineId, int amount) {
        List<MedicineLot> lots = lotMapper.findLotsForFifo(medicineId);

        for (MedicineLot lot : lots) {
            if (amount <= 0) break;

            int remain = lot.getQuantity() - amount;

            if (remain >= 0) {
                lot.setQuantity(remain);
                amount = 0;
            } else {
                amount -= lot.getQuantity();
                lot.setQuantity(0);
            }

            lotMapper.updateLotQuantity(lot);
        }
    }
}
