package io.github.pablojg9.product.modules.product.repository;

import io.github.pablojg9.product.modules.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
