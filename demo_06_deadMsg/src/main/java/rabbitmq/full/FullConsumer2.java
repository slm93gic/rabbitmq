package rabbitmq.full;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;
import rabbitmq.RabbitMqUtils;

/**
 * 消费者
 * 在消费者1中因为某种原因导致的死信进入死信队列无法消费，
 * 在本次用于处理死信消息
 */
@SuppressWarnings("all")
public class FullConsumer2 {

    //死信交换机
    public static final String DEAD_EXCHANGE = "dead_exchange";
    //死信队列名称
    public static final String DEAD_QUEUE = "dead_queue";

    public static void main(String[] args) throws Exception {
        //获取信道
        Channel channel = RabbitMqUtils.getChannel();
        //死信和正常交换机模式
        channel.exchangeDeclare(DEAD_EXCHANGE, BuiltinExchangeType.DIRECT);
        //创建普通队列
        channel.queueDeclare(DEAD_QUEUE, false, false, false, null);
        //绑定
        channel.queueBind(DEAD_QUEUE, DEAD_EXCHANGE, "lisi");

        //回调接口
        DeliverCallback deliverCallback = (consumberTag, message) -> {
            System.out.println("死信队列消费的信息: " + new String(message.getBody()));
        };

        channel.basicConsume(DEAD_QUEUE, true, deliverCallback, (consumberTag) -> {
        });


    }

}
