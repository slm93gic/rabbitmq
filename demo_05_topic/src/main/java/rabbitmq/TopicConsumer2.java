package rabbitmq;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;

/**
 * 消费者
 * topic模式
 */
@SuppressWarnings("all")
public class TopicConsumer2 {

    public static final String EXCHANGE_NAME = "topic_logs";

    public static void main(String[] args) throws Exception {
        //获取信道
        Channel channel = RabbitMqUtils.getChannel();

        //直接交换机模式
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.TOPIC);

        String queueName = "Q2";
        //创建队列
        channel.queueDeclare(queueName, false, false, false, null);
        //绑定
        channel.queueBind(queueName, EXCHANGE_NAME, "*.*rabbit");
        channel.queueBind(queueName, EXCHANGE_NAME, "lazy.#");

        //回调接口
        DeliverCallback deliverCallback = (consumberTag, message) -> {
            System.out.println("TopicConsumer2: " + new String(message.getBody()));
        };

        System.out.println("Q2等待消息");
        channel.basicConsume(queueName, true, deliverCallback, (consumberTag) -> {
        });

    }

}
