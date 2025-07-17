package com.example.saas_app.controller;

import com.example.saas_app.domain.product.Product;
import com.example.saas_app.domain.product.ProductRequestDTO;
import com.example.saas_app.domain.product.ProductResponseDTO;
import com.example.saas_app.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> getById(@PathVariable Long id) {
        ProductResponseDTO product = productService.getProductById(id);
        return ResponseEntity.ok(product);
    }

    @PostMapping
    public ResponseEntity<Product> create(@RequestBody ProductRequestDTO body){
        Product newProduct = this.productService.createProduct(body);
        return ResponseEntity.ok(newProduct);
    }
}
