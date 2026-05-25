package com.fag.lucasmartins.dlq_audit_service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditMessageRepository extends JpaRepository<AuditMessage, String> {
}
