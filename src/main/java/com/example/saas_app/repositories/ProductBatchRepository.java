package com.example.saas_app.repositories;

import com.example.saas_app.domain.product_batch.ProductBatch;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductBatchRepository extends JpaRepository<ProductBatch, Long> {
    List<ProductBatch> findByProductId(Long productId);
}
