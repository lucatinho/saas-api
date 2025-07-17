package com.example.saas_app.domain.product;

public record ProductRequestDTO(String name, String description, Integer stock, Integer minimumStock, Integer percentSale,
                                Long brand_id) {
}