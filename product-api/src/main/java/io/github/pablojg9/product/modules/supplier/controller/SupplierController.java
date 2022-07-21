package io.github.pablojg9.product.modules.supplier.controller;

import io.github.pablojg9.product.modules.supplier.dto.SupplierRequest;
import io.github.pablojg9.product.modules.supplier.dto.SupplierResponse;
import io.github.pablojg9.product.modules.supplier.service.SupplierService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/supplier")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SupplierController {

    private final SupplierService supplierService;

    @PostMapping
    public SupplierResponse save(@RequestBody SupplierRequest supplierRequest) {
        return supplierService.save(supplierRequest);
    }
}
