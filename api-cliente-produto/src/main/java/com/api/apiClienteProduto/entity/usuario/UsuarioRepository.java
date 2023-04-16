package com.api.apiClienteProduto.entity.usuario;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    List<Usuario> findAllByAtivoTrue();

    List<Usuario> findAllByCpf(String cpf);
}

