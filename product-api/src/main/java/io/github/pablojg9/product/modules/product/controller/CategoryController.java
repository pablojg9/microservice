package io.github.pablojg9.product.modules.product.controller;

import io.github.pablojg9.product.modules.product.dto.CategoryRequest;
import io.github.pablojg9.product.modules.product.dto.CategoryResponse;
import io.github.pablojg9.product.modules.product.service.CategoryService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public CategoryResponse save(@RequestBody CategoryRequest categoryRequest) {
        return categoryService.save(categoryRequest);
    }
}
