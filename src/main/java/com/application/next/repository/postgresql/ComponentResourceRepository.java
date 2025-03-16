package com.application.next.repository.postgresql;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.application.next.bean.entity.postgresql.resource.ComponentResourceEntity;

import java.util.List;

/**
 * 组件资源Repository接口
 */
@Repository
public interface ComponentResourceRepository extends JpaRepository<ComponentResourceEntity, Long>, JpaSpecificationExecutor<ComponentResourceEntity> {
    
    /**
     * 根据组件路径查询组件资源
     * @param path 组件路径
     * @return 组件资源实体
     */
    ComponentResourceEntity findByPath(String path);
    
    /**
     * 根据组件类型查询组件资源列表
     * @param type 组件类型
     * @return 组件资源实体列表
     */
    List<ComponentResourceEntity> findByType(String type);
    
    /**
     * 根据父组件ID查询子组件列表
     * @param parentId 父组件ID
     * @return 子组件资源实体列表
     */
    List<ComponentResourceEntity> findByParentId(Long parentId);
    
    /**
     * 查询所有顶级组件（没有父组件的组件）
     * @return 顶级组件资源实体列表
     */
    List<ComponentResourceEntity> findByParentIsNull();
} 