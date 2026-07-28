package com.projects.controle_financeiro.adapter.out.repository;

import com.projects.controle_financeiro.application.domain.model.AlertaAtraso;
import com.projects.controle_financeiro.application.domain.model.ContaBancaria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlertaRepository extends JpaRepository<AlertaAtraso,Integer> {

}
