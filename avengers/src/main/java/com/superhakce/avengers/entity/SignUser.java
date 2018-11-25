package com.superhakce.avengers.entity;

import com.superhakce.avengers.enums.BaseEnumConverter;
import com.superhakce.avengers.enums.Sex;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * @author           echelon
 * @email            2954632969@qq.com
 * @created_time     2018/11/22 16:23
 * @description      用户实体类
 */
@Data
@Entity
public class SignUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 手机号码
     */
    @NotNull
    private String phone;

    /**
     * 密码(MD5(密码+盐))
     */
    private String password;

    /**
     * 盐
     */
    private String salt;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 个性签名
     */
    private String signature;

    /**
     * 性别
     */
    @Convert(converter = Sex.Convert.class)
    private Sex sex;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 用户唯一标识id
     */
    private String userUuid;

    /**
     * 注册时间
     */
    @Column(columnDefinition = "datetime comment '注册时间'")
    private Date createTime;

    @Column(columnDefinition = "timestamp comment '更新时间'")
    private Date updateTime;

    /**
     * 注册IP地址
     *
     * @mbg.generated
     */
    private String createIp;

    /**
     * 最后登录时间
     */
    @Column(columnDefinition = "timestamp comment '最后登录时间'")
    private Date lastLoginTime;

    /**
     * 最后登录IP地址
     */
    private String lastLoginIp;

    @PrePersist
    public void prePersist() {
        Date currentTime = new Date(System.currentTimeMillis());
        createTime = currentTime;
        updateTime = currentTime;
    }

    @PreUpdate
    public void preUpdate() {
        updateTime = new Date(System.currentTimeMillis());
    }

}
