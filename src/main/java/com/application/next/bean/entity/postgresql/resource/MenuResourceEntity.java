package com.application.next.bean.entity.postgresql.resource;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Comment;

import java.util.HashSet;
import java.util.Set;

/**
 * 菜单资源实体类
 * 用于管理系统中的菜单资源
 */
@Getter
@Setter
@Entity
@Table(name = "fp_system_menu_resources")
@Comment("系统菜单资源表")
@DiscriminatorValue("MENU")
public class MenuResourceEntity extends ResourceEntity {

    @Column
    @Comment("菜单路径")
    private String path;
    
    @Column
    @Comment("菜单图标")
    private String icon;
    
    @Column
    @Comment("排序号")
    private Integer sort = 0;
    
    @Column
    @Comment("是否隐藏")
    private Boolean hidden = false;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    @Comment("父级菜单ID")
    private MenuResourceEntity parent;
    
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    @Comment("子菜单列表")
    private Set<MenuResourceEntity> children = new HashSet<>();
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "component_id")
    @Comment("关联的组件ID")
    private ComponentResourceEntity component;
} 