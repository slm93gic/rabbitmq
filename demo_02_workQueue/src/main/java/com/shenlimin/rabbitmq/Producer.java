package com.shenlimin.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

/**
 * 生产者
 * 发消息给队列
 * 简单消息模式
 */
public class Producer {

    //队列标识
    public static final String QUEUE_NAME = "hello";

    public static void main(String[] args) throws Exception {
        //创建信道
        Channel channel = RabbitMqUtils.getChannel();

        //声明队列，这里省略了交换机，用了默认的
        //4、通过创建交换机、声明队列、绑定关系、路由key,发送消息，和接受消息
        //1:消息队列名称
        //2:是否持久化
        //3:排他性，是否独占
        //4：是否自动
        //5、准备消息内容
        for (int i = 0; i < 10; i++) {
            String msg = "this is my send msgx" + i;
            channel.basicPublish("", QUEUE_NAME, null, msg.getBytes());
        }


        //6、发送给消息队列
        //1、交换机
        //2、队列名称 路由key
        //3、其他参数信息
        //4、发送的消息


        System.out.println("发送成功！～");


    }


}
