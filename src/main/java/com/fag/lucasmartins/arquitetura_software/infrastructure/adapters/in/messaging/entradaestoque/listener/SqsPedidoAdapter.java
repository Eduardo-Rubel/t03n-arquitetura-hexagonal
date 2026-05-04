package com.fag.lucasmartins.arquitetura_software.infrastructure.adapters.in.messaging.entradaestoque.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fag.lucasmartins.arquitetura_software.application.ports.in.service.PedidoServicePort;
import com.fag.lucasmartins.arquitetura_software.core.domain.bo.PedidoBO;
import com.fag.lucasmartins.arquitetura_software.core.domain.bo.PedidoProdutoBO;
import com.fag.lucasmartins.arquitetura_software.core.domain.bo.PessoaBO;
import com.fag.lucasmartins.arquitetura_software.core.domain.bo.ProdutoBO;
import com.fag.lucasmartins.arquitetura_software.infrastructure.adapters.in.messaging.pedido.dto.PedidoEventDTO;

import io.awspring.cloud.sqs.annotation.SqsListener;

public class SqsPedidoAdapter {

    private static final Logger log = LoggerFactory.getLogger(SqsPedidoAdapter.class);

    private final PedidoServicePort pedidoServicePort;

    public SqsPedidoAdapter(PedidoServicePort pedidoServicePort) {
        this.pedidoServicePort = pedidoServicePort;
    }

    @SqsListener("${queue.order-events}")
    public void receberMensagem(PedidoEventDTO dto) {
        log.info("Pedido recebido do SQS: customerId={} origin={}", dto.getCustomerId(), dto.getOrigin());

        PedidoBO pedidoBO = new PedidoBO();
        PessoaBO pessoaBO = new PessoaBO();
        pessoaBO.setId(dto.getCustomerId());
        pedidoBO.setPessoa(pessoaBO);
        pedidoBO.setCep(dto.getZipCode());

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

        pedidoServicePort.criarPedido(pedidoBO);
        log.info("Pedido processado com sucesso.");
    }
}
