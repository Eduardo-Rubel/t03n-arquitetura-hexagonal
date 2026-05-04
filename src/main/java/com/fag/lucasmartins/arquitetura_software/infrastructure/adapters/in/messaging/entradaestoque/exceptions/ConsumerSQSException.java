package com.fag.lucasmartins.arquitetura_software.infrastructure.adapters.in.messaging.entradaestoque.exceptions;

public class ConsumerSQSException extends RuntimeException {

    public ConsumerSQSException(String s, Exception e) {
        super(s, e);
    }
}
