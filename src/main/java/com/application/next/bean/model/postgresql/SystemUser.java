package com.application.next.bean.model.postgresql;

import com.application.next.bean.entity.BaseEntity;
import io.ebean.annotation.DbComment;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;

@Entity
@Table(name = "fp_system_user")
@Getter
@Setter
@DbComment("系统用户表")
public class SystemUser extends BaseEntity {

    @Column(length = 50, unique = true, nullable = false)
    @DbComment("用户名")
    private String username;

    @Column(nullable = false)
    @DbComment("密码")
    private String password;

    @Column(length = 20)
    @DbComment("手机号")
    private String phone;

    @Column(length = 50)
    @DbComment("邮箱")
    private String email;

    @ManyToMany
    @JoinTable(name = "fp_system_user_role")
    @DbComment("关联的角色列表")
    private List<SystemRole> roles;

    @ManyToOne
    @JoinColumn(name = "dept_id")
    @DbComment("所属部门ID")
    private SystemDepartment systemDepartment;

    @Enumerated(EnumType.STRING)
    @Column(length = 10)
    @DbComment("状态（ENABLED: 启用，DISABLED: 禁用）")
    private Status status = Status.ENABLED;

    public enum Status {
        ENABLED, DISABLED
    }

    // 添加静态方法来创建超级管理员
    public static SystemUser createSuperAdmin() {
        SystemUser superAdmin = new SystemUser();
        superAdmin.setUsername("superadmin");
        superAdmin.setPassword("superadmin"); // 注意：实际应用中需要对密码进行加密存储
        superAdmin.setPhone("13800138000");
        superAdmin.setEmail("admin@example.com");
        superAdmin.setStatus(Status.ENABLED);
        // 假设超级管理员的角色ID为1
        SystemRole adminRole = new SystemRole();
        adminRole.setId(1L);
        superAdmin.setRoles(Arrays.asList(adminRole));
        return superAdmin;
    }
}