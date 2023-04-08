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
    private List<Produto> produtos;
    private boolean ativo = true;

    public Cliente(DadosCadastroClienteDTO dados) {
        this.nome = dados.nome();
        this.email = dados.email();
        this.nomeMae = dados.nomeMae();
        this.senha = dados.senha();
        this.telefone = dados.senha();
        this.idade = dados.idade();
        this.endereco = dados.endereco();
        this.cpf = dados.cpf();
        this.rg = dados.rg();
        this.pessoaPublica = dados.pessoaPublica();
        this.renda = dados.renda();
        this.patrimonio = dados.patrimonio();
        this.produtos = dados.produtos();
    }

    public void excluir(){
        this.ativo = false;
    }

    public void atualizarInformacoes(DadosAtualizacaoClienteDTO dados){
        this.dataAtualizacao = Calendar.getInstance();
        if (dados.nome() != null){
            this.nome = dados.nome();
        }
        if (dados.email() != null){
            this.email = dados.email();
        }
        if (dados.nomeMae() != null){
            this.nomeMae = dados.nomeMae();
        }
        if (dados.senha() != null){
            this.senha = dados.senha();
        }
        if (dados.telefone() != null){
            this.telefone = dados.telefone();
        }
        if (dados.idade() != null){
            this.idade = dados.idade();
        }
        if (dados.endereco() != null){
            this.endereco = dados.endereco();
        }
        if (dados.cpf() != null){
            this.cpf = dados.cpf();
        }
        if (dados.rg() != null){
            this.rg = dados.rg();
        }
        if (dados.pessoaPublica() != null){
            this.pessoaPublica = dados.pessoaPublica();
        }
        if (dados.renda() != null){
            this.renda = dados.renda();
        }
        if (dados.patrimonio() != null){
            this.patrimonio = dados.renda();
        }
        if (dados.produtos() != null){
            this.produtos = dados.produtos();
        }

    }

}
