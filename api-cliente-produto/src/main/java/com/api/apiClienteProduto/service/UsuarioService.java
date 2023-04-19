package com.api.apiClienteProduto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.apiClienteProduto.entity.produto.Produto;
import com.api.apiClienteProduto.entity.usuario.Usuario;
import com.api.apiClienteProduto.entity.usuario.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional
    public Usuario saveUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public List<Usuario> UsuariosByCpf(String cpf) {
        return usuarioRepository.findByCpfAndAtivo(cpf, true);
    }

    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAllByAtivoTrue();
    }

    public Usuario findById(Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    public Usuario delProduto(Produto produto, Long id) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();
            if (usuario.getProdutos().contains(produto)) {
                usuario.getProdutos().remove(produto);
                usuarioRepository.save(usuario);
                return usuario;
            } else {
                throw new IllegalArgumentException();
            }
        }
        return null;
    }

    public Usuario desativarUsuario(Long id) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();
            usuario.excluir();
            usuarioRepository.save(usuario);
            return usuario;
        }
        return null;
    }
}
