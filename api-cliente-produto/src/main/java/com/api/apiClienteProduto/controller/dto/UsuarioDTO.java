package com.api.apiClienteProduto.controller.dto;

import java.util.Calendar;
import java.util.List;

import com.api.apiClienteProduto.entity.endereco.Endereco;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO {

    private Long id;
    private String nome;
    private String email;
    private String nomeMae;
    private String senha;
    private String telefone;
    private Integer idade;
    private Endereco endereco;
    private String cpf;
    private String rg;
    private boolean pessoaPublica;
    private Double renda;
    private Double patrimonio;
    private Calendar dataCadastro;
    private Calendar dataAtualizacao;
    private List<ProdutoDTO> produtos;
    private List<ChavePixDTO> chavesPix;
    private SaldoDTO saldo;
    private boolean ativo;

}
