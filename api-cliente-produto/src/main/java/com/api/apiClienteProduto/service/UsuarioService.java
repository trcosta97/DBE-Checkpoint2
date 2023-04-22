package com.api.apiClienteProduto.service;

import com.api.apiClienteProduto.entity.chavePix.ChavePix;
import com.api.apiClienteProduto.entity.chavePix.ChavePixRepository;
import com.api.apiClienteProduto.entity.produto.ProdutoRepository;
import com.api.apiClienteProduto.entity.transacaoPix.TransacaoPix;
import com.api.apiClienteProduto.entity.transacaoPix.TransacaoPixRepository;
import com.api.apiClienteProduto.entity.usuario.Usuario;
import com.api.apiClienteProduto.entity.usuario.UsuarioRepository;
import com.api.apiClienteProduto.entity.produto.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private TransacaoPixRepository transacaoPixRepository;
    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private ChavePixRepository chavePixRepository;

    public Usuario saveUsuario(Usuario usuario) {
        Usuario usuarioPersistido = usuarioRepository.save(usuario);
        List<Produto> produtos = usuario.getProdutos();

        for (Produto produto : produtos) {
            produto.setUsuario(usuarioPersistido);
            produtoRepository.save(produto);
        }

        List<ChavePix> chaves = usuario.getChavesPix();

        for (ChavePix chave : chaves){
            chave.setUsuario(usuarioPersistido);
            chavePixRepository.save(chave);
        }

        return usuarioPersistido;
    }


    public List<Usuario> UsuariosByCpf(String cpf){
        return usuarioRepository.findByCpfAndAtivo(cpf, true);

    }



    public List<Usuario> getAllUsuarios(){
        return usuarioRepository.findAllByAtivoTrue()
                .stream().map(Usuario::new).toList();
    }

    public Usuario findById(Long id){
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        if(usuario.isPresent()){
            return new Usuario(usuario.get());
        }
        return null;
    }

    public Usuario updateUsuario(Usuario newUsuario, Long id) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
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
            usuario.setChavesPix(newUsuario.getChavesPix());
            usuario.setSaldo(newUsuario.getSaldo());
            usuario.setAtivo(newUsuario.isAtivo());
            usuarioRepository.save(newUsuario);
            return usuario;

        }
        return null;
    }

    public Usuario addProduto(Produto newProduto, Long id){
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
        if(usuarioOptional.isPresent()){
            Usuario usuario = usuarioOptional.get();
            usuario.getProdutos().add(newProduto);
            newProduto.setUsuario(usuario);
            produtoRepository.save(newProduto);
            usuarioRepository.save(usuario);
            return usuario;
        }
        return null;
    }

    public Usuario delProduto(Produto produto, Long id){
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
        if(usuarioOptional.isPresent()){
            Usuario usuario = usuarioOptional.get();
            if(usuario.getProdutos().contains(produto)){
                usuario.getProdutos().remove(produto);
                usuarioRepository.save(usuario);
                return usuario;
            }else{
                throw new IllegalArgumentException();
            }
        }
        return null;
    }


    public Usuario desativarUsuario(Long id){
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
        if(usuarioOptional.isPresent()){
            Usuario usuario = usuarioOptional.get();
            usuario.excluir();
            usuarioRepository.save(usuario);
            return usuario;
        }
        return null;
    }

    public Usuario addChavePix(ChavePix chavePix, Long id){
        Optional<Usuario> usuarioSemChave = usuarioRepository.findById(id);
        if(usuarioSemChave.isPresent()){
            Usuario usuarioComChave = usuarioSemChave.get();
            List<ChavePix> chaves = new ArrayList<>();
            chaves.add(chavePix);
            for(ChavePix chave : usuarioSemChave.get().getChavesPix()){
                chaves.add(chave);
            }
            usuarioComChave.setChavesPix(chaves);
            chavePixRepository.save(chavePix);
            return usuarioRepository.save(usuarioComChave);

        }

        return null;
    }






}
