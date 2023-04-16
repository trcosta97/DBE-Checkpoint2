package com.api.apiClienteProduto.controller;

import com.api.apiClienteProduto.entity.chavePix.ChavePix;
import com.api.apiClienteProduto.entity.usuario.Usuario;
import com.api.apiClienteProduto.service.ChavePixService;
import com.api.apiClienteProduto.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
public class ChavePixController {
    @Autowired
    private ChavePixService chavePixService;
    @Autowired
    private UsuarioService usuarioService;


    @PostMapping("/chavePix")
    public ResponseEntity<ChavePix> criarChavePix(@RequestBody ChavePix chavePix) {
        Usuario usuario = usuarioService.findById(chavePix.getUsuario().getId());
        if (usuario != null && usuario.isAtivo()) {
            if (usuario.getChavesPix().size() >= 5) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            } else {
                ChavePix novaChavePix = chavePixService.saveChavePix(chavePix);
                return ResponseEntity.ok(novaChavePix);
            }
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }







}
