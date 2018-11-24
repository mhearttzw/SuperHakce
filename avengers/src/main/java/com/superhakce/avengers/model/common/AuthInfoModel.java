package com.superhakce.avengers.model.common;

import lombok.Data;

/**
 * @author           echelon
 * @email            2954632969@qq.com
 * @created_time     2018/11/24 20:50
 * @description      用户鉴权model,响应类
 */
@Data
public class AuthInfoModel {
    /**
     * 用户id-当前客户经理ID
     */
    private Long userId;

    /**
     * 用户名-当前客户经理名称
     */
    private String userName;

    /**
     * 机构id-当前机构ID
     */
    private Long organizationId;

    /**
     * 机构名-当前机构名称
     */
    private String organizationName;

    /**
     * 权限类型  (0L,"查询自己"),(1L,"查询当前机构"),(2L,"查询当前机构和子机构"),(3L,"查询所有");
     */
    private Integer DataScope;

    /**
     * 可操作机构列表以拼接
     */
    private String orgListStr;

    private String token;

    private String phone;//当前登录人的电话

    private String email;//当前登录人的邮箱


}
