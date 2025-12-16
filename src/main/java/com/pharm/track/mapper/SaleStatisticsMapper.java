package com.pharm.track.mapper;

import com.pharm.track.dtos.DailySalesDto;
import com.pharm.track.dtos.MedicineSalesDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SaleStatisticsMapper {

    List<DailySalesDto> findDailySales(
            @Param("startDate") String startDate,
            @Param("endDate") String endDate
    );

    List<MedicineSalesDto> findSalesByMedicine(
            @Param("startDate") String startDate,
            @Param("endDate") String endDate
    );
}
