package com.api.apiClienteProduto.entity.produto;

import com.api.apiClienteProduto.entity.cliente.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository repository;

    public Produto saveProduto(Produto produto){
        repository.save(produto);
        return produto;
    }

    public List<Produto> getAllProdutos(){
        return repository.findAllByAtivoTrue()
                .stream().map(entity -> new Produto(entity)).toList();
    }


    public Produto findById(Long id){
        Optional<Produto> produto = repository.findById(id);
        if(produto.isPresent()){
            return new Produto(produto.get());
        }
        return null;
    }

    public Produto updateProduto(Produto newProduto, Long id){
        Optional<Produto> produtoOptional = repository.findById(id);
        if(produtoOptional.isPresent()){
            Produto produto = produtoOptional.get();
            produto.setNome(newProduto.getNome());
            produto.setDataCadastro(newProduto.getDataCadastro());
            produto.setDataAtualizacao(newProduto.getDataAtualizacao());
            produto.setAtivo(newProduto.isAtivo());
        }
        return null;
    }

    public Produto desativarProduto(Long id){
        Optional<Produto> produtoOptional = repository.findById(id);
        if(produtoOptional.isPresent()){
            Produto produto = produtoOptional.get();
            produto.excluir();
            repository.save(produto);
            return produto;
        }
        return null;
    }
}
