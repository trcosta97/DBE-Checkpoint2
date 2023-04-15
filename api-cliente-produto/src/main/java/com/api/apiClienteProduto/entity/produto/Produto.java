package com.api.apiClienteProduto.entity.produto;

import jakarta.persistence.*;
import lombok.*;

import java.util.Calendar;

@Entity(name="Produto")
@Table(name="t_produtos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "nome_produto")
    private String nome;
    @Temporal(TemporalType.DATE)
    private Calendar dataCadastro = Calendar.getInstance();
    @Temporal(TemporalType.DATE)
    @Column(name="data_atualziacao")
    private Calendar dataAtualizacao;
    private boolean ativo;

    public Produto(Produto produto) {
        this.nome = produto.getNome();
        this.dataCadastro = produto.getDataCadastro();
        this.dataAtualizacao = produto.getDataAtualizacao();
        this.ativo = produto.isAtivo();
    }

    public void excluir(){
        this.ativo = false;
    }
}
