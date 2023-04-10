package com.api.apiClienteProduto.controller;

import com.api.apiClienteProduto.entity.cliente.Cliente;
import com.api.apiClienteProduto.entity.produto.Produto;
import com.api.apiClienteProduto.entity.produto.ProdutoRepository;
import com.api.apiClienteProduto.entity.produto.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProdutoController {

    @Autowired
    private ProdutoService service;

    @PostMapping("/produtos")
    @Transactional
    public ResponseEntity<Produto> cadastrarProduto(@RequestBody Produto produto){
        validarProduto(produto);
        service.saveProduto(produto);
        return ResponseEntity.ok(produto);
    }

    private void validarProduto(Produto newProduto) {
        if(newProduto.getNome() == null || newProduto.getNome().isEmpty()){
            throw new IllegalArgumentException("Produto without name");
        }else if(newProduto.getDataCadastro() == null){
            throw new IllegalArgumentException("Produto without data de cadastro");
        }

    }

    private ResponseEntity <List<Produto>> all(){
        return ResponseEntity.ok(service.getAllProdutos());
    }

    @GetMapping("produtos/{id}")
    public ResponseEntity<Produto> replaceProduto(@RequestBody Produto newProduto, @PathVariable Long id){
        Produto produtoAtualizado = service.updateProduto(newProduto, id);
        return ResponseEntity.ofNullable(produtoAtualizado);
    }

    @DeleteMapping("produtos/{id}")
    public ResponseEntity<Produto> deleteProduto(@PathVariable Long id){
        return ResponseEntity.ofNullable(service.deleteProduto(id));
    }


}