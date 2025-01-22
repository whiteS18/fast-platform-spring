package com.application.next.bean.model.postgresql;

import com.application.next.bean.entity.BaseEntity;
import io.ebean.annotation.DbComment;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "fp_system_department")
@Getter
@Setter
@DbComment("系统部门表")
public class SystemDepartment extends BaseEntity {

    @Column(length = 50, nullable = false)
    @DbComment("部门名称")
    private String deptName;

    @Column(length = 50)
    @DbComment("部门编码")
    private String deptCode;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    @DbComment("上级部门ID")
    private SystemDepartment parent;

    @Column(length = 500)
    @DbComment("所有祖先部门ID路径（如 1,2,3）")
    private String ancestors;

    @Column(nullable = false)
    @DbComment("排序号")
    private Integer sortOrder = 0;
}