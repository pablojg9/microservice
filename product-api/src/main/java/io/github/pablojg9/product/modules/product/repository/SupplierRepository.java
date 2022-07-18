package io.github.pablojg9.product.modules.product.repository;

import io.github.pablojg9.product.modules.product.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends JpaRepository<Supplier, Integer> {
}
