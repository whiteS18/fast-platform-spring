package com.application.next.bean.entity.postgresql;

import com.application.next.bean.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Comment;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * 用户实体类
 * 用于管理系统中的用户信息
 */
@Getter
@Setter
@Entity
@Table(name = "fp_system_users")
@Comment("系统用户表")
public class UserEntity extends BaseEntity {

    @Column(nullable = false, unique = true)
    @Comment("用户名")
    private String username;
    
    @Column(nullable = false)
    @Comment("密码")
    private String password;
    
    @Column
    @Comment("昵称")
    private String nickname;
    
    @Column
    @Comment("真实姓名")
    private String realName;
    
    @Column
    @Comment("头像")
    private String avatar;
    
    @Column
    @Comment("性别：0-未知，1-男，2-女")
    private Integer gender;
    
    @Column
    @Comment("邮箱")
    private String email;
    
    @Column
    @Comment("手机号")
    private String mobile;
    
    @Column(nullable = false)
    @Comment("账号状态：0-禁用，1-启用")
    private Boolean status = true;
    
    @Column
    @Comment("最后登录IP")
    private String loginIp;
    
    @Column
    @Comment("最后登录时间")
    private LocalDateTime loginDate;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    @Comment("所属部门")
    private DepartmentEntity department;
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "fp_user_roles",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    @Comment("用户角色列表")
    private Set<RoleEntity> roles = new HashSet<>();
    
    /**
     * 获取用户的所有权限
     * @return 权限集合
     */
    @Transient
    public Set<PermissionEntity> getAllPermissions() {
        Set<PermissionEntity> allPermissions = new HashSet<>();
        for (RoleEntity role : this.roles) {
            if (Boolean.TRUE.equals(role.getStatus())) {
                allPermissions.addAll(role.getPermissions());
            }
        }
        return allPermissions;
    }
}