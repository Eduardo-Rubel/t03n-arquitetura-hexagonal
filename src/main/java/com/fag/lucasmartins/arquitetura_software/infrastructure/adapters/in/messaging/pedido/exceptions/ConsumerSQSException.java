package com.fag.lucasmartins.arquitetura_software.infrastructure.adapters.in.messaging.pedido.exceptions;

public class ConsumerSQSException extends RuntimeException {

    public ConsumerSQSException(String s, Exception e) {
        super(s, e);
    }
}
