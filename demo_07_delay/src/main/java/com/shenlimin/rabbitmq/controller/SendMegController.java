package com.shenlimin.rabbitmq.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;


/**
 * 发送延迟消息为10秒
 */
@RestController("ttl")
@Slf4j
public class SendMegController {

    @Resource
    private RabbitTemplate rabbitTemplate;

    /**
     * 发送消息
     */
    @GetMapping("send/{message}")
    public void sendMSg(@PathVariable String message) {
        log.info("当前时间{}，发送一条信息给两个TTL队列：{}", new Date().toString(), message);
        rabbitTemplate.convertAndSend("X", "XA", "消息来自TTL 10秒的队列：" + message);
        rabbitTemplate.convertAndSend("X", "XB", "消息来自TTL 40秒的队列：" + message);
    }
}
