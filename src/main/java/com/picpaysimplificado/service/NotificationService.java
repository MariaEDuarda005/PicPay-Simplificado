package com.picpaysimplificado.service;

import com.picpaysimplificado.doman.User.User;
import com.picpaysimplificado.dtos.NotificationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NotificationService {
    @Autowired
    private RestTemplate restTemplate;

    public void  sendNotification(User user, String message) throws Exception {
        String email = user.getEmail();

        NotificationDTO notificationRequest = new NotificationDTO(email, message);

//        ResponseEntity<String> notificationResponse = restTemplate.postForEntity("https://util.devi.tools/api/v1/notify", notificationRequest, String.class);
//
//        if (!(notificationResponse.getStatusCode() == HttpStatus.NOT_FOUND)){ // a url só retorna 404
//            System.out.println("Erro ao enviar notificações");
//            throw new Exception("Serviço de notificação está fora do ar");
//        }

        System.out.println("Notificação enviada para usuario");
    }
}
