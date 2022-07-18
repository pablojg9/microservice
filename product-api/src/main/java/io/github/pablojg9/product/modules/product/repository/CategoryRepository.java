package io.github.pablojg9.product.modules.product.repository;

import io.github.pablojg9.product.modules.product.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
