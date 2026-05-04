package com.fag.lucasmartins.arquitetura_software.core.domain.bo;

public class AdicionarEstoqueBO {

    private Integer produtoId;

    private Integer quantidade;

    public AdicionarEstoqueBO(Integer produtoId, Integer quantidade) {
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
