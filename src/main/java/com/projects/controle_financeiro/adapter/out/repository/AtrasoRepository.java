package com.projects.controle_financeiro.adapter.out.repository;

import com.projects.controle_financeiro.application.domain.model.Atraso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AtrasoRepository extends JpaRepository<Atraso,Long> {
}
