package com.api.apiClienteProduto.entity.saldo;


import jakarta.persistence.*;
import lombok.*;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Saldo {
    @Column(name = "valor_saldo")
    private Double valor = 100.00;


}
