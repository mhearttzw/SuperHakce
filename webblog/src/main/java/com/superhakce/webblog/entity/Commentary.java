package com.superhakce.webblog.entity;

import lombok.Data;

import java.util.Date;

/**
 * @Author: heqingjiang
 * @Maintenance: author
 * @Description: 评论实体
 * @Date: Create in 2018/8/16 11:06
 */
@Data
public class Commentary {

    private Long userId;//评论人ID

    private Long contentID;//评论序号

    private String userName;//评论人名称

    private String content;//评论内容

    private Date contentTime;//评论时间

}
