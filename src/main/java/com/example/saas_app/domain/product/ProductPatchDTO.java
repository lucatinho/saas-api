package com.example.saas_app.domain.product;

import java.util.Optional;

public record ProductPatchDTO(Optional<String> name,
                              Optional<String> description,
                              Optional<Integer> stock,
                              Optional<Integer> minimumStock,
                              Optional<Integer> percentSale,
                              Optional<Long> brand_id,
                              Optional<Long> category_prod_id) {
}
