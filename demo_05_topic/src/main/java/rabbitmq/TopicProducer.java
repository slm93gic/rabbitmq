package rabbitmq;

import com.rabbitmq.client.Channel;

/**
 * Topics 主题模式-
 */
public class TopicProducer {

    public static void main(String[] args) throws Exception {
        //创建信道
        Channel channel = RabbitMqUtils.getChannel();

        String[] routingKeys = {"quick.orange.rabbit", "lazy.orange.elephant", "quick.orange.fox",
                "lazy.brown.fox", "lazy.pink.rabbit", "quick.brown.fox", "quick.orange.male.rabbit", "lazy.orange.male.rabbit"};
        for (String routingKey : routingKeys) {
            channel.basicPublish(TopicConsumer1.EXCHANGE_NAME, routingKey, null, routingKey.getBytes());
        }


        System.out.println("发送成功！～");
        channel.close();
    }


}
