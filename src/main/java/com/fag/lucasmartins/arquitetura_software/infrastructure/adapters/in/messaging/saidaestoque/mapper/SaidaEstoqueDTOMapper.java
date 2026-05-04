package com.fag.lucasmartins.arquitetura_software.infrastructure.adapters.in.messaging.saidaestoque.mapper;

import com.fag.lucasmartins.arquitetura_software.core.domain.bo.DiminuirEstoqueBO;
import com.fag.lucasmartins.arquitetura_software.infrastructure.adapters.in.messaging.saidaestoque.dto.SaidaEstoqueEventDTO;

public class SaidaEstoqueDTOMapper {

    public static DiminuirEstoqueBO toCommand(SaidaEstoqueEventDTO saidaEstoqueEventDTO) {
        return new DiminuirEstoqueBO(
                saidaEstoqueEventDTO.getProdutoId(),
                saidaEstoqueEventDTO.getQuantidadeAdicionada()
        );
    }
}
