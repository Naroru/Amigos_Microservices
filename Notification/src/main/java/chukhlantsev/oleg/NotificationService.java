package chukhlantsev.oleg;

import chukhlantsev.oleg.clients.notification.NotificationClient;
import chukhlantsev.oleg.clients.notification.NotificationRequest;
import chukhlantsev.oleg.entity.Notification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository repository;


    public void sendNotification(NotificationRequest request)
    {
        repository.save(
                Notification.builder()
                        .sendTo(request.sendTo())
                        .sender("Amigos Oleg")
                        .sendAt(LocalDateTime.now())
                        .message(request.message())
                        .build()
                        );
    }

}
