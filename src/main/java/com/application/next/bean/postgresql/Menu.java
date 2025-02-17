package com.application.next.bean.postgresql;

import com.application.next.bean.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Comment;

import java.util.HashSet;
import java.util.Set;

/**
 * 系统菜单实体类
 * 用于管理系统的菜单结构、权限和导航信息
 */
@Getter
@Setter
@Entity
@Table(name = "fp_system_menus")
@Comment("系统菜单表")
public class Menu extends BaseEntity {
    @Column(nullable = false)
    @Comment("菜单名称")
    private String name;

    @Column
    @Comment("菜单路径")
    private String path;

    @Column
    @Comment("前端组件路径")
    private String component;

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
    private Menu parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    @Comment("子菜单列表")
    private Set<Menu> children = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "menu_permissions",
        joinColumns = @JoinColumn(name = "menu_id"),
        inverseJoinColumns = @JoinColumn(name = "permission_id")
    )
    @Comment("菜单权限列表")
    private Set<Permission> permissions = new HashSet<>();

    @Column
    @Comment("权限标识")
    private String perms;

    @Column
    @Comment("菜单类型：M=菜单 B=按钮 A=接口")
    private String type;  // M=菜单 B=按钮 A=接口
}