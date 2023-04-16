package com.api.apiClienteProduto.controller;

import com.api.apiClienteProduto.entity.usuario.Usuario;
import com.api.apiClienteProduto.service.UsuarioService;
import com.api.apiClienteProduto.entity.produto.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UsuarioController {

    @Autowired
    private UsuarioService service;


    @PostMapping("/usuarios")
    @Transactional
    public ResponseEntity<Usuario> cadastrarUsuario(@RequestBody Usuario usuario){
        validarUsuario(usuario);
        validarCPF(usuario.getCpf());
        validarEmail(usuario.getEmail());
        validarCpfPorConta(usuario);
        service.saveUsuario(usuario);
        return ResponseEntity.ok(usuario);
    }

    private void validarUsuario(Usuario newUsuario) {
        if (newUsuario.getNome() == null || newUsuario.getNome().isEmpty()) {
            throw new IllegalArgumentException("Usuario without name");
        } else if (newUsuario.getEmail() == null || newUsuario.getEmail().isEmpty()) {
            throw new IllegalArgumentException("Usuario without email");
        } else if (newUsuario.getNomeMae() == null || newUsuario.getNomeMae().isEmpty()) {
            throw new IllegalArgumentException("Usuario without mother's name");
        } else if (newUsuario.getSenha() == null || newUsuario.getSenha().isEmpty()) {
            throw new IllegalArgumentException("Usuario without password");
        } else if (newUsuario.getTelefone() == null || newUsuario.getTelefone().isEmpty()) {
            throw new IllegalArgumentException("Usuario without phone number");
        } else if (newUsuario.getIdade() == null) {
            throw new IllegalArgumentException("Usuario without age");
        } else if (newUsuario.getEndereco() == null) {
            throw new IllegalArgumentException("Usuario without address");
        } else if (newUsuario.getCpf() == null || newUsuario.getCpf().isEmpty()) {
            throw new IllegalArgumentException("Usuario without CPF");
        } else if (newUsuario.getRg() == null || newUsuario.getRg().isEmpty()) {
            throw new IllegalArgumentException("Usuario without RG");
        } else if (newUsuario.getRenda() == null) {
            throw new IllegalArgumentException("Usuario without income");
        } else if (newUsuario.getPatrimonio() == null) {
            throw new IllegalArgumentException("Usuario without assets");
        } else if (newUsuario.getProdutos() == null || newUsuario.getProdutos().isEmpty()) {
            throw new IllegalArgumentException("Usuario without products");
        }
    }

    public void validarCPF(String cpf){
        if(cpf.length() != 11){
            throw new IllegalArgumentException("cpf inválido");
        }
    }

    public void validarEmail(String email){
        if(!email.contains("@")){
            throw new IllegalArgumentException("email inválido");
        }
    }

    public void validarCpfPorConta(Usuario usuario){
        service.usuarioByCpf(usuario);
    }

    @GetMapping("/usuarios/all")
    public ResponseEntity<List<Usuario>> all(){
        return ResponseEntity.ok(service.getAllUsuarios());
    }

    @GetMapping("/usuarios/{id}")
    public ResponseEntity<Usuario> one(@PathVariable Long id){
        return ResponseEntity.ofNullable(service.findById(id));
    }


    @PutMapping("/usuarios/{id}")
    @Transactional
    public ResponseEntity<Usuario> replaceUsuario(@RequestBody Usuario newUsuario, @PathVariable Long id){
        Usuario usuarioAtualizado = service.updateUsuario(newUsuario, id);
        validarUsuario(usuarioAtualizado);
        validarCPF(usuarioAtualizado.getCpf());
        validarEmail(usuarioAtualizado.getEmail());
        validarCpfPorConta(usuarioAtualizado);
        return ResponseEntity.ok(usuarioAtualizado);

    }

    @PutMapping("/usuarios/addProduto/{id}")
    @Transactional
    public ResponseEntity<Usuario> adicionarProduto(@RequestBody Produto produto, @PathVariable Long id){
        Usuario usuarioAtualizado = service.addProduto(produto, id);
        return ResponseEntity.ofNullable(usuarioAtualizado);
    }

    @PutMapping("/usuarios/delProduto/{id}")
    @Transactional
    public ResponseEntity<Usuario> removerProduto(@RequestBody Produto produto, @PathVariable Long id){
        Usuario usuarioAtualizado = service.delProduto(produto, id);
        return ResponseEntity.ofNullable(usuarioAtualizado);
    }



    @DeleteMapping("/usuarios/{id}")
    public ResponseEntity<Usuario> desativarUsuario(@PathVariable Long id){
        return ResponseEntity.ofNullable(service.desativarUsuario(id));
    }









}
