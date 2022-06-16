package rabbitmq;

import com.rabbitmq.client.Channel;

/**
 * 直接交换机 dirct 路由模式
 */
public class Producer {


    public static void main(String[] args) throws Exception {
        //创建信道
        Channel channel = RabbitMqUtils.getChannel();

        //S1 路由key
        //可以看routingKey
        for (int i = 0; i < 2; i++) {
            String msg = "this is my send msg" + i;
            channel.basicPublish(Consumer1.EXCHANGE_NAME, "", null, msg.getBytes());
        }

        System.out.println("发送成功！～");
        channel.close();
    }


}
