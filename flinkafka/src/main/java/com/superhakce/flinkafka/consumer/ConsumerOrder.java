package com.superhakce.flinkafka.consumer;

import com.superhakce.flinkafka.channel.OrderChannel;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

/**
 * @Author: heqingjiang
 * @Maintenance: author
 * @Description: 订单消费者
 * @Date: Create in 2018/10/16 18:00
 */
@EnableBinding(OrderChannel.class)
public class ConsumerOrder {

    @StreamListener(OrderChannel.InputOrderChannel)
    public void consumerOrder(Object object){
        System.err.println("object: " + object);
    }

}
