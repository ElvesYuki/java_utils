package com.elvesyuki.javautils.test;

import com.rabbitmq.client.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @ClassName AdminOrgController
 * @Description
 * @Author luohuan
 * @Date 2021/8/2 下午12:38
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplication.class)
public class RabbitmqTest {

    private static final Logger logger = LoggerFactory.getLogger(RabbitmqTest.class);

    private static final String host = "localhost";
    private static final Integer port = 5672;
    private static final String username = "admin";
    private static final String password = "admin";
    private static final String virtual_host = "my_vhost";

    private static final String QUEUE_NAME_HELLO = "hello";

    @Test
    public void test01() {
        logger.info("test01");
    }

    @Test
    public void testHello_Producer() {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(host);
        factory.setPort(port);
        factory.setUsername(username);
        factory.setPassword(password);
        factory.setVirtualHost(virtual_host);

        Connection connection = null;
        Channel channel = null;
        try {
            connection = factory.newConnection();
            channel = connection.createChannel();
            channel.queueDeclare(QUEUE_NAME_HELLO, false, false, false ,null);
            String message = "hello World";

            channel.basicPublish("", QUEUE_NAME_HELLO, null, message.getBytes());
            logger.info("消息发送完毕");

        } catch (IOException | TimeoutException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testHello_Consumer() {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(host);
        factory.setPort(port);
        factory.setUsername(username);
        factory.setPassword(password);
        factory.setVirtualHost(virtual_host);

        Connection connection = null;
        Channel channel = null;
        try {
            connection = factory.newConnection();
            channel = connection.createChannel();
            logger.info("等待接收消息");

            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String message = new String(delivery.getBody());
                logger.info(message);
            };

            CancelCallback cancelCallback = (consumerTag) -> {
                logger.info("消息消费被中断");
            };

            channel.basicConsume(QUEUE_NAME_HELLO, true, deliverCallback, cancelCallback);

        } catch (IOException | TimeoutException e) {
            e.printStackTrace();
        }

    }

}
