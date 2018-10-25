package com.superhakce.flinkafka.producer;

import com.superhakce.flinkafka.channel.OrderChannel;
import org.apache.kafka.common.network.KafkaChannel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.stereotype.Component;

/**
 * @Author: heqingjiang
 * @Maintenance: author
 * @Description: 订单生产者
 * @Date: Create in 2018/10/16 17:53
 */
@Component
@EnableBinding(OrderChannel.class)
public class ProducerOrder {

    @Autowired
    private OrderChannel orderChannel;

    public void producerOrder(String msg){
        orderChannel.output().send(MessageBuilder.withPayload("msg: " + msg).build());
    }

}
