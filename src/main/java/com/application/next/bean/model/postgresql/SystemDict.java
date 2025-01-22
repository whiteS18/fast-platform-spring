package com.application.next.bean.model.postgresql;

import com.application.next.bean.entity.BaseEntity;
import io.ebean.annotation.DbComment;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "fp_system_dict")
@Getter
@Setter
@DbComment("系统字典表")
public class SystemDict extends BaseEntity {

    @Column(length = 50, nullable = false)
    @DbComment("字典类型")
    private String dictType;

    @Column(length = 50, nullable = false)
    @DbComment("字典键")
    private String dictKey;

    @Column(length = 100, nullable = false)
    @DbComment("字典值")
    private String dictValue;

    @Column(nullable = false)
    @DbComment("排序号")
    private Integer sortOrder = 0;

    @Column(length = 1)
    @DbComment("状态（0-启用，1-停用）")
    private String status = "0";
}