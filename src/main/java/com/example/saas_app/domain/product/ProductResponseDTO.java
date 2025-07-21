package com.example.saas_app.domain.product;

public record ProductResponseDTO(Long id, String name, String description, Number stock, Number minimumStock,
                                 Number percenSale, String brand, String category_prod) {
}
