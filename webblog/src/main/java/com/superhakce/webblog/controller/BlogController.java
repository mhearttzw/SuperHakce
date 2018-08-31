package com.superhakce.webblog.controller;

import com.superhakce.webblog.entity.Blog;
import com.superhakce.webblog.enums.SystemCode;
import com.superhakce.webblog.model.common.ResponseModel;
import com.superhakce.webblog.service.BlogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: heqingjiang
 * @Maintenance: author
 * @Description: 博客 Controller
 * @Date: Create in 2018/8/16 12:10
 */
@RestController
@Slf4j
@RequestMapping(value = "api/webBlog")
public class BlogController {


    @Autowired
    private BlogService blogService;


    @PostMapping(value = "/saveBlog")
    public ResponseModel saveBlog(Blog blog) throws Exception{
        blogService.saveBlog(blog);
        return new ResponseModel(SystemCode.SUCCESS);
    }


    @PostMapping(value = "/findBlogByName")
    public ResponseModel<Blog> findBlogByName(@Param(value = "blogName") String blogName) throws Exception{
        Blog blog = blogService.findByBlogName(blogName);
        return new ResponseModel<>(SystemCode.SUCCESS, blog);
    }


    @PostMapping(value = "/findBlogById")
    public ResponseModel<Blog> findBlogById(@Param(value = "blogId") String blogId) throws Exception{
        Blog blog = blogService.findById(blogId);
        return new ResponseModel<>(SystemCode.SUCCESS, blog);
    }

}
