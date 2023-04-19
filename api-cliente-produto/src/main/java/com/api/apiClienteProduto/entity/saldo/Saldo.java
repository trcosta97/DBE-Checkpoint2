package com.api.apiClienteProduto.entity.saldo;

import com.api.apiClienteProduto.entity.usuario.Usuario;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "t_saldo")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@SequenceGenerator(name = "saldo", sequenceName = "sq_t_saldo", allocationSize = 1)
public class Saldo {

    // TODO: entender melhor papel de saldo como entidade; parece ser o caso de um embbed
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "saldo_id")
    private Long id;
    @Column(name = "valor_saldo")
    private Double valor = 100.00;
    @OneToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;
}
