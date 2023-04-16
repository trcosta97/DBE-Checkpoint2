package com.api.apiClienteProduto.entity.chavePix;

import com.api.apiClienteProduto.entity.usuario.Usuario;
import jakarta.persistence.*;
import lombok.*;

@Entity(name="Chave Pix")
@Table(name="t_chave_pix")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class ChavePix {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_chave_pix")
    private Long id;
    @Column(name = "tipo_chave_pix")
    @Enumerated(EnumType.STRING)
    private TipoChave tipo;
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    @Column(name = "valor_chave_pix")
    private String valor;
    @Column(name = "chave_chave_pix")
    private String chave;

    public ChavePix(ChavePix chavePix) {
        this.usuario = chavePix.getUsuario();
        this.tipo = chavePix.getTipo();
        this.usuario = chavePix.getUsuario();
        this.valor = chavePix.getValor();
        this.chave = chavePix.getChave();
    }
}
