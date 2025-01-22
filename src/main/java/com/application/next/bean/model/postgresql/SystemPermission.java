package com.application.next.bean.model.postgresql;

import com.application.next.bean.entity.BaseEntity;
import io.ebean.annotation.DbComment;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "fp_system_permission")
@Getter
@Setter
@DbComment("系统权限表")
public class SystemPermission extends BaseEntity {

    @Column(length = 50, nullable = false)
    @DbComment("权限名称")
    private String permName;

    @Column(length = 50, nullable = false, unique = true)
    @DbComment("权限编码")
    private String permCode;

    @Enumerated(EnumType.STRING)
    @Column(length = 10, nullable = false)
    @DbComment("资源类型（MENU: 菜单，BUTTON: 按钮）")
    private ResourceType resourceType;

    @Column(name = "resource_id", nullable = false)
    @DbComment("关联资源ID（菜单ID或按钮ID）")
    private Long resourceId;

    public enum ResourceType {
        MENU, BUTTON
    }
}