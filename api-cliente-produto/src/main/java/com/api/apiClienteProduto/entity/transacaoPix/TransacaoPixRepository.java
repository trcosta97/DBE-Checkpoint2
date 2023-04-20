package com.api.apiClienteProduto.entity.transacaoPix;

import com.api.apiClienteProduto.entity.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Calendar;
import java.util.List;

public interface TransacaoPixRepository extends JpaRepository<TransacaoPix, Long> {
    List<TransacaoPix> findByUsuarioCreditoId(Long id);

}
