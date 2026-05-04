package com.fag.lucasmartins.arquitetura_software.infrastructure.adapters.in.messaging.pedido.mapper;

import com.fag.lucasmartins.arquitetura_software.core.domain.bo.PedidoBO;
import com.fag.lucasmartins.arquitetura_software.core.domain.bo.PedidoProdutoBO;
import com.fag.lucasmartins.arquitetura_software.core.domain.bo.PessoaBO;
import com.fag.lucasmartins.arquitetura_software.core.domain.bo.ProdutoBO;
import com.fag.lucasmartins.arquitetura_software.infrastructure.adapters.in.messaging.pedido.dto.PedidoEventDTO;

public class PedidoEventDTOMapper {

    public static PedidoBO toBo(PedidoEventDTO dto) {
        PedidoBO pedidoBO = new PedidoBO();
        
        // Mapear pessoa
        PessoaBO pessoaBO = new PessoaBO();
        pessoaBO.setId(dto.getCustomerId());
        pedidoBO.setPessoa(pessoaBO);
        
        // Mapear CEP
        pedidoBO.setCep(dto.getZipCode());
        
        // Mapear itens do pedido
        if (dto.getOrderItems() != null) {
            for (PedidoEventDTO.OrderItem item : dto.getOrderItems()) {
                ProdutoBO produtoBO = new ProdutoBO();
                produtoBO.setId(item.getSku());
                
                PedidoProdutoBO pedidoItemBO = new PedidoProdutoBO();
                pedidoItemBO.setProduto(produtoBO);
                pedidoItemBO.setQuantidade(item.getAmount() != null ? item.getAmount() : 0);
                pedidoBO.getItens().add(pedidoItemBO);
            }
        }
        
        return pedidoBO;
    }
}
