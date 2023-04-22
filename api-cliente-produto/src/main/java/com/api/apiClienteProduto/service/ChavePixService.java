package com.api.apiClienteProduto.service;

import com.api.apiClienteProduto.entity.chavePix.ChavePix;
import com.api.apiClienteProduto.entity.chavePix.ChavePixRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ChavePixService {

    @Autowired
    private ChavePixRepository repository;





    public ChavePix saveChavePix(ChavePix chavePix) {
        ChavePix novaChavePix = repository.save(chavePix);
        return novaChavePix;
    }






}
