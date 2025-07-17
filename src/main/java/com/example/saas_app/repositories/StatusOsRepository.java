package com.example.saas_app.repositories;

import com.example.saas_app.domain.status_os.StatusOs;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusOsRepository extends JpaRepository<StatusOs, Long> {
}
