package com.api.apiClienteProduto.entity.transacaoPix;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Calendar;
import java.util.List;

public interface TransacaoPixRepository extends JpaRepository<TransacaoPix, Long> {
    List<TransacaoPix> findAllByUsuarioCreditoIdAndDataTransacaoBetween(Long id, Calendar seteDiasAtrás, Calendar dataTransacao);
    List<TransacaoPix> findAllByUsuarioDebitoIdAndDataTransacaoBetween(Long id, Calendar seteDiasAtrás, Calendar dataTransacao);
}


