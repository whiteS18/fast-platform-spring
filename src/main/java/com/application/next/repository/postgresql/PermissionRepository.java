package com.application.next.repository.postgresql;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.application.next.bean.entity.postgresql.PermissionEntity;

import java.util.Set;

/**
 * 权限Repository接口
 */
@Repository
public interface PermissionRepository extends JpaRepository<PermissionEntity, Long>, JpaSpecificationExecutor<PermissionEntity> {
    
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
     * 根据资源ID查询权限列表
     * @param resourceId 资源ID
     * @return 权限实体集合
     */
    @Query("SELECT p FROM PermissionEntity p JOIN p.resources r WHERE r.id = :resourceId")
    Set<PermissionEntity> findByResourceId(@Param("resourceId") Long resourceId);
    
    /**
     * 根据资源类型查询权限列表
     * @param resourceType 资源类型
     * @return 权限实体集合
     */
    @Query("SELECT p FROM PermissionEntity p JOIN p.resources r WHERE r.resourceType = :resourceType")
    Set<PermissionEntity> findByResourceType(@Param("resourceType") String resourceType);
    
    /**
     * 根据角色ID查询权限列表
     * @param roleId 角色ID
     * @return 权限实体集合
     */
    @Query("SELECT p FROM PermissionEntity p JOIN p.roles r WHERE r.id = :roleId")
    Set<PermissionEntity> findByRoleId(@Param("roleId") Long roleId);
} 