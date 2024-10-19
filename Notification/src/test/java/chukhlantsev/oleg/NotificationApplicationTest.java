package chukhlantsev.oleg;

import chukhlantsev.oleg.rabbit.RabbitMQMessageProducer;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class NotificationApplicationTest {

    @Autowired
    private RabbitMQMessageProducer messageProducer;

    @Autowired
    private  NotificationConfig notificationConfig;

    @Autowired
    private AmqpTemplate amqpTemplate;


    @Test
    public void testSendingMessage()
    {
        messageProducer.publish("foo", notificationConfig.getInternalExchange(), notificationConfig.getNotificationRoutingKey());

        // или не используя мой messageProducer, а напрямую в amqpTemplate
        amqpTemplate.convertAndSend(notificationConfig.getInternalExchange(), notificationConfig.getNotificationRoutingKey(), "foo");

    }
}