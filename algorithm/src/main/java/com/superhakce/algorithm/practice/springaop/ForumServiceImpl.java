package com.superhakce.algorithm.practice.springaop;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @Author: heqingjiang
 * @Maintenance: author
 * @Description: 实现
 * @Date: Create in 2018/10/2 20:30
 */
@Service
@Slf4j
public class ForumServiceImpl implements ForumService{

    @Override
    public void removeTopic(int topicId){
        //横切逻辑
        System.err.println("ForumServiceImpl.removeTopic");
        //横切逻辑
    }

    @Override
    public void removeForum(int forumId){
        //横切逻辑
        System.err.println("ForumServiceImpl.removeForum");
        //横切逻辑
    }

}
