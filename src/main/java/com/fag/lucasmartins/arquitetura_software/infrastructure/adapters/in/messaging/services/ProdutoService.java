package com.fag.lucasmartins.arquitetura_software.infrastructure.adapters.in.messaging.services;

<<<<<<< HEAD:src/main/java/com/fag/lucasmartins/arquitetura_software/infrastructure/adapters/in/messaging/services/ProdutoService.java
=======
import com.fag.lucasmartins.arquitetura_software.application.ports.in.service.ProdutoServicePort;
import com.fag.lucasmartins.arquitetura_software.application.ports.out.persistence.ProdutoRepositoryPort;
import com.fag.lucasmartins.arquitetura_software.core.domain.bo.ProdutoBO;
>>>>>>> 0ca4be6e81a4a218adffd67f0b9c1d6bca84636e:src/main/java/com/fag/lucasmartins/arquitetura_software/application/services/ProdutoService.java
import org.springframework.stereotype.Service;

import com.fag.lucasmartins.arquitetura_software.application.ports.in.service.ProdutoServicePort;
import com.fag.lucasmartins.arquitetura_software.application.ports.out.persistence.ProdutoRepositoryPort;
import com.fag.lucasmartins.arquitetura_software.core.domain.bo.ProdutoBO;

@Service
public class ProdutoService implements ProdutoServicePort {

    private final ProdutoRepositoryPort produtoRepositoryPort;

    public ProdutoService(ProdutoRepositoryPort produtoRepositoryPort) {
        this.produtoRepositoryPort = produtoRepositoryPort;
    }

    @Override
    public ProdutoBO salvar(ProdutoBO produtoBO) {
        produtoBO.validarPrecoProdutoPremium();
        produtoBO.calcularPrecoFinalPorEstoqueBaixo();

        return produtoRepositoryPort.salvar(produtoBO);
    }
}
