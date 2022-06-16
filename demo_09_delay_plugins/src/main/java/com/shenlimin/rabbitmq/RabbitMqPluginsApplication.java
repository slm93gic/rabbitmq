package com.shenlimin.rabbitmq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 延迟交换机
 * 比如：提交订单，但是没有支付
 * 注册没有换头像，一段事件后提醒
 * <p>
 * 优化，现在的延迟是死的需要设置不同的时间
 *
 * 优化：基于上一步的延迟，比如20秒的和2秒的延迟同时打印出来，是因为这是mq的bug，
 * 新增了一个延迟插件，rabbitmq_delayed_message_exchange-3.9.0.ez来解决
 */
@SpringBootApplication
public class RabbitMqPluginsApplication {

    public static void main(String[] args) {
        SpringApplication.run(RabbitMqPluginsApplication.class, args);
    }

}
