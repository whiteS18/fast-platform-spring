package com.application.next.service;

import java.util.List;
import java.util.Set;

import com.application.next.bean.entity.postgresql.PermissionEntity;
import com.application.next.bean.entity.postgresql.resource.ResourceEntity;

/**
 * 权限服务接口
 */
public interface PermissionService {
    
    /**
     * 保存权限
     * @param permission 权限实体
     * @return 保存后的权限实体
     */
    PermissionEntity save(PermissionEntity permission);
    
    /**
     * 根据ID查询权限
     * @param id 权限ID
     * @return 权限实体
     */
    PermissionEntity findById(Long id);
    
    /**
     * 根据权限标识查询权限
     * @param permission 权限标识
     * @return 权限实体
     */
    PermissionEntity findByPermission(String permission);
    
    /**
     * 根据权限名称查询权限
     * @param name 权限名称
     * @return 权限实体
     */
    PermissionEntity findByName(String name);
    
    /**
     * 查询所有权限
     * @return 权限实体列表
     */
    List<PermissionEntity> findAll();
    
    /**
     * 删除权限
     * @param id 权限ID
     */
    void delete(Long id);
    
    /**
     * 更新权限
     * @param permission 权限实体
     * @return 更新后的权限实体
     */
    PermissionEntity update(PermissionEntity permission);
    
    /**
     * 根据资源ID查询权限列表
     * @param resourceId 资源ID
     * @return 权限实体列表
     */
    Set<PermissionEntity> findByResourceId(Long resourceId);
    
    /**
     * 根据资源类型查询权限列表
     * @param resourceType 资源类型
     * @return 权限实体列表
     */
    Set<PermissionEntity> findByResourceType(String resourceType);
    
    /**
     * 根据角色ID查询权限列表
     * @param roleId 角色ID
     * @return 权限实体列表
     */
    Set<PermissionEntity> findByRoleId(Long roleId);
} 