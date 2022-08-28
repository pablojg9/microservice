package io.github.pablojg9.product.modules.supplier.service;

import io.github.pablojg9.product.config.exception.SuccessResponse;
import io.github.pablojg9.product.config.exception.ValidationException;
import io.github.pablojg9.product.modules.product.service.ProductService;
import io.github.pablojg9.product.modules.supplier.dto.SupplierRequest;
import io.github.pablojg9.product.modules.supplier.dto.SupplierResponse;
import io.github.pablojg9.product.modules.supplier.model.Supplier;
import io.github.pablojg9.product.modules.supplier.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.util.ObjectUtils.isEmpty;

@Service
public class SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private ProductService productService;

    public List<SupplierResponse> findAll() {
        return supplierRepository
                .findAll()
                .stream()
                .map(SupplierResponse::of)
                .collect(Collectors.toList());
    }

    public SupplierResponse findByIdResponse(Integer id) {
        validateInformedId(id);
        return SupplierResponse
                .of(supplierRepository
                        .findById(id)
                        .orElseThrow(() -> new ValidationException("There's no supplier for the given ID")));
    }

    public Supplier findById(Integer id) {
        return supplierRepository
                .findById(id)
                .orElseThrow(() -> new ValidationException("There's no supplier for the given ID"));
    }

    public List<SupplierResponse> findByName(String name) {
        if (isEmpty(name)) {
            throw new ValidationException("The Supplier name was not informed");
        }

        return supplierRepository
                .findByNameContainingIgnoreCase(name)
                .stream()
                .map(SupplierResponse::of)
                .collect(Collectors.toList());
    }

    public SupplierResponse save(SupplierRequest supplierRequest) {
        validateSupplierNameInformed(supplierRequest);
        Supplier supplier = supplierRepository.save(Supplier.of(supplierRequest));

        return SupplierResponse.of(supplier);
    }

    public SupplierResponse update(SupplierRequest supplierRequest, Integer id) {
        validateSupplierNameInformed(supplierRequest);
        validateInformedId(id);

        Supplier supplier = Supplier.of(supplierRequest);
        supplier.setId(id);
        supplierRepository.save(supplier);

        return SupplierResponse.of(supplier);
    }

    public SuccessResponse deleteById(Integer id) {
        validateInformedId(id);
        if (productService.existsBySupplierId(id)) {
            throw new ValidationException("You cannot delete this supplier because it's already defined by a product.");
        }

        supplierRepository.deleteById(id);
        return SuccessResponse
                .create("the supplier was delete");
    }

    private void validateInformedId(Integer id) {
        if (isEmpty(id)) {
            throw new ValidationException("The supplier ID was not informed.");
        }
    }

    private void validateSupplierNameInformed(SupplierRequest request) {
        if (isEmpty(request.getName())) {
            throw new ValidationException("The Supplier name was not informed.");
        }
    }
}
