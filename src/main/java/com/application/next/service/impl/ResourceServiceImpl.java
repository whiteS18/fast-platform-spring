package com.application.next.service.impl;

import com.application.next.bean.entity.postgresql.PermissionEntity;
import com.application.next.bean.entity.postgresql.resource.ResourceEntity;
import com.application.next.repository.postgresql.PermissionRepository;
import com.application.next.repository.postgresql.ResourceRepository;
import com.application.next.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * 资源服务实现类
 */
@Service
@Transactional
public class ResourceServiceImpl implements ResourceService {
    
    @Autowired
    private ResourceRepository resourceRepository;
    
    @Autowired
    private PermissionRepository permissionRepository;
    
    @Override
    public ResourceEntity save(ResourceEntity resource) {
        return resourceRepository.save(resource);
    }
    
    @Override
    public ResourceEntity findById(Long id) {
        Optional<ResourceEntity> optionalResource = resourceRepository.findById(id);
        return optionalResource.orElse(null);
    }
    
    @Override
    public ResourceEntity findByName(String name) {
        return resourceRepository.findByName(name);
    }
    
    @Override
    public ResourceEntity findByCode(String code) {
        return resourceRepository.findByCode(code);
    }
    
    @Override
    public List<ResourceEntity> findAll() {
        return resourceRepository.findAll();
    }
    
    @Override
    public List<ResourceEntity> findByResourceType(String resourceType) {
        return resourceRepository.findByResourceType(resourceType);
    }
    
    @Override
    public void delete(Long id) {
        resourceRepository.deleteById(id);
    }
    
    @Override
    public ResourceEntity update(ResourceEntity resource) {
        return resourceRepository.save(resource);
    }
    
    @Override
    public ResourceEntity addPermission(Long resourceId, Long permissionId) {
        ResourceEntity resource = findById(resourceId);
        if (resource != null) {
            Optional<PermissionEntity> optionalPermission = permissionRepository.findById(permissionId);
            if (optionalPermission.isPresent()) {
                PermissionEntity permission = optionalPermission.get();
                resource.getPermissions().add(permission);
                return resourceRepository.save(resource);
            }
        }
        return null;
    }
    
    @Override
    public ResourceEntity removePermission(Long resourceId, Long permissionId) {
        ResourceEntity resource = findById(resourceId);
        if (resource != null) {
            Optional<PermissionEntity> optionalPermission = permissionRepository.findById(permissionId);
            if (optionalPermission.isPresent()) {
                PermissionEntity permission = optionalPermission.get();
                resource.getPermissions().remove(permission);
                return resourceRepository.save(resource);
            }
        }
        return null;
    }
    
    @Override
    public Set<PermissionEntity> getPermissions(Long resourceId) {
        ResourceEntity resource = findById(resourceId);
        return resource != null ? resource.getPermissions() : new HashSet<>();
    }
    
    @Override
    public List<ResourceEntity> findByPermissionId(Long permissionId) {
        return resourceRepository.findByPermissionId(permissionId);
    }
} 