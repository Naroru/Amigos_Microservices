package chukhlantsev.oleg.clients.fraud;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//value = имя микросервиса из Eureka. Если Eureka не используется, то требуется адрес сервера (hostname), где
//работает сервис - подробнее см в документации

//path - это маппинг из контроллера, чьи методы мы тут описываем

@FeignClient(
        value = "fraud",
        path = "api/v1/fraud-check/")
public interface FraudClient {

    @GetMapping("{customerId}")
    FraudCheckResponse isFraudster(@PathVariable("customerId") Integer customerId); //обязательно надо прописать имя PathVariable

}
