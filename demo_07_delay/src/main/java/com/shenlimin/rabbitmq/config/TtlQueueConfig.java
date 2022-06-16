package com.shenlimin.rabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * 过期配置
 * 生产者P通过type=diect模式的普通交换机X,将消息分别发到ttl的10秒routingKey为XA的QA和40秒的routingKey为XB的QB,
 * 通过routingKey为YD，ttl后发送到死信交换机Y中的延迟队列（死信队列)QD中，进行消费
 */
@Configuration
@SuppressWarnings("all")
public class TtlQueueConfig {

    //普通交换机
    public static final String X_EXCHANGE = "X";
    //死信交换机
    public static final String Y_DEAD_LETTER_EXCHANGE = "Y";
    //普通队列名称
    public static final String QUEUE_A = "QA";
    public static final String QUEUE_B = "QB";
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

    //声明ttl普通队列
    @Bean("queueA")
    public Queue queueA() {
        return QueueBuilder.durable(QUEUE_A).deadLetterRoutingKey("YD").deadLetterExchange(Y_DEAD_LETTER_EXCHANGE).ttl(10 * 1000).build();
    }

    //声明ttl普通队列
    @Bean("queueB")
    public Queue queueB() {
        return QueueBuilder.durable(QUEUE_B).deadLetterRoutingKey("YD").deadLetterExchange(Y_DEAD_LETTER_EXCHANGE).ttl(40 * 1000).build();
    }

    //死信队列用于消费
    @Bean("queueD")
    public Queue queueQD() {
        return QueueBuilder.durable(DEAD_LETTER_QUEUE).build();
    }

    //绑定将队列A绑定到交换机X通过routingKey:XA
    @Bean
    public Binding queueABingdingX(@Qualifier("queueA") Queue queue, @Qualifier("xExChange") DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("XA");
    }

    //绑定将队列B绑定到交换机X通过routingKey:XB
    @Bean
    public Binding queueBBingdingX(@Qualifier("queueB") Queue queue, @Qualifier("xExChange") DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("XB");
    }

    //绑定将队列QD绑定到交换机y通过routingKey:YD
    @Bean
    public Binding queueDBingdingY(@Qualifier("queueD") Queue queue, @Qualifier("yExChange") DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("YD");
    }
}
