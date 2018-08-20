package com.superhakce.webblog.entity;

import lombok.Data;

import javax.persistence.Id;
import java.util.Date;
import java.util.List;

/**
 * @Author: heqingjiang
 * @Maintenance: author
 * @Description: 博客实体
 * @Date: Create in 2018/8/16 11:12
 */
@Data
public class Blog {

    @Id
    private String id;

    private String blogName;//博客标题

    private String blogContent;//博客内容

    private Long userId;//博客用户ID

    private String userName;//博客用户名称

    private Date createTime;//修改时间

    private Date updateTime;//更新时间

    private List<Commentary> commentaryList;//评论列表

}
