package com.fag.lucasmartins.arquitetura_software.application.ports.out.persistence;

import com.fag.lucasmartins.arquitetura_software.core.domain.bo.ProdutoBO;

import java.util.List;

public interface ProdutoRepositoryPort {

    ProdutoBO salvar(ProdutoBO produtoBO);

    List<ProdutoBO> salvarTodos(List<ProdutoBO> produtosBO);

    ProdutoBO encontrarPorId(Integer id);

    List<ProdutoBO> encontrarPorIds(List<Integer> listaIdProduto);

}
