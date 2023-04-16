package com.api.apiClienteProduto.entity.usuario;

import com.api.apiClienteProduto.entity.chavePix.ChavePix;
import com.api.apiClienteProduto.entity.endereco.Endereco;
import com.api.apiClienteProduto.entity.produto.Produto;
import com.api.apiClienteProduto.entity.saldo.Saldo;
import jakarta.persistence.*;
import lombok.*;


import java.util.Calendar;
import java.util.List;

@Entity(name="Usuario")
@Table(name="t_usuarios")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_usuario")
    private Long id;
    @Column(name = "nome_usuario")
    private String nome;
    @Column(name="email_usuario", unique = true)
    private String email;
    @Column(name = "nome_mae_usuario")
    private String nomeMae;
    @Column(name="senha_usuario")
    private String senha;
    @Column(name = "telefone_usuario")
    private String telefone;
    @Column(name="idade_usuario")
    private Integer idade;
    @Embedded
    @Column(name="endereco_usuario")
    private Endereco endereco;
    @Column(name="cpf_usuario")
    private String cpf;
    @Column(name = "rg_usuario")
    private String rg;
    @Column(name="pessoa_publica_usuario")
    private boolean pessoaPublica;
    @Column(name = "renda_usuario")
    private Float renda;
    @Column(name = "patrimonio_usuario")
    private Float patrimonio;
    @Temporal(TemporalType.DATE)
    @Column(name = "data_cadastro_usuario")
    private Calendar dataCadastro = Calendar.getInstance();
    @Temporal(TemporalType.DATE)
    @Column(name = "data_atualizacao_usuario")
    private Calendar dataAtualizacao;
    @OneToMany
    @JoinColumn(name = "cliente_id")
    @Column(columnDefinition = "VARCHAR", name = "produtos_usuario")
    private List<Produto> produtos;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<ChavePix> chavesPix;

    @OneToOne(mappedBy = "usuario")
    private Saldo saldo;
    private boolean ativo = true;

    public Usuario(Usuario entity) {
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
        this.chavesPix = entity.chavesPix;
        this.saldo = entity.saldo;
        this.ativo = entity.ativo;
    }

    public void excluir(){
        this.ativo = false;
    }



}
