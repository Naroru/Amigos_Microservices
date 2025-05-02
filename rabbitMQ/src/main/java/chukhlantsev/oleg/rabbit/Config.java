package chukhlantsev.oleg.rabbit;

import lombok.AllArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class Config {

    private final  ConnectionFactory connectionFactory;

    //позволяет отправлять сообщения в очередь
    //ПО УМОЛЧАНИЮ УЖЕ ЕСТЬ В КАЧЕСТВЕ БИНА., но тут мы типа кастомизируем его
    @Bean
    public AmqpTemplate amqpTemplate()
    {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jacksonConverter()); //для преобразования сообщений в json'ы
        return rabbitTemplate;
    };

    //позволяет получать читать сообщения из очереди
    //ПО УМОЛЧАНИЮ УЖЕ ЕСТЬ В КАЧЕСТВЕ БИНА. Здесь пишем чисто для понимания процесса
    @Bean
    public SimpleRabbitListenerContainerFactory simpleRabbitListenerContainerFactory()
    {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(jacksonConverter());

        return factory;
    }
    @Bean
    public MessageConverter jacksonConverter()
    {
        //конвертер также можно конфигурировать (дополнять какими то модулями и т д)
        return new Jackson2JsonMessageConverter();
    }


}