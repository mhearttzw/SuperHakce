package com.superhakce.flinkafka;

import com.superhakce.flinkafka.producer.ProducerOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FlinkafkaApplicationTests {

    @Autowired
    private ProducerOrder producerOrder;

	@Test
	public void contextLoads() {
	}

    @Test
	public void testKafka(){
        producerOrder.producerOrder("heqingjiang");
    }

}
