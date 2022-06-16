package com.shenlimin;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class ConnectionUtil {

    private static Connection connection = null;
    private static final String host = "192.168.56.10";
    private static final int port = 5672;
    private static final String vHost = "/";
    private static final String userName = "admin";
    private static final String passWord = "admin";

    public static Connection getConnection() throws Exception {
        if (connection == null) {
            getConnection(host, port, vHost, userName, passWord);
        }
        return connection;
    }

    public static void getConnection(String host, int port, String vHost, String userName, String passWord) throws Exception {
        //1、定义连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        //2、设置服务器地址
        factory.setHost(host);
        //3、设置端口
        factory.setPort(port);
        //4、设置虚拟主机、用户名、密码
        factory.setVirtualHost(vHost);
        factory.setUsername(userName);
        factory.setPassword(passWord);
        //5、通过连接工厂获取连接
        connection = factory.newConnection();
    }

}