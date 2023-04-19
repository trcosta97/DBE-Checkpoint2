package com.api.apiClienteProduto.controller;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.api.apiClienteProduto.controller.dto.ProdutoDTO;
import com.api.apiClienteProduto.controller.dto.UsuarioDTO;
import com.api.apiClienteProduto.entity.produto.Produto;
import com.api.apiClienteProduto.entity.usuario.Usuario;
import com.api.apiClienteProduto.service.UsuarioService;

@RestController
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @PostMapping("/usuarios")
    @Transactional
    public ResponseEntity<Usuario> cadastrarUsuario(@RequestBody UsuarioDTO resource) {
        Usuario usuario = new Usuario(resource.getNome(), resource.getEmail(), resource.getNomeMae(),
                resource.getSenha(),
                resource.getTelefone(), resource.getIdade(), resource.getEndereco(), resource.getCpf(),
                resource.getRg(), resource.isPessoaPublica(), resource.getRenda(), resource.getPatrimonio());
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

    public void validarCPF(String cpf) {
        if (cpf.length() != 11) {
            throw new IllegalArgumentException("cpf inválido");
        }
    }

    public void validarEmail(String email) {
        if (!email.contains("@")) {
            throw new IllegalArgumentException("email inválido");
        }
    }

    public void validarCpfPorConta(Usuario usuario) {
        List<Usuario> usuariosPorCpf = service.UsuariosByCpf(usuario.getCpf());
        if (usuariosPorCpf.size() >= 3) {
            throw new RuntimeException("Limite de contas por CPF atingido");
        }
    }

    @GetMapping("/usuarios/all")
    public ResponseEntity<List<Usuario>> all() {
        return ResponseEntity.ok(service.getAllUsuarios());
    }

    @GetMapping("/usuarios/{id}")
    public ResponseEntity<Usuario> one(@PathVariable Long id) {
        return ResponseEntity.ofNullable(service.findById(id));
    }

    @PutMapping("/usuarios/{id}")
    @Transactional
    public ResponseEntity<Usuario> replaceUsuario(@RequestBody UsuarioDTO resource, @PathVariable Long id) {
        Usuario usuario = service.findById(id);

        if (null == usuario) {
            return ResponseEntity.notFound().build();
        }

        usuario.setNome(resource.getNome());
        usuario.setEmail(resource.getEmail());
        usuario.setNomeMae(resource.getNomeMae());
        usuario.setSenha(resource.getSenha());
        usuario.setTelefone(resource.getTelefone());
        usuario.setIdade(resource.getIdade());
        usuario.setEndereco(resource.getEndereco());
        usuario.setCpf(resource.getCpf());
        usuario.setRg(resource.getRg());
        usuario.setPessoaPublica(resource.isPessoaPublica());
        usuario.setRenda(resource.getRenda());
        usuario.setPatrimonio(resource.getPatrimonio());
        usuario.setDataCadastro(resource.getDataCadastro());
        usuario.setDataAtualizacao(Calendar.getInstance());

        // TODO: uma atualização de usuário pode atualizar os produtos desse usuário?
        // usuario.setProdutos(resource.getProdutos()); <-- isso está errado
        // Você não pode criar uma nova entidade simplesmente porque algo na entidade
        // foi alterada.
        // Você somente cria uma nova entidade quando o conceito relacionado à entidade
        // não existe.
        // Se for substituir a lista de produto por uma lista nova, ainda que os nomes
        // coincidam, recomendo usar o produto como uma collection de embbeds.
        resource.getProdutos().stream().map(ProdutoDTO::getNome).forEach(nomeProduto -> {
            if (usuario.getProdutos().stream().map(Produto::getNome).filter(nome -> nome.equals(nomeProduto)).findAny()
                    .isEmpty()) {
                usuario.addProduto(nomeProduto);
            }
        });

        // Mesma observação vale para as chaves pix
        // usuario.setChavesPix(resource.getChavesPix());

        // TODO: melhorar linha abaixo
        usuario.getSaldo().setValor(resource.getSaldo().getValor());
        usuario.setAtivo(resource.isAtivo());

        validarCPF(usuario.getCpf());
        validarEmail(usuario.getEmail());
        validarCpfPorConta(usuario);

        return ResponseEntity.ok(usuario);
    }

    @PutMapping("/usuarios/addProduto/{id}")
    @Transactional
    public ResponseEntity<Usuario> adicionarProduto(@RequestBody ProdutoDTO resource, @PathVariable Long id) {
        Usuario usuario = service.findById(id);

        if (null == usuario) {
            return ResponseEntity.notFound().build();
        }

        usuario.addProduto(resource.getNome());

        return ResponseEntity.ofNullable(usuario);
    }

    @PutMapping("/usuarios/delProduto/{id}")
    @Transactional
    public ResponseEntity<Usuario> removerProduto(@RequestBody Produto produto, @PathVariable Long id) {
        Usuario usuarioAtualizado = service.delProduto(produto, id);
        return ResponseEntity.ofNullable(usuarioAtualizado);
    }

    @DeleteMapping("/usuarios/{id}")
    public ResponseEntity<Usuario> desativarUsuario(@PathVariable Long id) {
        return ResponseEntity.ofNullable(service.desativarUsuario(id));
    }
}
