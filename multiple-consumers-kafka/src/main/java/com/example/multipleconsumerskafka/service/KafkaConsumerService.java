package com.example.multipleconsumerskafka.service;

import com.example.multipleconsumerskafka.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {
    private final Logger logger = LoggerFactory.getLogger(KafkaConsumerService.class);

    @KafkaListener(topics = "${general.topic.name}", groupId = "${general.topic.group.id}")
    public void consume(String message){
        logger.info(String.format("message received -> %s",message));
    }

    @KafkaListener(
            topics = "${user.topic.name}",
            groupId = "${user.topic.group.id}",
            containerFactory = "userKafkaListenerContainerFactory"
    )
    public void consume(User user){
        logger.info(String.format("User received -> %s",user));
    }


}
