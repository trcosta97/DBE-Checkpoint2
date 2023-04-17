package com.api.apiClienteProduto.entity.chavePix;

import com.api.apiClienteProduto.entity.usuario.Usuario;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="t_chave_pix")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@SequenceGenerator(name = "chave_pix", sequenceName = "sq_t_chave_pix", allocationSize = 1)
public class ChavePix {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_chave_pix")

    private Long id;
    @Column(name = "tipo_chave_pix")
    @Enumerated(EnumType.STRING)
    private TipoChave tipo;
    @ManyToOne
    @JoinColumn(name = "id_usuario")
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
