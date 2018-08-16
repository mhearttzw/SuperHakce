package com.superhakce.webblog.repository;

import com.superhakce.webblog.entity.Blog;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

/**
 * @Author: heqingjiang
 * @Maintenance: author
 * @Description: 博客
 * @Date: Create in 2018/8/16 12:02
 */
@Component
public interface BlogRepository extends MongoRepository<Blog, String> {

    /**
     *  根据标题查询博客
     * @param name
     * @return
     */
    Blog findByName(String name);

}
