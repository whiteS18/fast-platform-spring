package com.application.next.bean.entity.postgresql;

import com.application.next.bean.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Comment;

import java.util.HashSet;
import java.util.Set;

/**
 * 部门实体类
 * 用于管理系统中的部门信息
 */
@Getter
@Setter
@Entity
@Table(name = "fp_system_departments")
@Comment("系统部门表")
public class DepartmentEntity extends BaseEntity {

    @Column(nullable = false)
    @Comment("部门名称")
    private String name;
    
    @Column
    @Comment("部门编码")
    private String code;
    
    @Column
    @Comment("部门描述")
    private String description;
    
    @Column
    @Comment("排序号")
    private Integer sort = 0;
    
    @Column(nullable = false)
    @Comment("部门状态：0-禁用，1-启用")
    private Boolean status = true;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    @Comment("父级部门")
    private DepartmentEntity parent;
    
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    @Comment("子部门列表")
    private Set<DepartmentEntity> children = new HashSet<>();
    
    @OneToMany(mappedBy = "department")
    @Comment("部门用户列表")
    private Set<UserEntity> users = new HashSet<>();
    
    /**
     * 添加子部门
     * @param child 子部门
     */
    public void addChild(DepartmentEntity child) {
        children.add(child);
        child.setParent(this);
    }
    
    /**
     * 移除子部门
     * @param child 子部门
     */
    public void removeChild(DepartmentEntity child) {
        children.remove(child);
        child.setParent(null);
    }
}