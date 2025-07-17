package com.example.saas_app.repositories;

import com.example.saas_app.domain.os.OrdemServico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdemServicoRepository extends JpaRepository<OrdemServico, Long> {
}
