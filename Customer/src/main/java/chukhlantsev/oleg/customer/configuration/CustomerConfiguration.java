package chukhlantsev.oleg.customer.configuration;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class CustomerConfiguration {

    @Bean
    @LoadBalanced //для Eureka, чтобы рест темплате мог выбрать 1  адрес микросервиса из берущихся в Eureka
    public RestTemplate restTemplate()
    {
        return new RestTemplate();

    }

}
