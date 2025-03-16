package com.application.next.repository.postgresql;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.application.next.bean.entity.postgresql.resource.ApiResourceEntity;

import java.util.List;

/**
 * API资源Repository接口
 */
@Repository
public interface ApiResourceRepository extends JpaRepository<ApiResourceEntity, Long>, JpaSpecificationExecutor<ApiResourceEntity> {
    
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
}