package com.example.multipleconsumerskafka.controller;

import com.example.multipleconsumerskafka.model.User;
import com.example.multipleconsumerskafka.service.KafkaProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/kafka")
public class KafkaProducerController {
    private final KafkaProducerService producerService;

    @Autowired
    public KafkaProducerController(KafkaProducerService producerService) {
        this.producerService = producerService;
    }

    @PostMapping(value = "/publish")
    public void sendMessageToKafkaTopic(@RequestParam("message") String message){
        producerService.sendMessage(message);
    }

    @PostMapping(value = "/createUser")
    public void sendMessageToKafkaTopic(
            @RequestParam("userId") String userId,
            @RequestParam("firstName") String firstName,
            @RequestParam("lastName") String lastName) {

        User user = new User();
        user.setUserId(Long.parseLong(userId));
        user.setFirstName(firstName);
        user.setLastName(lastName);

        producerService.saveCreateUserLog(user);
    }
}
