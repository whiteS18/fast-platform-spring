package com.application.next.service;

import com.application.next.bean.entity.postgresql.resource.ComponentResourceEntity;

import java.util.List;

/**
 * 组件资源服务接口
 */
public interface ComponentResourceService {
    
    /**
     * 保存组件资源
     * @param componentResource 组件资源实体
     * @return 保存后的组件资源实体
     */
    ComponentResourceEntity save(ComponentResourceEntity componentResource);
    
    /**
     * 根据ID查询组件资源
     * @param id 组件资源ID
     * @return 组件资源实体
     */
    ComponentResourceEntity findById(Long id);
    
    /**
     * 根据组件路径查询组件资源
     * @param path 组件路径
     * @return 组件资源实体
     */
    ComponentResourceEntity findByPath(String path);
    
    /**
     * 查询所有组件资源
     * @return 组件资源实体列表
     */
    List<ComponentResourceEntity> findAll();
    
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
    
    /**
     * 删除组件资源
     * @param id 组件资源ID
     */
    void delete(Long id);
    
    /**
     * 更新组件资源
     * @param componentResource 组件资源实体
     * @return 更新后的组件资源实体
     */
    ComponentResourceEntity update(ComponentResourceEntity componentResource);
    
    /**
     * 为组件添加API资源
     * @param componentId 组件ID
     * @param apiId API资源ID
     * @return 更新后的组件资源实体
     */
    ComponentResourceEntity addApi(Long componentId, Long apiId);
    
    /**
     * 从组件移除API资源
     * @param componentId 组件ID
     * @param apiId API资源ID
     * @return 更新后的组件资源实体
     */
    ComponentResourceEntity removeApi(Long componentId, Long apiId);
} 