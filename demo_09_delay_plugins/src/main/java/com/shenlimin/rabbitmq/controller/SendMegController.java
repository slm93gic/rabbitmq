package com.shenlimin.rabbitmq.controller;

import com.shenlimin.rabbitmq.config.DeadQueueConfig;
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
     * 优化的
     * 发送消息延迟时间
     */
    @GetMapping("/send/{message}/{ttl}")
    public void sendMsgPlugins(@PathVariable String message, @PathVariable Integer ttl) throws Exception {
        log.info("当前时间{}，发送一条时间长{}秒的TTL队列消息：{}", new Date().toString(), ttl, message);
        rabbitTemplate.convertAndSend(DeadQueueConfig.DELAYED_EXCHANGE, DeadQueueConfig.DELAYED_ROUTING_KEY, "消息来自延迟插件 " + ttl + "毫秒的队列：" + message, msg -> {
            //发送消息的延迟时间
            msg.getMessageProperties().setDelay(ttl);
            return msg;
        });
    }
}
