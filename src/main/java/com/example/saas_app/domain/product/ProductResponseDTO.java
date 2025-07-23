package com.example.saas_app.domain.product;

import com.example.saas_app.domain.product_batch.ProductBatchResponseDTO;

import java.util.List;

public record ProductResponseDTO(Long id,
                                 String name,
                                 String description,
                                 Number stock,
                                 Number minimumStock,
                                 Number percentSale,
                                 String brand,
                                 String category_prod,
                                 List<ProductBatchResponseDTO> productBatch) {
}
