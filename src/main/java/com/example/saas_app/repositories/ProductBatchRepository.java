package com.example.saas_app.repositories;

import com.example.saas_app.domain.product_batch.ProductBatch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductBatchRepository extends JpaRepository<ProductBatch, Long> {
}
