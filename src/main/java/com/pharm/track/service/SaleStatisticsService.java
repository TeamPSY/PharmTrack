package com.pharm.track.service;

import com.pharm.track.dtos.DailySalesDto;
import com.pharm.track.dtos.MedicineSalesDto;
import com.pharm.track.mapper.SaleStatisticsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SaleStatisticsService {

    private final SaleStatisticsMapper saleStatisticsMapper;

    // ✅ 일별 매출 통계 (실제 DB 데이터)
    public List<DailySalesDto> getDailySales(String startDate, String endDate) {
        return saleStatisticsMapper.findDailySales(startDate, endDate);
    }

    // ✅ 약품별 판매 통계 (실제 DB 데이터)
    public List<MedicineSalesDto> getSalesByMedicine(String startDate, String endDate) {
        return saleStatisticsMapper.findSalesByMedicine(startDate, endDate);
    }
}
