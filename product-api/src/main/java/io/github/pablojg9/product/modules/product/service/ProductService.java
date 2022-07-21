package io.github.pablojg9.product.modules.product.service;

import io.github.pablojg9.product.config.exception.ValidationException;
import io.github.pablojg9.product.modules.category.service.CategoryService;
import io.github.pablojg9.product.modules.product.dto.ProductRequest;
import io.github.pablojg9.product.modules.product.dto.ProductResponse;
import io.github.pablojg9.product.modules.product.model.Product;
import io.github.pablojg9.product.modules.product.repository.ProductRepository;
import io.github.pablojg9.product.modules.supplier.service.SupplierService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static org.springframework.util.ObjectUtils.isEmpty;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProductService {

    private static final Integer ZERO = 0;
    private final ProductRepository productRepository;
    private final SupplierService supplierService;
    private final CategoryService categoryService;

    public ProductResponse save(ProductRequest request) {
        validateProductDataInformed(request);
        validateCategoryAndSupplierIdInformed(request);
        var category = categoryService.findById(request.getCategoryId());
        var supplier = supplierService.findById(request.getSupplierId());
        var product = productRepository.save(Product.of(request, category, supplier));
        return ProductResponse.of(product);
    }

    private void validateProductDataInformed(ProductRequest productRequest) {
        if (isEmpty(productRequest.getName())) {
            throw new ValidationException("The product's name was not informed");
        }

        if (isEmpty(productRequest.getQuantityAvailable())) {
            throw new ValidationException("The product's quantity was not informed");
        }

        if (productRequest.getQuantityAvailable() <= ZERO) {
            throw new ValidationException("The quantity should not be less or to zero");
        }
    }

    private void validateCategoryAndSupplierIdInformed(ProductRequest productRequest) {
        if (isEmpty(productRequest.getCategoryId())) {
            throw new ValidationException("The Category id was not informed");
        }

        if (isEmpty(productRequest.getSupplierId())) {
            throw new ValidationException("The Supplier id was not informed");
        }
    }
}
