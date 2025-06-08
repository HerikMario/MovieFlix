package com.movieflix.controller;

import com.movieflix.entity.Category;
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
    public Category saveCategory(@RequestBody Category category) {
        return categoryService.saveCategory(category);
    }

    @GetMapping()
    public List<Category> getAllCategories() {
        return categoryService.findAll();
    }

    @GetMapping("/{id}")
    public Category getByCategoryId(@PathVariable Long id) {
        Optional<Category> optCategory = categoryService.findById(id);

        if(optCategory.isPresent()) {
            return optCategory.get();
        }

        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteByCategoryId(@PathVariable Long id) {
        categoryService.deleteByCategoryId(id);
    }

}
