
package com.pharm.track.service;

import com.pharm.track.dtos.CategoryDto;
import com.pharm.track.mapper.CategoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryMapper categoryMapper;

    public List<CategoryDto> getAllCategories() {
        return categoryMapper.findAll();
    }
}
