package com.api.apiClienteProduto.entity.cliente;

import com.api.apiClienteProduto.entity.endereco.Endereco;
import com.api.apiClienteProduto.entity.produto.Produto;

import java.util.Calendar;
import java.util.List;

public record DadosAtualizacaoClienteDTO(
        Long id,
        String nome,
        String email,
        String nomeMae,
        String senha,
        String telefone,
        Integer idade,
        Endereco endereco,
        String cpf,
        String rg,
        boolean pessoaPublica,
        Float renda,
        Float patrimonio,
        List<Produto> produtos
) {}

