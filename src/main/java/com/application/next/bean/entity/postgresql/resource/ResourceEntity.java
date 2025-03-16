package com.application.next.bean.entity.postgresql.resource;

import com.application.next.bean.entity.BaseEntity;
import com.application.next.bean.entity.postgresql.PermissionEntity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Comment;

import java.util.HashSet;
import java.util.Set;

/**
 * 资源实体类
 * 作为系统中各类资源的基础类
 */
@Getter
@Setter
@Entity
@Table(name = "fp_system_resources")
@Comment("系统资源表")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "resource_type", discriminatorType = DiscriminatorType.STRING)
public class ResourceEntity extends BaseEntity {

    @Column(nullable = false)
    @Comment("资源名称")
    private String name;
    
    @Column
    @Comment("资源代码")
    private String code;
    
    @Column
    @Comment("资源描述")
    private String description;
    
    @Column(nullable = false)
    @Comment("资源状态，true-启用，false-禁用")
    private Boolean status = true;
    
    @Column(name = "resource_type", insertable = false, updatable = false)
    @Comment("资源类型：MENU-菜单，COMPONENT-组件，API-接口")
    private String resourceType;
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "fp_resource_permissions",
        joinColumns = @JoinColumn(name = "resource_id"),
        inverseJoinColumns = @JoinColumn(name = "permission_id")
    )
    @Comment("资源权限列表")
    private Set<PermissionEntity> permissions = new HashSet<>();
} 