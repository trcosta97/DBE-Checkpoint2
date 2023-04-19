package com.api.apiClienteProduto.controller.dto;

import com.api.apiClienteProduto.entity.chavePix.TipoChave;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChavePixDTO {

    private Long id;
    private TipoChave tipo;
    private String valor;
    private String chave;

}
