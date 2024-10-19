package chukhlantsev.oleg;

import lombok.Getter;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class NotificationConfig {

    //------RABBIT MQ
    @Value("${rabbitmq.exchanges.internal}")
    private String internalExchange;

    @Value("${rabbitmq.queue.notification}")
    private String notificationQueue;

    @Value("${rabbitmq.routing-keys.internal-notification}")
    private String notificationRoutingKey;

    @Bean
    public TopicExchange internalTopicExchange()
    {
        return new TopicExchange(internalExchange);
    }

    @Bean
    public Queue notificationQueue()
    {
        return new Queue(notificationQueue);
    }

    @Bean
    public Binding internalNotificationBinding()
    {
        return BindingBuilder
                .bind(notificationQueue())
                .to(internalTopicExchange())
                .with(notificationRoutingKey);

    }
    //----RabbitMQ
}
