package chukhlantsev.oleg.customer;

import chukhlantsev.oleg.clients.fraud.FraudCheckResponse;
import chukhlantsev.oleg.clients.fraud.FraudClient;
import chukhlantsev.oleg.clients.notification.NotificationClient;
import chukhlantsev.oleg.clients.notification.NotificationRequest;
import chukhlantsev.oleg.customer.dto.CustomerRegistrationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class CustomerService{

    private final CustomerRepository customerRepository;
    private final RestTemplate restTemplate;
    private final FraudClient fraudClient;
    private final NotificationClient notificationClient;

    public void registerCustomer(CustomerRegistrationRequest customerRegistrationRequest)
    {

        //todo проверки на существование пользователя, валидацию данных
        Customer customer = Customer.builder()
                .name(customerRegistrationRequest.name())
                .surname(customerRegistrationRequest.surname())
                .email(customerRegistrationRequest.email())
                .build();

        customerRepository.saveAndFlush(customer); //flush нужен, чтобы получить пользователя и idшником из БД
                                                    //иначе в  customer.getId() он будет null

       /* FraudCheckResponse response = restTemplate.getForObject(
                "http://FRAUD/api/v1/fraud-check/{customerId}", //FRAUD  - имя приложения в Eureka  //"http://localhost:8081/api/v1/fraud-check/{customerId}",
                FraudCheckResponse.class,
                customer.getId() );  класс FraudCheckResponse удаляем из этого микросервиса и ниже будем импортить измодуля с Openfeign */

        FraudCheckResponse response = fraudClient.isFraudster(customer.getId());

        //todo првоверка что ответ не null
        if (response != null && response.isFraudster())
        {
            throw new IllegalArgumentException("Customer is fraud!");
        }

        notificationClient.sendNotification(
                new NotificationRequest(
                        customer.getEmail(),
                        String.format("Hi %s. Welcome to microservice's world", customer.getName())
                )
        );

    }
}
