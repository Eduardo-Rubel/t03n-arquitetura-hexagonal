<<<<<<< HEAD:src/main/java/com/fag/lucasmartins/arquitetura_software/infrastructure/adapters/in/messaging/services/EstoqueService.java
package com.fag.lucasmartins.arquitetura_software.infrastructure.adapters.in.messaging.services;

import org.springframework.stereotype.Service;
=======
package com.fag.lucasmartins.arquitetura_software.application.services;
>>>>>>> 0ca4be6e81a4a218adffd67f0b9c1d6bca84636e:src/main/java/com/fag/lucasmartins/arquitetura_software/application/services/EstoqueService.java

import com.fag.lucasmartins.arquitetura_software.application.ports.in.service.EstoqueServicePort;
import com.fag.lucasmartins.arquitetura_software.application.ports.out.persistence.ProdutoRepositoryPort;
import com.fag.lucasmartins.arquitetura_software.core.domain.bo.AdicionarEstoqueBO;
import com.fag.lucasmartins.arquitetura_software.core.domain.bo.DiminuirEstoqueBO;
import com.fag.lucasmartins.arquitetura_software.core.domain.bo.ProdutoBO;
<<<<<<< HEAD:src/main/java/com/fag/lucasmartins/arquitetura_software/infrastructure/adapters/in/messaging/services/EstoqueService.java
=======
import org.springframework.stereotype.Service;
>>>>>>> 0ca4be6e81a4a218adffd67f0b9c1d6bca84636e:src/main/java/com/fag/lucasmartins/arquitetura_software/application/services/EstoqueService.java

@Service
public class EstoqueService implements EstoqueServicePort {

    private final ProdutoRepositoryPort produtoRepositoryPort;

    public EstoqueService(ProdutoRepositoryPort produtoRepositoryPort) {
        this.produtoRepositoryPort = produtoRepositoryPort;
    }

    @Override
    public void adicinarEstoque(AdicionarEstoqueBO bo) {
        final ProdutoBO produtoBO = produtoRepositoryPort.encontrarPorId(bo.getProdutoId());

        produtoBO.adicionarEstoque(bo.getQuantidade());

        produtoRepositoryPort.salvar(produtoBO);
    }

    @Override
    public void diminuirEstoque(DiminuirEstoqueBO bo) {
        final ProdutoBO produtoBO = produtoRepositoryPort.encontrarPorId(bo.getProdutoId());

        produtoBO.diminuirEstoque(bo.getQuantidade());

        produtoRepositoryPort.salvar(produtoBO);
    }
}
