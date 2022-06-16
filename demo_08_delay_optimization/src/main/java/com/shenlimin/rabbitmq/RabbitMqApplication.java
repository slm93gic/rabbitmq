package com.shenlimin.rabbitmq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 延迟交换机
 * 比如：提交订单，但是没有支付
 * 注册没有换头像，一段事件后提醒
 * <p>
 * 优化，现在的延迟是死的需要设置不同的时间
 * <p>
 * 存在一个bug
 * 发送消息1 20秒
 * 发送消息2 2秒
 * <p>
 * 最后的结果显示两个同时出现
 */
@SpringBootApplication
public class RabbitMqApplication {

    public static void main(String[] args) {
        SpringApplication.run(RabbitMqApplication.class, args);
    }

}
