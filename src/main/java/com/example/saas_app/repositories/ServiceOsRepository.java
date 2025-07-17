package com.example.saas_app.repositories;

import com.example.saas_app.domain.service_os.ServiceOs;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceOsRepository extends JpaRepository<ServiceOs, Long> {
}
