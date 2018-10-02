package com.superhakce.algorithm.service.impl;

import com.superhakce.algorithm.entity.Master;
import com.superhakce.algorithm.mapper.master.MasterMapper;
import com.superhakce.algorithm.service.MasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/**
 * @Author: heqingjiang
 * @Maintenance: author
 * @Description: Master 实现
 * @Date: Create in 2018/9/29 18:08
 */
@Service
@Primary
public class MasterServiceImpl implements MasterService{

    @Autowired
    private MasterMapper masterMapper;

    @Override
    public boolean save(Master master){
        return masterMapper.insert(master) > 0;
    }

}
