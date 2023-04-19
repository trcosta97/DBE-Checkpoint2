package com.api.apiClienteProduto.entity.usuario;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.api.apiClienteProduto.entity.chavePix.ChavePix;
import com.api.apiClienteProduto.entity.endereco.Endereco;
import com.api.apiClienteProduto.entity.produto.Produto;
import com.api.apiClienteProduto.entity.saldo.Saldo;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "Usuario")
@Table(name = "t_usuarios")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@SequenceGenerator(name = "usuario", sequenceName = "sq_t_usuario", allocationSize = 1)
public class Usuario {

    @Id
    @GeneratedValue(generator = "usuario")
    @Column(name = "id_usuario")
    private Long id;
    @Column(name = "nome_usuario")
    private String nome;
    @Column(name = "email_usuario", unique = true)
    private String email;
    @Column(name = "nome_mae_usuario")
    private String nomeMae;
    @Column(name = "senha_usuario")
    private String senha;
    @Column(name = "telefone_usuario")
    private String telefone;
    @Column(name = "idade_usuario")
    private Integer idade;
    @Embedded
    @Column(name = "endereco_usuario")
    private Endereco endereco;
    @Column(name = "cpf_usuario")
    private String cpf;
    @Column(name = "rg_usuario")
    private String rg;
    @Column(name = "pessoa_publica_usuario")
    private boolean pessoaPublica;
    @Column(name = "renda_usuario")
    private Double renda;
    @Column(name = "patrimonio_usuario")
    private Double patrimonio;
    @Temporal(TemporalType.DATE)
    @Column(name = "data_cadastro_usuario")
    private Calendar dataCadastro = Calendar.getInstance();
    @Temporal(TemporalType.DATE)
    @Column(name = "data_atualizacao_usuario")
    private Calendar dataAtualizacao;
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Produto> produtos;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<ChavePix> chavesPix;

    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL)
    private Saldo saldo;
    private boolean ativo = true;

    public void addProduto(String nome) {
        // TODO: deveria haver um construtor com somente nome e usuário
        Produto produto = new Produto();
        produto.setNome(nome);
        produto.setUsuario(this);
        this.produtos.add(produto);
    }

    public Usuario(String nome, String email, String nomeMae, String senha, String telefone, int idade,
            Endereco endereco, String cpf,
            String rg, boolean pessoaPublica, double renda, double patrimonio) {
        this.nome = nome;
        this.email = email;
        this.nomeMae = nomeMae;
        this.senha = senha;
        this.telefone = telefone;
        this.idade = idade;
        this.endereco = endereco;
        this.cpf = cpf;
        this.rg = rg;
        this.pessoaPublica = pessoaPublica;
        this.renda = renda;
        this.patrimonio = patrimonio;
        this.dataCadastro = Calendar.getInstance();
        this.dataAtualizacao = Calendar.getInstance();
        this.produtos = new ArrayList<>();
        this.chavesPix = new ArrayList<>();
        // TODO: deveria haver um construtor com somente usuário
        this.saldo = new Saldo();
        this.saldo.setUsuario(this);
        this.ativo = true;
    }

    public void excluir() {
        this.ativo = false;
    }
}
