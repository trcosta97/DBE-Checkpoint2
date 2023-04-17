package com.api.apiClienteProduto.entity.transacaoPix;

import com.api.apiClienteProduto.entity.usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.Calendar;

@Entity(name = "Transacao Pix")
@Table(name = "t_transacao_pix")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SequenceGenerator(name = "transacao_pix", sequenceName = "sq_t_transacaoPix", allocationSize = 1)
public class TransacaoPix {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "transacao_pix_id")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "usuario_debito_id")
    private Usuario usuarioDebito;
    @ManyToOne
    @JoinColumn(name = "usuario_credito_id")
    private Usuario usuarioCredito;
    @Column(nullable = false, name = "transacao_pix_valor")
    private Double valor;
    @Column(nullable = false, name = "transacao_pix_status")
    private String status;
    @Column(name = "transacao_pix_descricao")
    private String descricao;
    @Column(name = "transacao_pix_data")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar dataTransicao;

}
