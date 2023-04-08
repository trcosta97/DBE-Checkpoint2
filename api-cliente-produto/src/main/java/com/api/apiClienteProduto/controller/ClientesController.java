package com.api.apiClienteProduto.controller;

import com.api.apiClienteProduto.entity.cliente.Cliente;
import com.api.apiClienteProduto.entity.cliente.ClienteRepository;
import com.api.apiClienteProduto.entity.cliente.DadosAtualizacaoClienteDTO;
import com.api.apiClienteProduto.entity.cliente.DadosCadastroClienteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("clientes")
public class ClientesController {

    @Autowired
    private ClienteRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrarCliente(@RequestBody DadosCadastroClienteDTO dados){
        var cliente = new Cliente(dados);
        repository.save(cliente);
        return ResponseEntity.ok().body(dados);
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> listarClientes() {
        List<Cliente> clientes = repository.findAllByAtivoTrue();
        if (clientes.isEmpty()){
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.ok(clientes);
        }
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody DadosAtualizacaoClienteDTO dados){
        var cliente = repository.getReferenceById(dados.id());
        cliente.atualizarInformacoes(dados);
        return ResponseEntity.ok(dados);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id){
        var cliente = repository.getReferenceById(id);
        cliente.excluir();

        return ResponseEntity.noContent().build();
    }





}
