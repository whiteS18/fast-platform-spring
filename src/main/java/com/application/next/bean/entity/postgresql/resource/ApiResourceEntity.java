package com.application.next.bean.entity.postgresql.resource;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Comment;

import java.util.HashSet;
import java.util.Set;

/**
 * API资源实体类
 * 用于管理系统中的后端接口资源
 */
@Getter
@Setter
@Entity
@Table(name = "fp_system_api_resources")
@Comment("系统API资源表")
@DiscriminatorValue("API")
public class ApiResourceEntity extends ResourceEntity {

    @Column(name = "api_version")
    @Comment("API版本")
    private String apiVersion;
    
    @Column(name = "api_group")
    @Comment("API分组")
    private String apiGroup;
    
    @Column(name = "method", nullable = false)
    @Comment("HTTP方法：GET, POST, PUT, DELETE等")
    private String method;
    
    @Column(name = "path", nullable = false)
    @Comment("API路径")
    private String path;
    
    @ManyToMany(mappedBy = "apis")
    @Comment("使用该API的组件")
    private Set<ComponentResourceEntity> components = new HashSet<>();

    public String getApiGroup() {
        return apiGroup;
    }
    
    public void setApiGroup(String apiGroup) {
        this.apiGroup = apiGroup;
    }
} 