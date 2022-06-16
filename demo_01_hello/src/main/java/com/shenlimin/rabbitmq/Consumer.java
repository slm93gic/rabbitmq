package com.shenlimin.rabbitmq;

import com.rabbitmq.client.*;

/**
 * 消费者
 */
public class Consumer {

    public static void main(String[] args) {
        Connection connection = null;
        try {
            connection = MyConnectionFactory.getConnectionFactory().newConnection();
            Channel channel = connection.createChannel();

            DeliverCallback deliverCallback = (consumberTag, message) -> {
                System.out.println(new String(message.getBody()));
            };

            CancelCallback cancelCallback = (consumberTag) -> {
                System.out.printf("消息接受中断");
            };
            //1、消费队列
            //2、消费后是否自动应对
            //3、消费者未消息的回调
            //4、消费者取消费的回调
            channel.basicConsume(Producer.QUEUE_NAME, true, deliverCallback, cancelCallback);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
