package com.projects.controle_financeiro.adapter.out.repository;

import com.projects.controle_financeiro.application.domain.model.Renda;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RendaRecorrenteRepository extends JpaRepository<Renda,Long> {
}
