package chukhlantsev.oleg;

import chukhlantsev.oleg.clients.notification.NotificationClient;
import chukhlantsev.oleg.clients.notification.NotificationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class FraudCheckService {

    private final FraudCheckRepository fraudCheckRepository;


    public boolean isFraudster(Integer customerID)
    {
         fraudCheckRepository.save(
                FraudCheckHistory.builder()
                        .customerId(customerID)
                        .isFraudster(false)
                        .createdAt(LocalDateTime.now())
                        .build()
        );

        return false; //не будем делать реальную проверку на то, является ли юзер мошенником
    }
}
