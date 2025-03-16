package com.application.next.bean.entity.postgresql;

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
    
    /**
     * 操作类型（如：API调用、菜单访问、组件访问等）
     */
    @Column(nullable = false)
    private String operationType;

    /**
     * 资源名称
     */
    @Column(nullable = false)
    private String resourceName;

    /**
     * 资源路径
     */
    @Column(nullable = false)
    private String resourcePath;

    /**
     * 资源ID
     */
    @Column
    private String resourceId;

    /**
     * 操作内容描述
     */
    @Column(nullable = false)
    private String operationContent;

    /**
     * 操作结果（成功/失败）
     */
    @Column(nullable = false)
    private String operationResult;

    /**
     * 错误信息
     */
    @Column
    private String errorMessage;

    /**
     * 请求URL
     */
    @Column(nullable = false)
    private String requestUrl;

    /**
     * 请求方法
     */
    @Column(nullable = false)
    private String requestMethod;

    /**
     * 请求参数
     */
    @Column(columnDefinition = "TEXT")
    private String requestParams;

    /**
     * 响应数据
     */
    @Column
    private String responseData;

    /**
     * 操作人用户名
     */
    @Column(nullable = false)
    private String operatorUsername;

    /**
     * 操作人IP地址
     */
    @Column(nullable = false)
    private String operatorIp;

    /**
     * 操作时间
     */
    @Column(nullable = false)
    private LocalDateTime operationTime;

    /**
     * 执行时间（毫秒）
     */
    @Column
    private Long executionTime;

    /**
     * 请求头
     */
    @Column(columnDefinition = "TEXT")
    private String requestHeaders;
}