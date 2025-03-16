package com.application.next.repository.postgresql;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.application.next.bean.entity.postgresql.resource.ResourceEntity;

import java.util.List;

/**
 * 资源Repository接口
 */
@Repository
public interface ResourceRepository extends JpaRepository<ResourceEntity, Long>, JpaSpecificationExecutor<ResourceEntity> {
    
    /**
     * 根据资源名称查询资源
     * @param name 资源名称
     * @return 资源实体
     */
    ResourceEntity findByName(String name);
    
    /**
     * 根据资源代码查询资源
     * @param code 资源代码
     * @return 资源实体
     */
    ResourceEntity findByCode(String code);
    
    /**
     * 根据资源类型查询资源列表
     * @param resourceType 资源类型
     * @return 资源实体列表
     */
    List<ResourceEntity> findByResourceType(String resourceType);
    
    /**
     * 根据权限ID查询拥有该权限的资源列表
     * @param permissionId 权限ID
     * @return 资源实体列表
     */
    @Query("SELECT r FROM ResourceEntity r JOIN r.permissions p WHERE p.id = :permissionId")
    List<ResourceEntity> findByPermissionId(@Param("permissionId") Long permissionId);
} 