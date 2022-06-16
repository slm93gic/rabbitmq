package com.shenlimin.pack01;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.shenlimin.ConnectionUtil;

public class Producer01 {

    public final static String QUEUE_NAME = "my_queue";

    public static void main(String[] args) throws Exception {
        sendMessage();
    }

    public static void sendMessage() throws Exception {
        //1、获取连接
        Connection connection = ConnectionUtil.getConnection();
        //2、声明信道
        Channel channel = connection.createChannel();
        //3、声明(创建)队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        //4、定义消息内容
        String message = "hello rabbitmq ";
        //5、发布消息
        channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
        System.out.println("[x] Sent'" + message + "'");
        //6、关闭通道
        channel.close();
        //7、关闭连接
        connection.close();
    }

}
