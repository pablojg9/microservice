package io.github.pablojg9.product.modules.category.service;

import io.github.pablojg9.product.config.exception.ValidationException;
import io.github.pablojg9.product.modules.category.dto.CategoryRequest;
import io.github.pablojg9.product.modules.category.dto.CategoryResponse;
import io.github.pablojg9.product.modules.category.model.Category;
import io.github.pablojg9.product.modules.category.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.util.ObjectUtils.isEmpty;


@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<CategoryResponse> findAll() {
        return categoryRepository
                .findAll()
                .stream()
                .map(CategoryResponse::of)
                .collect(Collectors.toList());
    }

    public CategoryResponse findByIdResponse(Integer id) {
        return CategoryResponse.of(findById(id));
    }

    public List<CategoryResponse> findByDescription(String description) {

        if(isEmpty(description)) {
            throw new ValidationException("The category description must be informed!");
        }
         return categoryRepository
                .findByDescriptionIgnoreCaseContaining(description)
                .stream()
                .map(CategoryResponse::of)
                .collect(Collectors.toList());
    }

    public Category findById(Integer id) {

        if(isEmpty(id)){
            throw new ValidationException("The category ID was not informed.");
        }

        return categoryRepository
                .findById(id)
                .orElseThrow(() -> new ValidationException("There's no supplier for the given ID"));
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
