package com.shenlimin.rabbitmq;

import com.rabbitmq.client.ConnectionFactory;

@SuppressWarnings("all")
public class MyConnectionFactory {

    private static ConnectionFactory factory = null;
    private static String host = "127.0.0.1";
    private static Integer port = 5672;
    private static String userName = "admin";
    private static String password = "admin";
    private static String virtualHost = "/";


    static {
        if (factory == null) {
            factory = new ConnectionFactory();
            factory.setHandshakeTimeout(9999999);
            factory.setHost(host);
            factory.setPort(port);
            factory.setUsername(userName);
            factory.setPassword(password);
            factory.setVirtualHost(virtualHost);
        }
    }

    public static ConnectionFactory getConnectionFactory() {
        return factory;
    }


}
