package com.shenlimin.pack01;

import com.rabbitmq.client.*;
import com.shenlimin.ConnectionUtil;
import com.sun.deploy.net.HttpUtils;

import java.io.IOException;

public class Receive01 {

    private static final String QUEUE_NAME = Producer01.QUEUE_NAME;

    public static void main(String args[]) throws Exception {
        //获取连接
        Connection connection = ConnectionUtil.getConnection();
        //获取channel、
        Channel channel = connection.createChannel();
        //声明队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        //定义一个消费这
        Consumer consumer = new DefaultConsumer(channel) {

            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String msg = new String(body, "utf-8");
                System.out.println("[1] Receive1 msg:" + msg);
            }
        };
        boolean autoAck = false;
        channel.basicConsume(QUEUE_NAME, autoAck, consumer);
    }

}
