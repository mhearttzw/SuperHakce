package com.superhakce.webblog.service.impl;

import com.superhakce.webblog.entity.Blog;
import com.superhakce.webblog.repository.BlogRepository;
import com.superhakce.webblog.service.BlogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @Author: heqingjiang
 * @Maintenance: author
 * @Description: 博客服务实现
 * @Date: Create in 2018/8/16 12:07
 */
@Service
@Slf4j
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogRepository blogRepository;

    /**
     *  根据标题查询博客
     * @param blogName
     * @return
     */
    @Override
    public Blog findByBlogName(String blogName) throws Exception{
        return blogRepository.findByBlogName(blogName);
    }

    /**
     * 保存博客
     * @param blog
     * @throws Exception
     */
    @Override
    public void saveBlog(Blog blog) throws Exception{
        blogRepository.save(blog);
    }

    /**
     *  根据ID查询博客
     * @param id
     * @return
     */
    @Override
    public Blog findById(String id) throws Exception{
        Optional<Blog> optionalBlog = blogRepository.findById(id);
        return optionalBlog.get();
    }

}
