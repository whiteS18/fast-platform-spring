package com.application.next.service;

import com.application.next.bean.entity.postgresql.resource.MenuResourceEntity;

import java.util.List;

/**
 * 菜单资源服务接口
 */
public interface MenuResourceService {
    
    /**
     * 保存菜单资源
     * @param menuResource 菜单资源实体
     * @return 保存后的菜单资源实体
     */
    MenuResourceEntity save(MenuResourceEntity menuResource);
    
    /**
     * 根据ID查询菜单资源
     * @param id 菜单资源ID
     * @return 菜单资源实体
     */
    MenuResourceEntity findById(Long id);
    
    /**
     * 根据菜单路径查询菜单资源
     * @param path 菜单路径
     * @return 菜单资源实体
     */
    MenuResourceEntity findByPath(String path);
    
    /**
     * 查询所有菜单资源
     * @return 菜单资源实体列表
     */
    List<MenuResourceEntity> findAll();
    
    /**
     * 根据父菜单ID查询子菜单列表
     * @param parentId 父菜单ID
     * @return 子菜单资源实体列表
     */
    List<MenuResourceEntity> findByParentId(Long parentId);
    
    /**
     * 查询所有顶级菜单（没有父菜单的菜单）
     * @return 顶级菜单资源实体列表
     */
    List<MenuResourceEntity> findByParentIsNull();
    
    /**
     * 根据组件ID查询关联的菜单列表
     * @param componentId 组件ID
     * @return 菜单资源实体列表
     */
    List<MenuResourceEntity> findByComponentId(Long componentId);
    
    /**
     * 根据是否隐藏查询菜单列表
     * @param hidden 是否隐藏
     * @return 菜单资源实体列表
     */
    List<MenuResourceEntity> findByHidden(Boolean hidden);
    
    /**
     * 删除菜单资源
     * @param id 菜单资源ID
     */
    void delete(Long id);
    
    /**
     * 更新菜单资源
     * @param menuResource 菜单资源实体
     * @return 更新后的菜单资源实体
     */
    MenuResourceEntity update(MenuResourceEntity menuResource);
    
    /**
     * 设置菜单关联的组件
     * @param menuId 菜单ID
     * @param componentId 组件ID
     * @return 更新后的菜单资源实体
     */
    MenuResourceEntity setComponent(Long menuId, Long componentId);
} 