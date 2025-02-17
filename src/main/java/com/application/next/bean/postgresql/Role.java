package com.application.next.bean.postgresql;

import com.application.next.bean.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Comment;

import java.util.HashSet;
import java.util.Set;

/**
 * 系统角色实体类
 * 用于管理系统中的角色定义及其与用户、权限的关联关系
 */
@Getter
@Setter
@Entity
@Table(name = "fp_system_roles")
@Comment("系统角色表")
public class Role extends BaseEntity {
    @Column(unique = true, nullable = false)
    @Comment("角色名称")
    private String name;

    @Column
    @Comment("角色描述")
    private String description;

    @ManyToMany(mappedBy = "roles")
    @Comment("关联的用户列表")
    private Set<User> users = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "role_permissions",
        joinColumns = @JoinColumn(name = "role_id"),
        inverseJoinColumns = @JoinColumn(name = "permission_id")
    )
    @Comment("角色拥有的权限列表")
    private Set<Permission> permissions = new HashSet<>();
}