package com.api.apiClienteProduto.entity.produto;

import com.api.apiClienteProduto.entity.usuario.Usuario;
import com.fasterxml.jackson.annotation.JsonBackReference;
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
@SequenceGenerator(name = "produto", sequenceName = "sq_t_produto", allocationSize = 1)
@Embeddable
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(name = "nome_produto")
    private String nome;
    @Temporal(TemporalType.DATE)
    private Calendar dataCadastro = Calendar.getInstance();
    @Temporal(TemporalType.DATE)
    @Column(name="data_atualizacao")
    private Calendar dataAtualizacao;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    @JsonBackReference
    private Usuario usuario;
    private boolean ativo;

    public Produto(Produto produto) {
        this.nome = produto.getNome();
        this.dataCadastro = produto.getDataCadastro();
        this.dataAtualizacao = produto.getDataAtualizacao();
        this.usuario = produto.getUsuario();
        this.ativo = produto.isAtivo();
    }

    public void excluir(){
        this.ativo = false;
    }
}
