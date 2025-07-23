package com.example.saas_app.domain.product_batch;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ProductBatchResponseDTO(
        Long id,
        Integer amount,
        BigDecimal purchase_price,
        BigDecimal sale_price,
        LocalDateTime data_compra,
        Long product_id
) {
}
