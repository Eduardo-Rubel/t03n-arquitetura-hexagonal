package com.fag.lucasmartins.arquitetura_software.infrastructure.adapters.in.messaging.entradaestoque.mapper;

import com.fag.lucasmartins.arquitetura_software.core.domain.bo.AdicionarEstoqueBO;
import com.fag.lucasmartins.arquitetura_software.infrastructure.adapters.in.messaging.entradaestoque.dto.EntradaEstoqueDTO;

public class EntradaEstoqueDTOMapper {

    public static AdicionarEstoqueBO toBo(EntradaEstoqueDTO entradaEstoqueDTO) {
        return new AdicionarEstoqueBO(
                entradaEstoqueDTO.getProdutoId(),
                entradaEstoqueDTO.getQuantidadeAdicionada()
        );
    }
}
