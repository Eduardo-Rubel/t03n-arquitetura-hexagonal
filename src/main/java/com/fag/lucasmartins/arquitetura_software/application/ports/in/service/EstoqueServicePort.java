package com.fag.lucasmartins.arquitetura_software.application.ports.in.service;

import com.fag.lucasmartins.arquitetura_software.core.domain.bo.AdicionarEstoqueBO;
import com.fag.lucasmartins.arquitetura_software.core.domain.bo.DiminuirEstoqueBO;

public interface EstoqueServicePort {

    void adicinarEstoque(AdicionarEstoqueBO command);

    void diminuirEstoque(DiminuirEstoqueBO command);
}
