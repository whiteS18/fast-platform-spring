package com.application.next.service.impl;

import com.application.next.bean.entity.postgresql.PermissionEntity;
import com.application.next.repository.postgresql.PermissionRepository;
import com.application.next.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * 权限服务实现类
 */
@Service
@Transactional
public class PermissionServiceImpl implements PermissionService {
    
    @Autowired
    private PermissionRepository permissionRepository;
    
    @Override
    public PermissionEntity save(PermissionEntity permission) {
        return permissionRepository.save(permission);
    }
    
    @Override
    public PermissionEntity findById(Long id) {
        Optional<PermissionEntity> optionalPermission = permissionRepository.findById(id);
        return optionalPermission.orElse(null);
    }
    
    @Override
    public PermissionEntity findByPermission(String permission) {
        return permissionRepository.findByPermission(permission);
    }
    
    @Override
    public PermissionEntity findByName(String name) {
        return permissionRepository.findByName(name);
    }
    
    @Override
    public List<PermissionEntity> findAll() {
        return permissionRepository.findAll();
    }
    
    @Override
    public void delete(Long id) {
        permissionRepository.deleteById(id);
    }
    
    @Override
    public PermissionEntity update(PermissionEntity permission) {
        return permissionRepository.save(permission);
    }
    
    @Override
    public Set<PermissionEntity> findByResourceId(Long resourceId) {
        return permissionRepository.findByResourceId(resourceId);
    }
    
    @Override
    public Set<PermissionEntity> findByResourceType(String resourceType) {
        return permissionRepository.findByResourceType(resourceType);
    }
    
    @Override
    public Set<PermissionEntity> findByRoleId(Long roleId) {
        return permissionRepository.findByRoleId(roleId);
    }
} 