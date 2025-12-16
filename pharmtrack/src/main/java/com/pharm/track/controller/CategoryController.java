package com.pharm.track.controller;

import com.pharm.track.dtos.CategoryDto;
import com.pharm.track.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public List<CategoryDto> list() {
        return categoryService.getAllCategories();
    }
}
