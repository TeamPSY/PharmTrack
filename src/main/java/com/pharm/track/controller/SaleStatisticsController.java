package com.pharm.track.controller;

import com.pharm.track.dtos.DailySalesDto;
import com.pharm.track.dtos.MedicineSalesDto;
import com.pharm.track.service.SaleStatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sales/statistics")
@RequiredArgsConstructor
public class SaleStatisticsController {

    private final SaleStatisticsService saleStatisticsService;

    // ✅ 일별 매출 통계
    @GetMapping("/daily")
    public List<DailySalesDto> getDailySales(
            @RequestParam String startDate,
            @RequestParam String endDate
    ) {
        return saleStatisticsService.getDailySales(startDate, endDate);
    }

    // ✅ 약품별 판매 통계
    @GetMapping("/by-medicine")
    public List<MedicineSalesDto> getSalesByMedicine(
            @RequestParam String startDate,
            @RequestParam String endDate
    ) {
        return saleStatisticsService.getSalesByMedicine(startDate, endDate);
    }
}
