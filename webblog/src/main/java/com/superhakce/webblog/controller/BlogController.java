package com.superhakce.webblog.controller;

import com.superhakce.webblog.entity.Blog;
import com.superhakce.webblog.service.BlogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @Author: heqingjiang
 * @Maintenance: author
 * @Description: 博客控制器
 * @Date: Create in 2018/8/16 12:10
 */
@RestController
@Slf4j
@RequestMapping(value = "api/webblog")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @GetMapping(value = "/saveBlog")
    public String saveBlog() throws Exception{
        Blog blog = new Blog();
        blog.setContent("First Blog");
        blog.setCreateTime(new Date());
        blog.setUpdateTime(new Date());
        blog.setName("First Blog");
        blog.setUserId(1L);
        blog.setUserName("heqingjiang");
        blogService.saveBlog(blog);
        return "OK";
    }

    @GetMapping(value = "/findBlogByName")
    public Blog findBlogByName() throws Exception{
        return blogService.findByName("First Blog");
    }

    @GetMapping(value = "/findBlogById")
    public Blog findBlogById() throws Exception{
        return blogService.findById("5b74fb97dc1ada0c2461b8b9");
    }

}
