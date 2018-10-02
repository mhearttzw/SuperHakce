package com.superhakce.algorithm;

import com.superhakce.algorithm.entity.Master;
import com.superhakce.algorithm.entity.Slave;
import com.superhakce.algorithm.service.MasterService;
import com.superhakce.algorithm.service.SlaveService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.SQLException;

/**
 * @Author: heqingjiang
 * @Maintenance: author
 * @Description: 分布式事务，分布式锁
 * @Date: Create in 2018/9/4 18:39
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class DistributedAffairsTest {

    @Autowired
    private MasterService masterService;

    @Autowired
    private SlaveService slaveService;

    @Test
    @Transactional(rollbackFor = {Exception.class, SQLException.class})
    public void testManSave(){

        Master master = new Master();
        master.setName("heqingjiang");
        master.setAge(24);
        master.setPower(new BigDecimal(100.00));
        boolean flag = masterService.save(master);
        log.info("master flag={}", flag);

        Slave slave = new Slave();
        slave.setName("heqingjiang");
        slave.setAge(24);
        slave.setPower(new BigDecimal(100.00));
        boolean flags = slaveService.save(slave);
        log.info("slave flags={}", flags);

    }

}
