package com.example.saas_app.service;

import com.example.saas_app.domain.brand.Brand;
import com.example.saas_app.domain.category_prod.CategoryProd;
import com.example.saas_app.domain.product.Product;
import com.example.saas_app.domain.product.ProductPatchDTO;
import com.example.saas_app.domain.product.ProductRequestDTO;
import com.example.saas_app.domain.product.ProductResponseDTO;
import com.example.saas_app.mapper.ProductMapper;
import com.example.saas_app.repositories.BrandRepository;
import com.example.saas_app.repositories.CategoryProdRepository;
import com.example.saas_app.repositories.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private CategoryProdRepository categoryProdRepository;

    @Autowired
    private ProductMapper productMapper;

    public ProductResponseDTO getProductById(Long id) {
        log.info("[LOG] getProductById");
        Product product = productRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Id nao encontrado"));
        return productMapper.toResponseDTO(product);
    }

    public List<ProductResponseDTO> getProducts(int page, int size, String name) {
        log.info("[LOG] getProducts by name");
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> productPage;

        if (name == null || name.isEmpty()) {
            productPage = productRepository.findAll(pageable);
            List<Product> teste = productPage.stream().toList();
            log.info(teste.toString());
        } else {
            productPage = productRepository.findByNameContainingIgnoreCase(name, pageable);
        }

        return productPage.stream().map(productMapper::toResponseDTO).toList();
    }

    public Product createProduct(ProductRequestDTO data) {
        log.info("[LOG] createProduct");
        Brand brand = brandRepository.findById(data.brand_id()).orElseThrow(() -> new IllegalArgumentException("Marca nao encontrada."));
        CategoryProd categoryProd = categoryProdRepository.findById(data.category_prod_id()).orElseThrow(() -> new IllegalArgumentException("Categoria nao encontrada."));
        Product newProduct = productMapper.toEntity(data);
        newProduct.setBrand(brand);
        newProduct.setCategoryProd(categoryProd);

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

        data.category_prod_id().ifPresent(categoryProdId -> {
            CategoryProd categoryProd = categoryProdRepository.findById(categoryProdId).orElseThrow(() -> new IllegalArgumentException("Categoria nao encontrada."));
            product.setCategoryProd(categoryProd);
        });

        return productRepository.save(product);
    }

}
