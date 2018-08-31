package com.superhakce.webblog.service;

import com.superhakce.webblog.entity.Blog;

/**
 * @Author: heqingjiang
 * @Maintenance: author
 * @Description: 博客服务
 * @Date: Create in 2018/8/16 12:06
 */
public interface BlogService {

    /**
     * 保存博客
     * @param blog       博客
     * @throws Exception SQLException
     */
    void saveBlog(Blog blog) throws Exception;

    /**
     *  根据标题查询博客
     * @param name      博客名称
     * @return          Blog
     */
    Blog findByBlogName(String name) throws Exception;

    /**
     *  根据ID查询博客
     * @param id       博客ID
     * @return         Blog
     */
    Blog findById(String id) throws Exception;

}
