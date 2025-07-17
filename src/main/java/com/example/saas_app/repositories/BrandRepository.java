package com.example.saas_app.repositories;

import com.example.saas_app.domain.brand.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<Brand, Long> {
}
