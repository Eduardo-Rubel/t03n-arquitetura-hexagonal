package com.fag.lucasmartins.arquitetura_software.infrastructure.adapters.in.messaging.saidaestoque.listener;

<<<<<<< HEAD
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

=======
>>>>>>> 0ca4be6e81a4a218adffd67f0b9c1d6bca84636e
import com.fag.lucasmartins.arquitetura_software.application.ports.in.service.EstoqueServicePort;
import com.fag.lucasmartins.arquitetura_software.core.domain.bo.DiminuirEstoqueBO;
import com.fag.lucasmartins.arquitetura_software.infrastructure.adapters.in.messaging.entradaestoque.exceptions.ConsumerSQSException;
import com.fag.lucasmartins.arquitetura_software.infrastructure.adapters.in.messaging.saidaestoque.dto.SaidaEstoqueEventDTO;
import com.fag.lucasmartins.arquitetura_software.infrastructure.adapters.in.messaging.saidaestoque.mapper.SaidaEstoqueDTOMapper;
<<<<<<< HEAD
=======
import io.awspring.cloud.sqs.annotation.SqsListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
>>>>>>> 0ca4be6e81a4a218adffd67f0b9c1d6bca84636e

@Component
public class SaidaEstoqueSqsAdapter {

    private static final Logger log = LoggerFactory.getLogger(SaidaEstoqueSqsAdapter.class);

    private final EstoqueServicePort estoqueServicePort;

    public SaidaEstoqueSqsAdapter(EstoqueServicePort estoqueServicePort) {
        this.estoqueServicePort = estoqueServicePort;
    }

    //@SqsListener(value = "${aws.sqs.queue.saida-estoque}")
    public void receberMensagem(SaidaEstoqueEventDTO evento) {
        try {
            log.info("Evento de saida de estoque recebido para o produto {}", evento.getProdutoId());

            final DiminuirEstoqueBO command = SaidaEstoqueDTOMapper.toCommand(evento);
            estoqueServicePort.diminuirEstoque(command);

            log.info("Saida de estoque processada para o produto {}", evento.getProdutoId());
        } catch (Exception e) {
            log.error("Erro ao processar o evento de saida do estoque para o produto {}", evento.getProdutoId(), e);
            throw new ConsumerSQSException("erro ao processar o evento de saida do estoque para o produto " + evento.getProdutoId(), e);
        }
    }
}
