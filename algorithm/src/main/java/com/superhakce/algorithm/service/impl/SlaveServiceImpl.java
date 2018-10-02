package com.superhakce.algorithm.service.impl;

import com.superhakce.algorithm.entity.Slave;
import com.superhakce.algorithm.mapper.slave.SlaveMapper;
import com.superhakce.algorithm.service.SlaveService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: heqingjiang
 * @Maintenance: author
 * @Description: Slave 实现
 * @Date: Create in 2018/9/29 18:39
 */
@Slf4j
@Service
public class SlaveServiceImpl implements SlaveService{

    @Autowired
    private SlaveMapper slaveMapper;

    @Override
    public boolean save(Slave slave){
        return slaveMapper.insert(slave) > 0;
    }

}
