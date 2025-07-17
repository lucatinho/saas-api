package com.example.saas_app.domain.product;

import com.example.saas_app.domain.brand.Brand;
import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Table(name = "product")
@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private Number stock;
    private Number minimumStock;
    private Number purchasePrice;
    private Number salePrice;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;
}