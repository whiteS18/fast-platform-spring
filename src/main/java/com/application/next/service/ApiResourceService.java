package com.application.next.service;

import com.application.next.bean.entity.postgresql.resource.ApiResourceEntity;

import java.util.List;

/**
 * API资源服务接口
 */
public interface ApiResourceService {
    
    /**
     * 保存API资源
     * @param apiResource API资源实体
     * @return 保存后的API资源实体
     */
    ApiResourceEntity save(ApiResourceEntity apiResource);
    
    /**
     * 根据ID查询API资源
     * @param id API资源ID
     * @return API资源实体
     */
    ApiResourceEntity findById(Long id);
    
    /**
     * 根据API路径查询API资源
     * @param path API路径
     * @return API资源实体
     */
    ApiResourceEntity findByPath(String path);
    
    /**
     * 根据API路径和HTTP方法查询API资源
     * @param path API路径
     * @param method HTTP方法
     * @return API资源实体
     */
    ApiResourceEntity findByPathAndMethod(String path, String method);
    
    /**
     * 查询所有API资源
     * @return API资源实体列表
     */
    List<ApiResourceEntity> findAll();
    
    /**
     * 根据API分组查询API资源列表
     * @param apiGroup API分组
     * @return API资源实体列表
     */
    List<ApiResourceEntity> findByApiGroup(String apiGroup);
    
    /**
     * 根据API版本查询API资源列表
     * @param apiVersion API版本
     * @return API资源实体列表
     */
    List<ApiResourceEntity> findByApiVersion(String apiVersion);
    
    /**
     * 删除API资源
     * @param id API资源ID
     */
    void delete(Long id);
    
    /**
     * 更新API资源
     * @param apiResource API资源实体
     * @return 更新后的API资源实体
     */
    ApiResourceEntity update(ApiResourceEntity apiResource);
} 