package com.fag.lucasmartins.arquitetura_software.core.domain.bo;

public class DiminuirEstoqueBO {

    private Integer produtoId;

    private Integer quantidade;

    public DiminuirEstoqueBO(Integer produtoId, Integer quantidade) {
        this.produtoId = produtoId;
        this.quantidade = quantidade;
    }

    public Integer getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Integer produtoId) {
        this.produtoId = produtoId;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
}
