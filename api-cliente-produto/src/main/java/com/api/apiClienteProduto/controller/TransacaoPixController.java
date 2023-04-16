package com.api.apiClienteProduto.controller;

import com.api.apiClienteProduto.entity.transacaoPix.TransacaoPix;
import com.api.apiClienteProduto.service.TransacaoPixService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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

}
