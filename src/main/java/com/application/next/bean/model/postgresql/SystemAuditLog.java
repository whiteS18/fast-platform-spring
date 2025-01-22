package com.application.next.bean.model.postgresql;

import com.application.next.bean.entity.BaseEntity;
import io.ebean.annotation.DbComment;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * 系统审计日志表
 */
@Entity
@Table(name = "fp_system_audit_log")
@Getter
@Setter
@DbComment("系统审计日志表") // 表注释
public class SystemAuditLog extends BaseEntity {

    public enum AuditType {
        MENU_ACCESS,    // 菜单访问
        BUTTON_CLICK,   // 按钮点击
        API_CALL        // 接口调用
    }

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    @DbComment("操作类型（MENU_ACCESS: 菜单访问, BUTTON_CLICK: 按钮点击, API_CALL: 接口调用）") // 字段注释
    private AuditType auditType;

    @Column(nullable = false, length = 100)
    @DbComment("资源名称（菜单名/按钮名/接口路径）") // 字段注释
    private String resourceName;

    @Column(length = 500)
    @DbComment("资源详情（JSON 格式扩展信息）") // 字段注释
    private String resourceDetail;

    @Column(nullable = false, length = 50)
    @DbComment("操作人IP") // 字段注释
    private String operatorIp;

    @Column(nullable = false)
    @DbComment("操作人ID") // 字段注释
    private Long operatorId;

    @Column(nullable = false, length = 50)
    @DbComment("操作人姓名") // 字段注释
    private String operatorName;

    @Column(nullable = false)
    @DbComment("操作时间") // 字段注释
    private LocalDateTime operateTime = LocalDateTime.now();

    @Column(length = 1000)
    @DbComment("请求参数（JSON 格式）") // 字段注释
    private String requestParams;

    @Column(length = 1000)
    @DbComment("操作结果（成功/失败 + 详情）") // 字段注释
    private String result;
}