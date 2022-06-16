package com.shenlimin.rabbitmq;

import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DeliverCallback;

import java.util.concurrent.TimeUnit;

/**
 * 消费者
 * 用于手动应答，就是消息在发送到某个消费者，消费者挂了，这个时候ack发现没有收到信息，然后回将
 * 消息返回到队列，继续排队，交给其他消费者进行消费，保证消息不被丢失
 */
@SuppressWarnings("all")
public class Consumer2 {

    public static void main(String[] args) throws Exception {
        Channel channel = RabbitMqUtils.getChannel();

        DeliverCallback deliverCallback = (consumberTag, message) -> {
            System.out.println(new String(message.getBody()));
            try {
                TimeUnit.SECONDS.sleep(30);
            } catch (InterruptedException e) {

            }
            channel.basicAck(message.getEnvelope().getDeliveryTag(), false);
        };

        CancelCallback cancelCallback = (consumberTag) -> {
            System.out.printf("消息接受中断");
        };

        System.out.println("C2等待消息");
        channel.basicConsume(Producer.QUEUE_NAME, false, deliverCallback, cancelCallback);

    }

}
