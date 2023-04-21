package com.api.apiClienteProduto.controller;

import com.api.apiClienteProduto.entity.transacaoPix.TransacaoPix;
import com.api.apiClienteProduto.entity.transacaoPix.TransacaoPixDTO;
import com.api.apiClienteProduto.service.TransacaoPixService;
import com.api.apiClienteProduto.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TransacaoPixController {

    @Autowired
    private TransacaoPixService transacaoPixService;

    @PostMapping("/transacaoPix")
    @Transactional
    public ResponseEntity<TransacaoPix> efetuarPix(@RequestBody TransacaoPix transacaoPix){
        TransacaoPix novaTransacao =  transacaoPixService.fazerPix(transacaoPix);
        return ResponseEntity.ofNullable(novaTransacao);
    }

    @GetMapping("/transacaoPix/recebidas/{id}")
    public ResponseEntity<List<TransacaoPix>> transacoesRecebidas(@PathVariable Long id){
        List<TransacaoPix> transacoes = transacaoPixService.transacoesRecebidasUltimos7Dias(id);
        return ResponseEntity.ofNullable(transacoes);

    }

    @GetMapping("/transacaoPix/efetuadas/{id}")
    public ResponseEntity<List<TransacaoPix>> transacoesEfetuadas(@PathVariable Long id){
        List<TransacaoPix> transacoes = transacaoPixService.transacoesEfetuadasUltimos7Dias(id);
        return ResponseEntity.ofNullable(transacoes);

    }

}
