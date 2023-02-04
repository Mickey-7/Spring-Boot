package com.example.kafkaspringboot.controller;

import com.example.kafkaspringboot.service.KafkaSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/java_in_use-kafka")
public class ApacheKafkaWebController {

    @Autowired
    KafkaSenderService kafkaSenderService;

    @GetMapping(value = "/producer")
    public String producer(@RequestParam("message") String message){
        kafkaSenderService.send(message);

        return "Message sent to the Kafka Topic javainuse-topic Successfully";
    }
}
