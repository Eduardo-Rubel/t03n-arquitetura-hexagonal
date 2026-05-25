package com.fag.lucasmartins.dlq_audit_service;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Entity
@Table(name = "audit_messages")
public class AuditMessage {

    @Id
    @Column(name = "error_id")
    private String errorId;

    @Column(name = "queue_name")
    private String queueName;

    @Lob
    @Column(name = "payload", columnDefinition = "CLOB")
    private String payload;

    @Column(name = "timestamp")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private LocalDateTime timestamp;

    @Column(name = "status")
    private String status;

    @Column(name = "severity")
    private String severity;

    public AuditMessage() {
    }

    public AuditMessage(String errorId, String queueName, String payload, LocalDateTime timestamp, String status, String severity) {
        this.errorId = errorId;
        this.queueName = queueName;
        this.payload = payload;
        this.timestamp = timestamp;
        this.status = status;
        this.severity = severity;
    }

    public static AuditMessage buildFromPayload(String rawPayload, String queueName, ObjectMapper objectMapper) {
        AuditMessage message = new AuditMessage();
        message.setErrorId(UUID.randomUUID().toString());
        message.setQueueName(queueName);
        message.setPayload(rawPayload);
        message.setStatus("PENDING_ANALYSIS");

        int totalItemsAmount = 0;
        LocalDateTime occurredTime = null;

        try {
            JsonNode root = objectMapper.readTree(rawPayload);

            // Extract occurTime/occurredAt if present
            if (root.has("occurredAt")) {
                String occurredAtStr = root.get("occurredAt").asText();
                try {
                    occurredTime = ZonedDateTime.parse(occurredAtStr).toLocalDateTime();
                } catch (Exception e) {
                    occurredTime = LocalDateTime.parse(occurredAtStr.replace("Z", ""));
                }
            }

            // Sum order items amounts
            if (root.has("orderItems") && root.get("orderItems").isArray()) {
                for (JsonNode item : root.get("orderItems")) {
                    if (item.has("amount")) {
                        totalItemsAmount += item.get("amount").asInt();
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Erro ao processar o payload JSON para auditoria: " + e.getMessage());
        }

        if (occurredTime == null) {
            occurredTime = LocalDateTime.now();
        }
        message.setTimestamp(occurredTime);

        // Triagem de Severidade logic:
        if (totalItemsAmount > 100) {
            message.setSeverity("HIGH");
        } else if (totalItemsAmount >= 50) {
            message.setSeverity("MEDIUM");
        } else {
            message.setSeverity("LOW");
        }

        return message;
    }

    // Getters and Setters
    public String getErrorId() {
        return errorId;
    }

    public void setErrorId(String errorId) {
        this.errorId = errorId;
    }

    public String getQueueName() {
        return queueName;
    }

    public void setQueueName(String queueName) {
        this.queueName = queueName;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }
}
