package com.movieflix.controller;

import com.movieflix.controller.request.CategoryRequest;
import com.movieflix.controller.response.CategoryResponse;
import com.movieflix.mapper.CategoryMapper;
import com.movieflix.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movieflix/category")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping()
    public ResponseEntity<CategoryResponse> saveCategory(@RequestBody CategoryRequest category) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(
                        CategoryMapper.toCategoryResponse(categoryService.saveCategory(CategoryMapper.toCategory(category)))
                );
    }

    @GetMapping()
    public ResponseEntity<List<CategoryResponse>> getAllCategories() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(
                        categoryService.findAll()
                                .stream()
                                .map(category -> CategoryMapper.toCategoryResponse(category))
                                .toList()
                );
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> getByCategoryId(@PathVariable Long id) {
        return categoryService.findById(id)
                .map(category -> ResponseEntity.status(HttpStatus.OK)
                                                .body(CategoryMapper.toCategoryResponse(category))
                )
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteByCategoryId(@PathVariable Long id) {
        categoryService.deleteByCategoryId(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
