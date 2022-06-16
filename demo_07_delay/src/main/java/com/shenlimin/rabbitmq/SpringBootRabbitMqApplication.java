package com.shenlimin.rabbitmq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 延迟交换机
 * 比如：提交订单，但是没有支付
 * 注册没有换头像，一段事件后提醒
 */
@SpringBootApplication
public class SpringBootRabbitMqApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootRabbitMqApplication.class, args);
    }

}
