package com.example.saas_app.service;

import com.example.saas_app.domain.brand.Brand;
import com.example.saas_app.domain.product.Product;
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

    public Product createProduct(ProductRequestDTO data) {
        System.out.println("[LOG] createProduct");
        Brand brand = brandRepository.findById(data.brand_id()).orElseThrow(() -> new IllegalArgumentException("Marca nao encontrada."));
        Product newProduct = new Product();
        newProduct.setBrand(brand);
        newProduct.setName(data.name());
        newProduct.setDescription(data.description());
        newProduct.setStock(data.stock());
        newProduct.setMinimumStock(data.minimumStock());
        newProduct.setPercentSale(data.percentSale());

        System.out.println(newProduct.toString());
        return productRepository.save(newProduct);
    }

    public ProductResponseDTO getProductById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Id nao encontrado"));
        log.info("[LOG] getProductById");
        return new ProductResponseDTO(product.getId(), product.getName(), product.getDescription(),
                product.getStock(), product.getMinimumStock(), product.getPercentSale(), product.getBrand().getName());
    }
}
