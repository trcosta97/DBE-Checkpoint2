package com.api.apiClienteProduto.entity.transacaoPix;

import com.api.apiClienteProduto.entity.usuario.Usuario;

public record TransacaoPixDTO(Usuario usuarioDebito, Usuario usuarioCredito, Double valor) {
}
