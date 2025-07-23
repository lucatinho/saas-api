package com.example.saas_app.service;

import com.example.saas_app.domain.product.Product;
import com.example.saas_app.domain.product.ProductResponseDTO;
import com.example.saas_app.domain.product_batch.ProductBatch;
import com.example.saas_app.domain.product_batch.ProductBatchRequestDTO;
import com.example.saas_app.domain.product_batch.ProductBatchResponseDTO;
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


    public ProductBatchResponseDTO getProductBatchById(Long id) {
        ProductBatch productBatch = productBatchRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Id nao encontrado"));
        Product product = productRepository.findById(productBatch.getId()).orElseThrow(() -> new EntityNotFoundException("Id nao encontrado"));
        return new ProductBatchResponseDTO(
                productBatch.getId(),
                productBatch.getAmount(),
                productBatch.getPurchase_price(),
                productBatch.getSale_price(),
                productBatch.getData_compra()
//                new ProductResponseDTO(
//                        product.getId(),
//                        product.getName(),
//                        product.getDescription(),
//                        product.getStock(),
//                        product.getMinimumStock(),
//                        product.getPercentSale(),
//                        product.getBrand().getName(),
//                        product.getCategoryProd().getName()
//                )
        );
    }

    public ProductBatch create(@RequestBody ProductBatchRequestDTO data) {
        Product product = productRepository.findById(data.product_id()).orElseThrow(() -> new IllegalArgumentException("Produto nao encontrado."));
        ProductBatch productBatch = new ProductBatch();
        productBatch.setProduct(product);
        productBatch.setAmount(data.amount());
        productBatch.setPurchase_price(data.purchase_price());
        productBatch.setSale_price(data.sale_price());
        productBatch.setData_compra(data.data_compra());

        productBatchRepository.save(productBatch);

        return productBatch;
    }
}
