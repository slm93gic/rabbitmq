package com.shenlimin.rabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * 过期配置
 * 生产者P通过type=direct模式的普通交换机X,将消息分别发到ttl的10秒routingKey为XA的QA和40秒的routingKey为XB的QB,
 * 通过routingKey为YD，ttl后发送到死信交换机Y中的延迟队列（死信队列)QD中，进行消费
 */
@Configuration
public class TtlQueueConfig {
    //普通交换机
    public static final String X_EXCHANGE = "X";
    //死信交换机
    public static final String Y_DEAD_LETTER_EXCHANGE = "Y";
    public static final String QUEUE_C = "QC";
    //死信队列名称
    public static final String DEAD_LETTER_QUEUE = "QD";

    //声明普通交换机
    @Bean("xExChange")
    public DirectExchange xExchange() {
        return new DirectExchange(X_EXCHANGE);
    }

    //声明死信交换机
    @Bean("yExChange")
    public DirectExchange yExchange() {
        return new DirectExchange(Y_DEAD_LETTER_EXCHANGE);
    }

    /**
     * 优化
     * 现在的延迟时间是写死的，增加一个QC用于解决不同延迟时间的使用
     */
    @Bean("queueC")
    public Queue queueC() {
        return QueueBuilder.durable(QUEUE_C).deadLetterRoutingKey("YD").deadLetterExchange(Y_DEAD_LETTER_EXCHANGE).build();
    }

    //死信队列用于消费
    @Bean("queueD")
    public Queue queueQD() {
        return QueueBuilder.durable(DEAD_LETTER_QUEUE).build();
    }


    //绑定将队列QD绑定到交换机y通过routingKey:YD
    @Bean
    public Binding queueDBindingY(@Qualifier("queueD") Queue queue, @Qualifier("yExChange") DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("YD");
    }

    //绑定将队列QC绑定到交换机x通过routingKey:XC
    @Bean
    public Binding queueCBindingX(@Qualifier("queueC") Queue queue, @Qualifier("xExChange") DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("XC");
    }
}
