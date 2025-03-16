package com.application.next.repository.postgresql;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.application.next.bean.entity.postgresql.resource.MenuResourceEntity;

import java.util.List;

/**
 * 菜单资源Repository接口
 */
@Repository
public interface MenuResourceRepository extends JpaRepository<MenuResourceEntity, Long>, JpaSpecificationExecutor<MenuResourceEntity> {
    
    /**
     * 根据菜单路径查询菜单资源
     * @param path 菜单路径
     * @return 菜单资源实体
     */
    MenuResourceEntity findByPath(String path);
    
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
} 