package com.pharm.track.mapper;

import com.pharm.track.dtos.CategoryDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CategoryMapper {
    List<CategoryDto> findAll();
}
