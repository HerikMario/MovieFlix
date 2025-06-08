package com.movieflix.controller;

import com.movieflix.controller.request.CategoryRequest;
import com.movieflix.controller.response.CategoryResponse;
import com.movieflix.entity.Category;
import com.movieflix.mapper.CategoryMapper;
import com.movieflix.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/movieflix/category")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping()
    public CategoryResponse saveCategory(@RequestBody CategoryRequest category) {
        Category categorySaved = categoryService.saveCategory(CategoryMapper.toCategory(category));
        return CategoryMapper.toCategoryResponse(categorySaved);
    }

    @GetMapping()
    public List<CategoryResponse> getAllCategories() {
        List<Category> categories = categoryService.findAll();
        return categories.stream()
                .map(CategoryMapper::toCategoryResponse)
                .toList();
    }

    @GetMapping("/{id}")
    public CategoryResponse getByCategoryId(@PathVariable Long id) {
        Optional<Category> optCategory = categoryService.findById(id);
        if(optCategory.isPresent()) {
            return CategoryMapper.toCategoryResponse(optCategory.get());
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteByCategoryId(@PathVariable Long id) {
        categoryService.deleteByCategoryId(id);
    }

}
