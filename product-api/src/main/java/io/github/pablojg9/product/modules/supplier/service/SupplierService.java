package io.github.pablojg9.product.modules.supplier.service;

import io.github.pablojg9.product.config.exception.ValidationException;
import io.github.pablojg9.product.modules.supplier.dto.SupplierRequest;
import io.github.pablojg9.product.modules.supplier.dto.SupplierResponse;
import io.github.pablojg9.product.modules.supplier.model.Supplier;
import io.github.pablojg9.product.modules.supplier.repository.SupplierRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.util.ObjectUtils.isEmpty;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SupplierService {

    private final SupplierRepository supplierRepository;

    public List<SupplierResponse> findAll() {
        return supplierRepository
                .findAll()
                .stream()
                .map(SupplierResponse::of)
                .collect(Collectors.toList());
    }

    public SupplierResponse findByIdResponse(Integer id) {
        if (isEmpty(id)) {
            throw new ValidationException("The supplier ID was not informed.");
        }

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

    private void validateSupplierNameInformed(SupplierRequest request) {
        if (isEmpty(request.getName())) {
            throw new ValidationException("The Supplier name was not informed.");
        }
    }
}
