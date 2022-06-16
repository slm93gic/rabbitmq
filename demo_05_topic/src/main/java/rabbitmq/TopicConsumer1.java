package rabbitmq;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;

/**
 * 消费者
 * topic模式
 * 生命主题交换机
 */
@SuppressWarnings("all")
public class TopicConsumer1 {

    //交换机
    public static final String EXCHANGE_NAME = "topic_logs";

    public static void main(String[] args) throws Exception {
        //获取信道
        Channel channel = RabbitMqUtils.getChannel();

        //直接交换机模式
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.TOPIC);

        String queueName = "Q1";
        //创建队列
        channel.queueDeclare(queueName, false, false, false, null);
        //绑定
        String routingKey = "*.orange.*";
        channel.queueBind(queueName, EXCHANGE_NAME, routingKey);

        //回调接口
        DeliverCallback deliverCallback = (consumberTag, message) -> {
            System.out.println("TopicConsumer1: " + new String(message.getBody()));
        };

        System.out.println("Q1等待消息");
        channel.basicConsume(queueName, true, deliverCallback, (consumberTag) -> {
        });


    }

}
