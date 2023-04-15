package com.api.apiClienteProduto.service;

import com.api.apiClienteProduto.entity.usuario.Usuario;
import com.api.apiClienteProduto.entity.usuario.UsuarioRepository;
import com.api.apiClienteProduto.entity.produto.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    public Usuario saveUsuario(Usuario usuario){
        List<Usuario> usuarioByCpf = repository.findAllByCpf(usuario.getCpf());
        if(usuarioByCpf.size()>=3){
            throw new RuntimeException("Limite de contas por CPF atingido");
        }else{
            repository.save(usuario);
            return usuario;
        }
    }

    public List<Usuario> getAllUsuarios(){
        return repository.findAllByAtivoTrue()
                .stream().map(Usuario::new).toList();
    }

    public Usuario findById(Long id){
        Optional<Usuario> usuario = repository.findById(id);
        if(usuario.isPresent()){
            return new Usuario(usuario.get());
        }
        return null;
    }

    public Usuario updateUsuario(Usuario newUsuario, Long id) {
        Optional<Usuario> usuarioOptional = repository.findById(id);
        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();
            usuario.setNome(newUsuario.getNome());
            usuario.setEmail(newUsuario.getEmail());
            usuario.setNomeMae(newUsuario.getNomeMae());
            usuario.setSenha(newUsuario.getSenha());
            usuario.setTelefone(newUsuario.getTelefone());
            usuario.setIdade(newUsuario.getIdade());
            usuario.setEndereco(newUsuario.getEndereco());
            usuario.setCpf(newUsuario.getCpf());
            usuario.setRg(newUsuario.getRg());
            usuario.setPessoaPublica(newUsuario.isPessoaPublica());
            usuario.setRenda(newUsuario.getRenda());
            usuario.setPatrimonio(newUsuario.getPatrimonio());
            usuario.setDataCadastro(newUsuario.getDataCadastro());
            usuario.setDataAtualizacao(Calendar.getInstance());
            usuario.setProdutos(newUsuario.getProdutos());
            usuario.setAtivo(newUsuario.isAtivo());
            repository.save(newUsuario);
            return usuario;

        }
        return null;
    }

    public Usuario addProduto(Produto newProduto, Long id){
        Optional<Usuario> usuarioOptional = repository.findById(id);
        if(usuarioOptional.isPresent()){
            Usuario usuario = usuarioOptional.get();
            usuario.getProdutos().add(newProduto);
            repository.save(usuario);
            return usuario;
        }
        return null;
    }

    public Usuario delProduto(Produto produto, Long id){
        Optional<Usuario> usuarioOptional = repository.findById(id);
        if(usuarioOptional.isPresent()){
            Usuario usuario = usuarioOptional.get();
            if(usuario.getProdutos().contains(produto)){
                usuario.getProdutos().remove(produto);
                repository.save(usuario);
                return usuario;
            }else{
                throw new IllegalArgumentException();
            }
        }
        return null;
    }




    public Usuario desativarUsuario(Long id){
        Optional<Usuario> usuarioOptional = repository.findById(id);
        if(usuarioOptional.isPresent()){
            Usuario usuario = usuarioOptional.get();
            usuario.excluir();
            repository.save(usuario);
            return usuario;
        }
        return null;
    }

}
