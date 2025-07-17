package com.example.saas_app.repositories;

import com.example.saas_app.domain.sales.Sales;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalesRepository extends JpaRepository<Sales, Long> {
}
