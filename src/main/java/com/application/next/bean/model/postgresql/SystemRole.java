package com.application.next.bean.model.postgresql;

import com.application.next.bean.entity.BaseEntity;
import io.ebean.annotation.DbComment;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "fp_system_role")
@Getter
@Setter
@DbComment("系统角色表")
public class SystemRole extends BaseEntity {

    @Column(length = 50, nullable = false)
    @DbComment("角色名称")
    private String roleName;

    @Column(length = 50, nullable = false, unique = true)
    @DbComment("角色编码")
    private String roleCode;

    @Column(length = 500)
    @DbComment("角色描述")
    private String description;

    @ManyToMany
    @JoinTable(name = "fp_system_role_permission")
    @DbComment("关联的权限列表")
    private List<SystemPermission> systemPermissions;
}