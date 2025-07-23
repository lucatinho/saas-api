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

import java.util.List;

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

    public List<ProductBatchResponseDTO> getAllProductBatches(Long product_id) {
        List<ProductBatch> batches = productBatchRepository.findByProductId(product_id);
        return batches.stream()
                .map(productBatchMapper::toProductBatchResponseDto)
                .toList();
    }

    public ProductBatchResponseDTO create(ProductBatchRequestDTO data) {
        Product product = productRepository.findById(data.product_id()).orElseThrow(() -> new IllegalArgumentException("Produto nao encontrado."));
        ProductBatch productBatch = productBatchMapper.toEntity(data);
        productBatch.setProduct(product);

        productBatchRepository.save(productBatch);

        return productBatchMapper.toProductBatchResponseDto(productBatch);
    }

    public ProductBatchResponseDTO update(Long id, ProductBatchRequestDTO data) {
        ProductBatch existingBatch = productBatchRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Id nao encontrado"));
        Product product = productRepository.findById(data.product_id()).orElseThrow(() -> new IllegalArgumentException("Produto nao encontrado."));
        productBatchMapper.updateProductBatchFromDto(data, existingBatch);
        existingBatch.setProduct(product);
        productBatchRepository.save(existingBatch);
        return productBatchMapper.toProductBatchResponseDto(existingBatch);
    }
}
