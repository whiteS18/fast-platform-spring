package com.application.next.bean.postgresql;

import com.application.next.bean.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Comment;

import java.util.HashSet;
import java.util.Set;

/**
 * 系统用户实体类
 * 用于管理系统中的用户账号信息及其与角色的关联关系
 */
@Getter
@Setter
@Entity
@Table(name = "fp_system_users")
@Comment("系统用户表")
public class User extends BaseEntity {
    @Column(unique = true, nullable = false)
    @Comment("用户名")
    private String username;

    @Column(nullable = false)
    @Comment("密码")
    private String password;

    @Column(unique = true)
    @Comment("电子邮箱")
    private String email;

    @Column
    @Comment("账号是否启用")
    private boolean enabled = true;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "user_roles",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    @Comment("用户拥有的角色列表")
    private Set<Role> roles = new HashSet<>();
}