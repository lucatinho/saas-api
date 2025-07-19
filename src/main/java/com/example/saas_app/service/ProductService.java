package com.example.saas_app.service;

import com.example.saas_app.domain.brand.Brand;
import com.example.saas_app.domain.product.Product;
import com.example.saas_app.domain.product.ProductPatchDTO;
import com.example.saas_app.domain.product.ProductRequestDTO;
import com.example.saas_app.domain.product.ProductResponseDTO;
import com.example.saas_app.repositories.BrandRepository;
import com.example.saas_app.repositories.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private BrandRepository brandRepository;

    public ProductResponseDTO getProductById(Long id) {
        log.info("[LOG] getProductById");
        Product product = productRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Id nao encontrado"));
        return new ProductResponseDTO(product.getId(), product.getName(), product.getDescription(),
                product.getStock(), product.getMinimumStock(), product.getPercentSale(), product.getBrand().getName());
    }

    public Product createProduct(ProductRequestDTO data) {
        log.info("[LOG] createProduct");
        Brand brand = brandRepository.findById(data.brand_id()).orElseThrow(() -> new IllegalArgumentException("Marca nao encontrada."));
        Product newProduct = new Product();
        newProduct.setBrand(brand);
        newProduct.setName(data.name());
        newProduct.setDescription(data.description());
        newProduct.setStock(data.stock());
        newProduct.setMinimumStock(data.minimumStock());
        newProduct.setPercentSale(data.percentSale());

        return productRepository.save(newProduct);
    }

    public Product updateProduct(Long id, ProductPatchDTO data) {
        log.info("[LOG] updateProduct");
        Product product = productRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Produto não encontrada."));
        data.name().ifPresent(product::setName);
        data.description().ifPresent(product::setDescription);
        data.stock().ifPresent(product::setStock);
        data.minimumStock().ifPresent(product::setMinimumStock);
        data.percentSale().ifPresent(product::setPercentSale);

        data.brand_id().ifPresent(brandId -> {
            Brand brand = brandRepository.findById(brandId).orElseThrow(() -> new IllegalArgumentException("Marca nao encontrada."));
            product.setBrand(brand);
        });

        return productRepository.save(product);
    }

}
