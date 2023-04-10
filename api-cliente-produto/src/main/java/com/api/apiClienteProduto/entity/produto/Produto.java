package com.api.apiClienteProduto.entity.produto;

import jakarta.persistence.*;
import lombok.*;

import java.util.Calendar;

@Entity(name="Produto")
@Table(name="produtos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @Temporal(TemporalType.DATE)
    private Calendar dataCadastro = Calendar.getInstance();
    @Temporal(TemporalType.DATE)
    private Calendar dataAtualizacao;
    private boolean ativo;

    public Produto(Produto produto) {
        this.nome = produto.getNome();
        this.dataCadastro = produto.getDataCadastro();
        this.dataAtualizacao = produto.getDataAtualizacao();
        this.ativo = produto.isAtivo();
    }
}
