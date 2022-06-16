package com.shenlimin.rabbitmq.consumer;

import com.rabbitmq.client.Channel;
import com.shenlimin.rabbitmq.config.TtlQueueConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 消息消费者
 */
@Slf4j
@Component
public class DeadLetterConsumer {

    //接收消息
    @RabbitListener(queues = TtlQueueConfig.DEAD_LETTER_QUEUE)
    public void receiveD(Message message, Channel channel) {
        String msg = new String(message.getBody());
        log.info("当前时间{}，收到死信的消息：{}", new Date().toString(), msg);
    }

}
