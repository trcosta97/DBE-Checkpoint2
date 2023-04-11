package com.api.apiClienteProduto.entity.cliente;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;
import java.util.List;
import java.util.stream.DoubleStream;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    List<Cliente> findAllByAtivoTrue();

    List<Cliente> findAllByCpf(String cpf);
}
