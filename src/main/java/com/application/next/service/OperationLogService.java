package com.application.next.service;

import com.application.next.bean.entity.postgresql.OperationLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OperationLogService {
    /**
     * 记录操作日志
     *
     * @param operationLog 操作日志实体
     * @return 保存后的操作日志实体
     */
    OperationLog save(OperationLog operationLog);

    /**
     * 分页查询操作日志
     *
     * @param pageable 分页参数
     * @return 操作日志分页数据
     */
    Page<OperationLog> findAll(Pageable pageable);

    /**
     * 根据ID查询操作日志
     *
     * @param id 操作日志ID
     * @return 操作日志实体
     */
    OperationLog findById(Long id);

    /**
     * 根据资源ID查询操作日志
     *
     * @param resourceId 资源ID
     * @param pageable 分页参数
     * @return 操作日志分页数据
     */
    Page<OperationLog> findByResourceId(String resourceId, Pageable pageable);
} 