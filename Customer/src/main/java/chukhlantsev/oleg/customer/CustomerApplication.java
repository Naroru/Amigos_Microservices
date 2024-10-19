package chukhlantsev.oleg.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(scanBasePackages = {
        "chukhlantsev.oleg.rabbit",
        "chukhlantsev.oleg.customer"}
)

@EnableEurekaClient
@EnableFeignClients (basePackages = "chukhlantsev.oleg.clients")
public class CustomerApplication {
    public static void main(String[] args) {
        SpringApplication.run(CustomerApplication.class,args);
    }
}
