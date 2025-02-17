package com.application.next.bean.postgresql;

import com.application.next.bean.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "fp_system_operation_logs")
public class OperationLog extends BaseEntity {
    @Column(nullable = false)
    private String operationType;

    @Column(nullable = false)
    private String operationContent;

    @Column(nullable = false)
    private String operationResult;

    @Column
    private String errorMessage;

    @Column(nullable = false)
    private String requestUrl;

    @Column(nullable = false)
    private String requestMethod;

    @Column
    private String requestParams;

    @Column
    private String responseData;

    @Column(nullable = false)
    private String operatorUsername;

    @Column(nullable = false)
    private String operatorIp;

    @Column(nullable = false)
    private LocalDateTime operationTime;

    @Column
    private Long executionTime;
}