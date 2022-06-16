package com.shenlimin.rabbitmq;

import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;

/**
 * 消费者01
 */
public class Work {

    public static void main(String[] args) throws Exception {
        DeliverCallback deliverCallback = (consumerTag, message) -> {
            System.out.println(new String(message.getBody()));
        };

        CancelCallback cancelCallback = (consumerTag) -> {
            System.out.printf("消息接受中断");
        };

        Channel channel = RabbitMqUtils.getChannel();
        System.out.println("C2等待消息");
        //true 表示批量应答，下面有收到应答的
        channel.basicConsume(Producer.QUEUE_NAME, true, deliverCallback, cancelCallback);
    }

}
