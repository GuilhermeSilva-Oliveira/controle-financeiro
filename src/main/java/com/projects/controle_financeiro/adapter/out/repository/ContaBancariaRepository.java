package com.projects.controle_financeiro.adapter.out.repository;

import com.projects.controle_financeiro.application.domain.model.ContaBancaria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContaBancariaRepository extends JpaRepository<ContaBancaria,Long> {
}
