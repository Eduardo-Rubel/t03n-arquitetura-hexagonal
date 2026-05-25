package com.fag.lucasmartins.dlq_audit_service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/audit-logs")
public class AuditController {

    private final AuditMessageRepository auditMessageRepository;
    private final ObjectMapper objectMapper;

    public AuditController(AuditMessageRepository auditMessageRepository, ObjectMapper objectMapper) {
        this.auditMessageRepository = auditMessageRepository;
        this.objectMapper = objectMapper;
    }

    @GetMapping
    public List<AuditMessage> getAllLogs() {
        return auditMessageRepository.findAll();
    }

    @PostMapping("/mock")
    public ResponseEntity<AuditMessage> mockIngest(@RequestBody String rawPayload) {
        AuditMessage auditMessage = AuditMessage.buildFromPayload(rawPayload, "T03N_eduardo_rubel", objectMapper);
        AuditMessage saved = auditMessageRepository.save(auditMessage);
        return ResponseEntity.ok(saved);
    }
}
