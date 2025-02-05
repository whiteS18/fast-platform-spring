package com.application.next.bean.model.postgresql;

import com.application.next.bean.entity.BaseEntity;
import io.ebean.annotation.DbComment;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "fp_system_menu")
@Getter
@Setter
@DbComment("系统菜单表")
public class SystemMenu extends BaseEntity {

    @Column(length = 50, nullable = false)
    @DbComment("菜单名称")
    private String menuName;

    @Column(length = 100)
    @DbComment("路由路径")
    private String path;

    @Column(length = 50)
    @DbComment("前端组件名")
    private String component;

    @Column(length = 50)
    @DbComment("菜单图标")
    private String icon;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    @DbComment("上级菜单ID")
    private SystemMenu parent;

    @Column(nullable = false)
    @DbComment("排序号")
    private Integer sortOrder = 0;

    @Column(length = 1)
    @DbComment("是否可见（0-可见，1-隐藏）")
    private String visible = "0";

    @Column(length = 1)
    @DbComment("是否外部链接（0-内部，1-外部）")
    private String isFrame = "0";

    // 添加静态方法来创建首页菜单
    public static SystemMenu createHomeMenu() {
        SystemMenu homeMenu = new SystemMenu();
        homeMenu.setMenuName("首页");
        homeMenu.setPath("/home");
        homeMenu.setComponent("Home");
        homeMenu.setIcon("home");
        homeMenu.setSortOrder(1);
        homeMenu.setVisible("0");
        homeMenu.setIsFrame("0");
        return homeMenu;
    }
}