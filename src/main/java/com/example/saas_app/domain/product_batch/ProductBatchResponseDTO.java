package com.example.saas_app.domain.product_batch;

import com.example.saas_app.domain.product.ProductResponseDTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ProductBatchResponseDTO(
        Long id,
        Integer amount,
        BigDecimal purchase_price,
        BigDecimal sale_price,
        LocalDateTime data_compra
) {
}
