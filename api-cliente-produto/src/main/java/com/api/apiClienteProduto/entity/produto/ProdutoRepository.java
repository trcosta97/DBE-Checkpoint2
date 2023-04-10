package com.api.apiClienteProduto.entity.produto;

import com.api.apiClienteProduto.entity.cliente.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    List<Produto> findAllByAtivoTrue();
}
