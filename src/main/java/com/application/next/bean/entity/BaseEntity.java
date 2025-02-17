package com.application.next.bean.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
public class BaseEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Version
    @Column(name = "version", nullable = false)
    private Integer version;

//    @Column(name = "tenant_id", nullable = false)
//    private String tenantId;

    @Column(name = "create_id")
    private Long createId;

    @Column(name = "create_name")
    private String createName;

    @Column(name = "create_time", nullable = false)
    private LocalDateTime createTime;

    @Column(name = "update_id")
    private Long updateId;

    @Column(name = "update_name")
    private String updateName;

    @Column(name = "update_time")
    private LocalDateTime updateTime;

    @Column(name = "delete_flag", nullable = false)
    private Integer deleteFlag;

    @Column(name = "delete_id")
    private Long deleteId;

    @Column(name = "delete_name")
    private String deleteName;

    @Column(name = "delete_time")
    private LocalDateTime deleteTime;


}
