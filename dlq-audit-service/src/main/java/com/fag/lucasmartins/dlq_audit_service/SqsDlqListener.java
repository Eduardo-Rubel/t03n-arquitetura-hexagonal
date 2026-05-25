package com.fag.lucasmartins.dlq_audit_service;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.awspring.cloud.sqs.annotation.SqsListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
public class SqsDlqListener {

    private static final Logger log = LoggerFactory.getLogger(SqsDlqListener.class);

    private final AuditMessageRepository auditMessageRepository;
    private final ObjectMapper objectMapper;

    @Value("${queue.original}")
    private String originalQueueName;

    public SqsDlqListener(AuditMessageRepository auditMessageRepository, ObjectMapper objectMapper) {
        this.auditMessageRepository = auditMessageRepository;
        this.objectMapper = objectMapper;
    }

    @SqsListener("${queue.dlq}")
    public void listen(Message<String> message) {
        String rawPayload = message.getPayload();
        log.info("Mensagem recebida da DLQ: {}", rawPayload);

        try {
            // Build the AuditMessage (parses payload, counts items, triages severity)
            AuditMessage auditMessage = AuditMessage.buildFromPayload(rawPayload, originalQueueName, objectMapper);

            // Persist securely to DB
            AuditMessage saved = auditMessageRepository.save(auditMessage);
            log.info("Mensagem salva na auditoria com ID: {}, Prioridade: {}", saved.getErrorId(), saved.getSeverity());

        } catch (Exception e) {
            log.error("Falha ao salvar a mensagem da DLQ no banco de auditoria. Lançando erro para não remover a mensagem da fila.", e);
            throw e;
        }
    }
}
