package com.application.next.service.impl;

import com.application.next.bean.entity.postgresql.OperationLog;
import com.application.next.repository.postgresql.OperationLogRepository;
import com.application.next.service.OperationLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OperationLogServiceImpl implements OperationLogService {

    private final OperationLogRepository operationLogRepository;

    @Override
    @Transactional
    public OperationLog save(OperationLog operationLog) {
        return operationLogRepository.save(operationLog);
    }

    @Override
    public Page<OperationLog> findAll(Pageable pageable) {
        return operationLogRepository.findAll(pageable);
    }

    @Override
    public OperationLog findById(Long id) {
        return operationLogRepository.findById(id).orElse(null);
    }

    @Override
    public Page<OperationLog> findByResourceId(String resourceId, Pageable pageable) {
        return operationLogRepository.findAll((root, query, cb) -> 
            cb.equal(root.get("resourceId"), resourceId), pageable);
    }
} 