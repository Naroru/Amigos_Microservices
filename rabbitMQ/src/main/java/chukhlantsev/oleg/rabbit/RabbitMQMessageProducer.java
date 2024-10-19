package chukhlantsev.oleg.rabbit;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Component;

//предоставляет функционал продюсерам отправлять сообщения

@Slf4j
@RequiredArgsConstructor
@Component
public class RabbitMQMessageProducer {

    //будет использоваься наш бин из класса Config
    private final AmqpTemplate amqpTemplate;

    //данные сообщения (payload) имеют тип Object, поскольку мы можем разные ДТОшки отправлять
    public void publish(Object payload, String exchange, String routingKey)
    {
        log.info("Publish to {} using routing key: {}, payload: {}",exchange,routingKey,payload);
        amqpTemplate.convertAndSend(exchange,routingKey,payload);

    }

}
