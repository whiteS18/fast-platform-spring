package com.application.next.bean.postgresql;

import com.application.next.bean.entity.BaseEntity;
import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Comment;

/**
 * 系统权限实体类
 * 用于管理系统的权限定义和权限分配
 */
@Getter
@Setter
@Entity
@Table(name = "fp_system_permissions")
@Comment("系统权限表")
public class Permission extends BaseEntity {
    @Column(unique = true, nullable = false)
    @Comment("权限名称")
    private String name;

    @Column
    @Comment("权限描述")
    private String description;

    @ManyToMany(mappedBy = "permissions")
    @Comment("关联的角色列表")
    private Set<Role> roles = new HashSet<>();
}