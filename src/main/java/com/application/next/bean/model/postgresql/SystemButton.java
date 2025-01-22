package com.application.next.bean.model.postgresql;

import com.application.next.bean.entity.BaseEntity;
import io.ebean.annotation.DbComment;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "fp_system_button")
@Getter
@Setter
@DbComment("系统按钮表")
public class SystemButton extends BaseEntity {

    @Column(length = 50, nullable = false)
    @DbComment("按钮名称")
    private String btnName;

    @Column(length = 50, nullable = false)
    @DbComment("按钮编码")
    private String btnCode;

    @ManyToOne
    @JoinColumn(name = "menu_id", nullable = false)
    @DbComment("所属菜单ID")
    private SystemMenu menu;

    @Column(length = 500)
    @DbComment("备注")
    private String remark;
}