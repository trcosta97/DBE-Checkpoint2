package com.api.apiClienteProduto.entity.cliente;

import com.api.apiClienteProduto.entity.endereco.Endereco;
import com.api.apiClienteProduto.entity.produto.Produto;
import jakarta.persistence.*;
import lombok.*;

import java.util.Calendar;
import java.util.List;

@Entity(name="Cliente")
@Table(name="clientes")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @Column(unique = true)
    private String email;
    private String nomeMae;
    private String senha;
    private String telefone;
    private Integer idade;
    @Embedded
    private Endereco endereco;
    private String cpf;
    private String rg;
    private boolean pessoaPublica;
    private Float renda;
    private Float patrimonio;
    @Temporal(TemporalType.DATE)
    private Calendar dataCadastro = Calendar.getInstance();
    @Temporal(TemporalType.DATE)
    private Calendar dataAtualizacao;
    @OneToMany
    @JoinColumn(name = "cliente_id")
    @Column(columnDefinition = "VARCHAR")
    private List<Produto> produtos;
    private boolean ativo = true;

    public Cliente(Cliente entity) {
        this.nome = entity.nome;
        this.email = entity.email;
        this.nomeMae = entity.nomeMae;
        this.senha = entity.senha;
        this.telefone = entity.telefone;
        this.idade = entity.idade;
        this.endereco = entity.endereco;
        this.cpf = entity.cpf;
        this.rg = entity.rg;
        this.pessoaPublica = entity.pessoaPublica;
        this.renda = entity.renda;
        this.patrimonio = entity.patrimonio;
        this.dataCadastro = entity.dataCadastro;
        this.dataAtualizacao = entity.dataAtualizacao;
        this.produtos = entity.produtos;
        this.ativo = entity.ativo;
    }

    public void excluir(){
        this.ativo = false;
    }



}
