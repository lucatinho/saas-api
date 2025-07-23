package com.example.saas_app.service;

import com.example.saas_app.domain.product.Product;
import com.example.saas_app.domain.product_batch.ProductBatch;
import com.example.saas_app.domain.product_batch.ProductBatchRequestDTO;
import com.example.saas_app.domain.product_batch.ProductBatchResponseDTO;
import com.example.saas_app.mapper.ProductBatchMapper;
import com.example.saas_app.repositories.ProductBatchRepository;
import com.example.saas_app.repositories.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class ProductBatchService {

    @Autowired
    private ProductBatchRepository productBatchRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductBatchMapper productBatchMapper;


    public ProductBatchResponseDTO getProductBatchById(Long id) {
        ProductBatch productBatch = productBatchRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Id nao encontrado"));
        return productBatchMapper.toProductBatchResponseDto(productBatch);
    }

    public ProductBatchResponseDTO create(@RequestBody ProductBatchRequestDTO data) {
        Product product = productRepository.findById(data.product_id()).orElseThrow(() -> new IllegalArgumentException("Produto nao encontrado."));
        ProductBatch productBatch = productBatchMapper.toEntity(data);
        productBatch.setProduct(product);

        productBatchRepository.save(productBatch);

        return productBatchMapper.toProductBatchResponseDto(productBatch);
    }
}
