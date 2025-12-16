package com.pharm.track.service;

import com.pharm.track.dtos.DailySalesDto;
import com.pharm.track.dtos.MedicineSalesDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SaleStatisticsService {

    // 일별 매출 통계
    public List<DailySalesDto> getDailySales(String startDate, String endDate) {
        // TODO: 추후 Mapper 연동
        return new ArrayList<>();
    }
    
    // 약품별 판매 통계
    public List<MedicineSalesDto> getSalesByMedicine(String startDate, String endDate) {
        // TODO: 추후 Mapper 연동
        return new ArrayList<>();
    }
}
