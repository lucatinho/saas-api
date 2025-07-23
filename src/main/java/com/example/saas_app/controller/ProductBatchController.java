package com.example.saas_app.controller;

import com.example.saas_app.domain.product_batch.ProductBatchRequestDTO;
import com.example.saas_app.domain.product_batch.ProductBatchResponseDTO;
import com.example.saas_app.service.ProductBatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product-batch")
public class ProductBatchController {

    @Autowired
    private ProductBatchService productBatchService;

    @GetMapping("/{id}")
    public ResponseEntity<ProductBatchResponseDTO> getById(@PathVariable Long id) {
        ProductBatchResponseDTO productBatch = productBatchService.getProductBatchById(id);
        return ResponseEntity.ok(productBatch);
    }

    @GetMapping
    public ResponseEntity<List<ProductBatchResponseDTO>> getBatchesByProductId(@RequestParam Long productId) {
        return ResponseEntity.ok(productBatchService.getAllProductBatches(productId));
    }

    @PostMapping
    public ResponseEntity<ProductBatchResponseDTO> create(@RequestBody ProductBatchRequestDTO body) {
        ProductBatchResponseDTO productBatch = this.productBatchService.create(body);
        return ResponseEntity.ok(productBatch);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductBatchResponseDTO> update(@PathVariable Long id, @RequestBody ProductBatchRequestDTO body) {
        ProductBatchResponseDTO productBatch = this.productBatchService.update(id, body);
        return ResponseEntity.ok(productBatch);
    }
}
