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
public class Consumer1 {

    public static final String EXCHANGE_NAME = "direct_logs";

    public static void main(String[] args) throws Exception {
        Channel channel = RabbitMqUtils.getChannel();

        //直接交换机模式
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);

        //创建队列
        channel.queueDeclare("console", false, false, false, null);

        //绑定
        channel.queueBind("console", EXCHANGE_NAME, "info");
        channel.queueBind("console", EXCHANGE_NAME, "warning");


        DeliverCallback deliverCallback = (consumberTag, message) -> {
            System.out.println("Consumer1:" + new String(message.getBody()));
        };
        //不自动应答
        channel.basicConsume("console", true, deliverCallback, (consumberTag) -> {
        });


    }

}
