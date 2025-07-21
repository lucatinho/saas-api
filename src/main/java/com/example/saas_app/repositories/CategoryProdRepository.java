package com.example.saas_app.repositories;

import com.example.saas_app.domain.category_prod.CategoryProd;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryProdRepository extends JpaRepository<CategoryProd, Long> {
}
