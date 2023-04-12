package com.api.apiClienteProduto.entity.cliente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    public Cliente saveCliente(Cliente cliente){
        List<Cliente> clienteByCpf = repository.findAllByCpf(cliente.getCpf());
        if(clienteByCpf.size()>=3){
            throw new RuntimeException("Limite de contas por CPF atingido");
        }else{
            repository.save(cliente);
            return cliente;
        }
    }

    public List<Cliente> getAllClientes(){
        return repository.findAll()
                .stream().map(Cliente::new).toList();
    }

    public Cliente findById(Long id){
        Optional<Cliente> cliente = repository.findById(id);
        if(cliente.isPresent()){
            return new Cliente(cliente.get());
        }
        return null;
    }

    public Cliente updateCliente(Cliente newCliente, Long id){
        Optional<Cliente> clienteOptional = repository.findById(id);
        if(clienteOptional.isPresent()){
            Cliente cliente = clienteOptional.get();
            cliente.setNome(newCliente.getNome());
            cliente.setEmail(newCliente.getEmail());
            cliente.setNomeMae(newCliente.getNomeMae());
            cliente.setSenha(newCliente.getSenha());
            cliente.setTelefone(newCliente.getTelefone());
            cliente.setIdade(newCliente.getIdade());
            cliente.setEndereco(newCliente.getEndereco());
            cliente.setCpf(newCliente.getCpf());
            cliente.setRg(newCliente.getRg());
            cliente.setPessoaPublica(newCliente.isPessoaPublica());
            cliente.setRenda(newCliente.getRenda());
            cliente.setPatrimonio(newCliente.getPatrimonio());
            cliente.setDataCadastro(newCliente.getDataCadastro());
            cliente.setDataAtualizacao(newCliente.getDataAtualizacao());
            cliente.setProdutos(newCliente.getProdutos());
            cliente.setAtivo(newCliente.isAtivo());
            return cliente;
        }
        return null;
    }

    public Cliente deleteCLiente(Long id){
        Optional<Cliente> clienteOptional = repository.findById(id);
        if(clienteOptional.isPresent()){
            Cliente cliente = clienteOptional.get();
            repository.delete(cliente);
            return new Cliente(cliente);
        }
        return null;
    }

    public Cliente desativarCliente(Long id){
        Optional<Cliente> clienteOptional = repository.findById(id);
        if(clienteOptional.isPresent()){
            Cliente cliente = clienteOptional.get();
            cliente.excluir();
            repository.save(cliente);
            return cliente;
        }
        return null;
    }

}
