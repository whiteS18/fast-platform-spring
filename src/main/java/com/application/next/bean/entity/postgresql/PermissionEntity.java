package com.application.next.bean.entity.postgresql;

import com.application.next.bean.entity.BaseEntity;
import com.application.next.bean.entity.postgresql.resource.ResourceEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Comment;

import java.util.HashSet;
import java.util.Set;

/**
 * 权限实体类
 * 用于管理系统中的权限定义
 */
@Getter
@Setter
@Entity
@Table(name = "fp_system_permissions")
@Comment("系统权限表")
public class PermissionEntity extends BaseEntity {

    @Column(nullable = false, unique = true)
    @Comment("权限标识")
    private String permission;
    
    @Column(nullable = false)
    @Comment("权限名称")
    private String name;
    
    @Column
    @Comment("权限描述")
    private String description;
    
    @Column
    @Comment("权限状态，1-启用，0-禁用")
    private Boolean status = true;
    
    @ManyToMany(mappedBy = "permissions")
    @Comment("拥有该权限的资源")
    private Set<ResourceEntity> resources = new HashSet<>();
    
    @ManyToMany(mappedBy = "permissions")
    @Comment("拥有该权限的角色")
    private Set<RoleEntity> roles = new HashSet<>();
    

}