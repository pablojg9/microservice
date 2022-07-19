package io.github.pablojg9.product.modules.category.repository;

import io.github.pablojg9.product.modules.category.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
