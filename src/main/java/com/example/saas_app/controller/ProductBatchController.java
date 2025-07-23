package com.example.saas_app.controller;

import com.example.saas_app.domain.product_batch.ProductBatch;
import com.example.saas_app.domain.product_batch.ProductBatchRequestDTO;
import com.example.saas_app.domain.product_batch.ProductBatchResponseDTO;
import com.example.saas_app.service.ProductBatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/product-batch")
public class ProductBatchController {

    @Autowired
    private ProductBatchService productBatchService;

    @GetMapping("/{id}")
    public ResponseEntity<ProductBatchResponseDTO> getById(@PathVariable Long id){
        ProductBatchResponseDTO productBatch = productBatchService.getProductBatchById(id);
        return ResponseEntity.ok(productBatch);
    }

    @PostMapping
    public ResponseEntity<ProductBatch> create(@RequestBody ProductBatchRequestDTO body) {
        ProductBatch productBatch = this.productBatchService.create(body);
        return ResponseEntity.ok(productBatch);
    }
}
