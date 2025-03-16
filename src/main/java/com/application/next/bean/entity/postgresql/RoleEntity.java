package com.application.next.bean.entity.postgresql;

import com.application.next.bean.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Comment;

import java.util.HashSet;
import java.util.Set;

/**
 * 角色实体类
 * 用于管理系统中的角色定义
 */
@Getter
@Setter
@Entity
@Table(name = "fp_system_roles")
@Comment("系统角色表")
public class RoleEntity extends BaseEntity {

    @Column(nullable = false, unique = true)
    @Comment("角色代码")
    private String code;
    
    @Column(nullable = false)
    @Comment("角色名称")
    private String name;
    
    @Column
    @Comment("角色描述")
    private String description;
    
    @Column(nullable = false)
    @Comment("角色状态，1-启用，0-禁用")
    private Boolean status = true;
    
    @Column
    @Comment("数据范围(1:全部数据权限 2:自定义数据权限 3:本部门数据权限 4:本部门及以下数据权限 5:仅本人数据权限)")
    private Integer dataScope = 1;
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "fp_role_permissions",
        joinColumns = @JoinColumn(name = "role_id"),
        inverseJoinColumns = @JoinColumn(name = "permission_id")
    )
    @Comment("角色权限列表")
    private Set<PermissionEntity> permissions = new HashSet<>();
    
    @ManyToMany(mappedBy = "roles")
    @Comment("拥有该角色的用户")
    private Set<UserEntity> users = new HashSet<>();
}