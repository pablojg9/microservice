package io.github.pablojg9.product.modules.product.service;

import io.github.pablojg9.product.config.exception.ValidationException;
import io.github.pablojg9.product.modules.product.dto.CategoryRequest;
import io.github.pablojg9.product.modules.product.dto.CategoryResponse;
import io.github.pablojg9.product.modules.product.model.Category;
import io.github.pablojg9.product.modules.product.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static org.springframework.util.ObjectUtils.isEmpty;


@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    public CategoryResponse save(CategoryRequest categoryRequest) {
        validateCategoryNameInformed(categoryRequest);
        Category category = categoryRepository.save(Category.of(categoryRequest));
        return CategoryResponse.of(category);
    }

    private void validateCategoryNameInformed(CategoryRequest request) {
        if (isEmpty(request.getDescription())) {
            throw new ValidationException("The category description was not informed.");
        }
    }
}