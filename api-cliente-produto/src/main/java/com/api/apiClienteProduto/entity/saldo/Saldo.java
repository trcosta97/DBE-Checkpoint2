package com.api.apiClienteProduto.entity.saldo;

import com.api.apiClienteProduto.entity.usuario.Usuario;
import jakarta.persistence.*;
import lombok.*;

@Entity(name="Saldo")
@Table(name="t_saldo")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Saldo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "saldo_id")
    private Long id;
    @Column(name="valor_saldo")
    private Double valor = 100.00;
    @OneToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
}
