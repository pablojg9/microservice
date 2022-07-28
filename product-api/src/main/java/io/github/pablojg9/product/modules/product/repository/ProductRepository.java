package io.github.pablojg9.product.modules.product.repository;

import io.github.pablojg9.product.modules.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    List<Product> findByNameContainingIgnoreCase(String name);

    List<Product> findByCategoryId(Integer id);

    List<Product> findBySupplierId(Integer id);
}
