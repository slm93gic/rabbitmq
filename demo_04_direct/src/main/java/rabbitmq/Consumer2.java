package rabbitmq;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;

import java.util.concurrent.TimeUnit;

/**
 * 消费者
 * 直接交换机（路由模式）区别在routingkey
 */
@SuppressWarnings("all")
public class Consumer2 {

    public static final String EXCHANGE_NAME = "direct_logs";

    public static void main(String[] args) throws Exception {
        Channel channel = RabbitMqUtils.getChannel();

        //直接交换机模式
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);

        //创建队列
        channel.queueDeclare("disk", false, false, false, null);

        //绑定
        channel.queueBind("disk", EXCHANGE_NAME, "error");

        DeliverCallback deliverCallback = (consumberTag, message) -> {
            System.out.println("Consumer2:" + new String(message.getBody()));
        };
        //不自动应答
        channel.basicConsume("disk", true, deliverCallback, (consumberTag) -> {
        });


    }

}
