package rabbitmq.ttl;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import rabbitmq.RabbitMqUtils;

/**
 * 死信
 * 生产的信息，没有被消费，增加一个死信队列，放到死信队列，后续处理
 * 比如，订单没有被下单产生
 * 来源
 * 1、消息TTL过期，消息存活时间
 * 2、队列达到最大长度，队列满了
 * 3、消息被拒绝，并且没有自动应答
 * 将死信放到死信队列，后续处理
 */
public class TTlProducer {

    public static void main(String[] args) throws Exception {
        //创建信道
        Channel channel = RabbitMqUtils.getChannel();
        //发送一个延迟的死信消息 time to live 10秒过期
        AMQP.BasicProperties basicProperties = new AMQP.BasicProperties().builder().expiration("10000").build();

        String routingKey = "zhangsan";
        for (int i = 0; i < 12; i++) {
            String message = "info:" + i;
            channel.basicPublish(TTlConsumer1.NORMAL_EXCHANGE, routingKey, basicProperties, message.getBytes());
        }

        System.out.println("发送成功！～");
        channel.close();
    }


}
