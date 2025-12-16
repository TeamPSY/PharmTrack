package com.pharm.track.mapper;

import com.pharm.track.dtos.SaleDetailDto;
import com.pharm.track.dtos.SaleItemResponseDto;
import com.pharm.track.model.Sale;
import com.pharm.track.model.SaleItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SaleMapper {

    void insertSale(Sale sale);

    void insertSaleItem(SaleItem item);

    SaleDetailDto getSaleDetail(Long saleId);

    List<SaleItemResponseDto> getSaleItems(Long saleId);

    List<SaleDetailDto> getSaleList();
}
