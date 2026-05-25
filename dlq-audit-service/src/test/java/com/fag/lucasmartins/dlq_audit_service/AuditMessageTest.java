package com.fag.lucasmartins.dlq_audit_service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AuditMessageTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void testSeverityTriageHigh() {
        String payload = "{"
                + "  \"zipCode\": \"80010000\","
                + "  \"customerId\": 1,"
                + "  \"orderItems\": ["
                + "    { \"sku\": 1, \"amount\": 60 },"
                + "    { \"sku\": 2, \"amount\": 50 }"
                + "  ],"
                + "  \"origin\": \"SQS_QUEUE\","
                + "  \"occurredAt\": \"2024-05-20T14:30:00Z\""
                + "}";
        AuditMessage message = AuditMessage.buildFromPayload(payload, "TestQueue", objectMapper);
        assertEquals("HIGH", message.getSeverity());
        assertEquals("TestQueue", message.getQueueName());
        assertEquals("PENDING_ANALYSIS", message.getStatus());
    }

    @Test
    void testSeverityTriageMediumBoundary() {
        String payload = "{"
                + "  \"zipCode\": \"80010000\","
                + "  \"customerId\": 1,"
                + "  \"orderItems\": ["
                + "    { \"sku\": 1, \"amount\": 50 }"
                + "  ],"
                + "  \"origin\": \"SQS_QUEUE\","
                + "  \"occurredAt\": \"2024-05-20T14:30:00Z\""
                + "}";
        AuditMessage message = AuditMessage.buildFromPayload(payload, "TestQueue", objectMapper);
        assertEquals("MEDIUM", message.getSeverity());
    }

    @Test
    void testSeverityTriageLow() {
        String payload = "{"
                + "  \"zipCode\": \"80010000\","
                + "  \"customerId\": 1,"
                + "  \"orderItems\": ["
                + "    { \"sku\": 1, \"amount\": 49 }"
                + "  ],"
                + "  \"origin\": \"SQS_QUEUE\","
                + "  \"occurredAt\": \"2024-05-20T14:30:00Z\""
                + "}";
        AuditMessage message = AuditMessage.buildFromPayload(payload, "TestQueue", objectMapper);
        assertEquals("LOW", message.getSeverity());
    }
}
