package io.github.pablojg9.product.modules.supplier.repository;

import io.github.pablojg9.product.modules.supplier.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends JpaRepository<Supplier, Integer> {
}
