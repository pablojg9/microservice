package io.github.pablojg9.product.modules.category.controller;

import io.github.pablojg9.product.config.exception.SuccessResponse;
import io.github.pablojg9.product.modules.category.dto.CategoryRequest;
import io.github.pablojg9.product.modules.category.dto.CategoryResponse;
import io.github.pablojg9.product.modules.category.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public List<CategoryResponse> findAll() {
        return categoryService.findAll();
    }

    @GetMapping("description/{description}")
    public List<CategoryResponse> findByDescription(@PathVariable String description) {
        return categoryService.findByDescription(description);
    }

    @GetMapping("{id}")
    public CategoryResponse findById(@PathVariable Integer id) {
        return categoryService.findByIdResponse(id);
    }

    @PostMapping
    public CategoryResponse save(@RequestBody CategoryRequest categoryRequest) {
        return categoryService.save(categoryRequest);
    }

    @DeleteMapping("{id}")
    public SuccessResponse deleteById(@PathVariable Integer id) {
        return categoryService.deleteById(id);
    }

    @PutMapping("update/{id}")
    public CategoryResponse update(@RequestBody CategoryRequest categoryRequest, @PathVariable Integer id) {
        return categoryService.update(categoryRequest, id);
    }
}
