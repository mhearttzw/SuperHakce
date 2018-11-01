package com.superhakce.algorithm.practice.service;

import com.superhakce.algorithm.practice.entity.Master;
import com.superhakce.algorithm.practice.entity.Slave;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * @Author: heqingjiang
 * @Maintenance: author
 * @Description:
 * @Date: Create in 2018/10/15 18:35
 */
@Component
public class TestAtomikos {

    @Autowired
    private MasterService masterService;

    @Autowired
    private SlaveService slaveService;

    @Transactional
    public void test(){
        Master master = new Master();
        master.setAge(22);master.setName("A");master.setPower(new BigDecimal(12.00));

        Slave slave = new Slave();
        slave.setAge(22);slave.setName("A");slave.setPower(new BigDecimal(12.00));

        masterService.save(master);
        slaveService.save(slave);
    }

}
