package com.fag.lucasmartins.arquitetura_software.application.services;

import com.fag.lucasmartins.arquitetura_software.application.ports.in.service.EstoqueServicePort;
import com.fag.lucasmartins.arquitetura_software.application.ports.out.persistence.ProdutoRepositoryPort;
import com.fag.lucasmartins.arquitetura_software.core.domain.bo.AdicionarEstoqueBO;
import com.fag.lucasmartins.arquitetura_software.core.domain.bo.DiminuirEstoqueBO;
import com.fag.lucasmartins.arquitetura_software.core.domain.bo.ProdutoBO;
import org.springframework.stereotype.Service;

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
