package com.projects.controle_financeiro.adapter.out.repository;

import com.projects.controle_financeiro.application.domain.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Integer> {}
