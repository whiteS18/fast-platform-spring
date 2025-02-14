package com.application.next.bean.entity;

import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Version;

import java.time.LocalDateTime;

@Getter
@Setter
public class BaseEntity {


    @Id(keyType = KeyType.Auto)
    @Column(value = "id",comment = "主键id")
    private Long id;

    @Version
    @Column(value = "version", onInsertValue = "0",comment = "乐观锁版本号")
    private Integer version;

    @Column(value = "tenant_id", tenantId = true)
    private String tenantId;

    @Column(value = "create_id")
    private Long createId;

    @Column(value = "create_name")
    private String createName;

    @Column(value = "create_time")
    private LocalDateTime createTime;

    @Column(value = "update_id")
    private Long updateId;

    @Column(value = "update_name")
    private String updateName;

    @Column(value = "update_time")
    private LocalDateTime updateTime;

    @Column(value = "delete_flag", isLogicDelete = true)
    private Integer deleteFlag;

    @Column(value = "delete_id")
    private Long deleteId;

    @Column(value = "delete_name")
    private String deleteName;

    @Column(value = "delete_time")
    private LocalDateTime deleteTime;


}
