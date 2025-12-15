package com.pharm.track.mapper;

import com.pharm.track.model.MedicineLot;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MedicineLotMapper {

    // 약품별 모든 로트 조회
    List<MedicineLot> findLotsByMedicine(Long medicineId);

    // 새로운 로트 등록
    void insertLot(MedicineLot lot);

    // FIFO 순서(유통기한 오름차순) 조회
    List<MedicineLot> findLotsForFifo(Long medicineId);

    // 로트 재고 업데이트
    void updateLotQuantity(MedicineLot lot);
}
