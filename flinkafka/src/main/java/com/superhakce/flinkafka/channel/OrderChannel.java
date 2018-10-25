package com.superhakce.flinkafka.channel;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 * @Author: heqingjiang
 * @Maintenance: author
 * @Description: kafka channel
 * @Date: Create in 2018/10/16 17:39
 */
public interface OrderChannel {

    String InputOrderChannel = "InputOrderChannel";

    String OutputOrderChannel = "OutputOrderChannel";

    @Input(OrderChannel.InputOrderChannel)
    SubscribableChannel input();

    @Output(OrderChannel.OutputOrderChannel)
    MessageChannel output();

}
