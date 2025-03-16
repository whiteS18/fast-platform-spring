package com.application.next.bean.entity.postgresql.resource;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Comment;

import java.util.HashSet;
import java.util.Set;

/**
 * 组件资源实体类
 * 用于管理系统中的前端组件资源
 */
@Getter
@Setter
@Entity
@Table(name = "fp_system_component_resources")
@Comment("系统组件资源表")
@DiscriminatorValue("COMPONENT")
public class ComponentResourceEntity extends ResourceEntity {

    @Column
    @Comment("组件路径")
    private String path;
    
    @Column
    @Comment("组件类型：PAGE-页面组件，WIDGET-控件组件，BASIC-基础组件")
    private String type;
    
    @Column
    @Comment("组件图标")
    private String icon;
    
    @Column
    @Comment("组件配置(JSON格式)")
    @Lob
    private String config;
    
    @Column
    @Comment("组件模板(HTML/CSS/JS)")
    @Lob
    private String template;
    
    @Column
    @Comment("组件样式(CSS)")
    @Lob
    private String style;
    
    @Column
    @Comment("组件脚本(JS)")
    @Lob
    private String script;
    
    @Column
    @Comment("排序号")
    private Integer sort = 0;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    @Comment("父级组件ID")
    private ComponentResourceEntity parent;
    
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    @Comment("子组件列表")
    private Set<ComponentResourceEntity> children = new HashSet<>();
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "fp_component_api_resources",
        joinColumns = @JoinColumn(name = "component_id"),
        inverseJoinColumns = @JoinColumn(name = "api_id")
    )
    @Comment("组件使用的API列表")
    private Set<ApiResourceEntity> apis = new HashSet<>();
    
    @OneToMany(mappedBy = "component")
    @Comment("使用该组件的菜单")
    private Set<MenuResourceEntity> menus = new HashSet<>();
} 