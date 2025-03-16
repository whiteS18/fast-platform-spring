package com.application.next.controller;

import com.application.next.bean.entity.postgresql.OperationLog;
import com.application.next.service.OperationLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/operation-logs")
@RequiredArgsConstructor
public class OperationLogController {

    private final OperationLogService operationLogService;

    @GetMapping
    public ResponseEntity<Page<OperationLog>> findAll(Pageable pageable) {
        return ResponseEntity.ok(operationLogService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OperationLog> findById(@PathVariable Long id) {
        OperationLog operationLog = operationLogService.findById(id);
        return operationLog != null ? ResponseEntity.ok(operationLog) : ResponseEntity.notFound().build();
    }

    @GetMapping("/resource/{resourceId}")
    public ResponseEntity<Page<OperationLog>> findByResourceId(
            @PathVariable String resourceId,
            Pageable pageable) {
        return ResponseEntity.ok(operationLogService.findByResourceId(resourceId, pageable));
    }

    @PostMapping
    public ResponseEntity<OperationLog> save(@RequestBody OperationLog operationLog) {
        return ResponseEntity.ok(operationLogService.save(operationLog));
    }
} 