package com.example.saas_app.repositories;

import com.example.saas_app.domain.technical.Technical;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TechnicalRepository extends JpaRepository<Technical, Long> {
}
