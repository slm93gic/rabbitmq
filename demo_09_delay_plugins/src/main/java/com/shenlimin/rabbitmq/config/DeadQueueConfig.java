package com.shenlimin.rabbitmq.config;

import com.rabbitmq.client.BuiltinExchangeType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
@Slf4j
public class DeadQueueConfig {

    public static final String DELAYED_EXCHANGE = "delayed.exchange";
    public static final String DELAYED_QUEUE_NAME = "delayed.queue";
    public static final String DELAYED_ROUTING_KEY = "delayed.routing_key";

    @Bean
    public CustomExchange exchange(){
        Map<String, Object> arguments = new HashMap<>();
        arguments.put("x-delayed-type", BuiltinExchangeType.DIRECT.getType());
        return new CustomExchange(DELAYED_EXCHANGE,"x-delayed-message",true,false,arguments);
    }

    @Bean
    public Queue queue(){
        return new Queue(DELAYED_QUEUE_NAME);
    }

    @Bean
    public Binding binding(@Qualifier("queue") Queue queue,@Qualifier("exchange") CustomExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with(DELAYED_ROUTING_KEY).noargs();
    }



}
