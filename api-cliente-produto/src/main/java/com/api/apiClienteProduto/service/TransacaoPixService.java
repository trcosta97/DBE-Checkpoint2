package com.api.apiClienteProduto.service;

import com.api.apiClienteProduto.entity.transacaoPix.TransacaoPix;

import com.api.apiClienteProduto.entity.transacaoPix.TransacaoPixRepository;
import com.api.apiClienteProduto.entity.usuario.Usuario;
import com.api.apiClienteProduto.entity.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Service
public class TransacaoPixService {
    @Autowired
    private TransacaoPixRepository transacaoPixRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    public TransacaoPix fazerPix(TransacaoPix transacaoPix){
        Optional<Usuario> usuarioDebitoOptional = usuarioRepository.findById(transacaoPix.getUsuarioDebito().getId());
        Usuario usuarioDebito = null;
        if (usuarioDebitoOptional.isPresent()) {
            usuarioDebito = usuarioDebitoOptional.get();
        } else {
            transacaoPix.setStatus("FALHA");
            transacaoPix.setDescricao("USUARIO DÉBITO NAO ENCONTRADO");
            return transacaoPix;
        }

        Optional<Usuario> usuarioCreditoOptional = usuarioRepository.findById(transacaoPix.getUsuarioCredito().getId());
        Usuario usuarioCredito = null;
        if (usuarioCreditoOptional.isPresent()) {
            usuarioCredito = usuarioCreditoOptional.get();
        } else {
            transacaoPix.setStatus("FALHA");
            transacaoPix.setDescricao("USUARIO CRÉDITO NAO ENCONTRADO");
            return transacaoPix;
        }

        if(usuarioDebito.getSaldo().getValor() < transacaoPix.getValor()){
            transacaoPix.setStatus("FALHA");
            transacaoPix.setDescricao("SALDO INSUFICIENTE PARA TRANSAÇÃO");
        }else{
            Double novoSaldoDebito = usuarioDebito.getSaldo().getValor() - transacaoPix.getValor();
            usuarioDebito.getSaldo().setValor(novoSaldoDebito);
            Double novoSaldoCredito = usuarioCredito.getSaldo().getValor() + transacaoPix.getValor();
            usuarioCredito.getSaldo().setValor(novoSaldoCredito);
            transacaoPix.setStatus("SUCESSO");
            transacaoPix.setDescricao("TRANSAÇÃO EFETUADA");
            transacaoPix.setDataTransacao(Calendar.getInstance());
            transacaoPixRepository.save(transacaoPix);
            salvarUsuario(usuarioDebito);
            salvarUsuario(usuarioCredito);
        }
        return transacaoPix;
    }


    private void salvarUsuario(Usuario usuario) {
        usuarioRepository.save(usuario);
    }

    public List<TransacaoPix> transacoesRecebidasUltimos7Dias(Long id){
        Calendar seteDiasAtras = Calendar.getInstance();
        seteDiasAtras.add(Calendar.DAY_OF_YEAR, -7);
        return transacaoPixRepository.findAllByUsuarioCreditoIdAndDataTransacaoBetween(id, seteDiasAtras,Calendar.getInstance());

    }

    public List<TransacaoPix> transacoesEfetuadasUltimos7Dias(Long id){
        Calendar seteDiasAtras = Calendar.getInstance();
        seteDiasAtras.add(Calendar.DAY_OF_YEAR, -7);
        return transacaoPixRepository.findAllByUsuarioDebitoIdAndDataTransacaoBetween(id, seteDiasAtras,Calendar.getInstance());

    }






}
