package com.example.multipleconsumerskafka.service;


import com.example.multipleconsumerskafka.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class KafkaProducerService {
    private static final Logger logger = LoggerFactory.getLogger(KafkaProducerService.class);

    //1) General topic with string payload
    @Value(value = "${general.topic.name}")
    private String topicName;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    //2) Topic with user object payload
    @Value(value = "${user.topic.name}")
    private String userTopicName;

    @Autowired
    private KafkaTemplate<String, User> userKafkaTemplate;

    public void sendMessage(String message) {
        CompletableFuture<SendResult<String, String>> future = kafkaTemplate.send(topicName, message);
        future.handle((result, exception) -> {
            if (exception != null) {
                logger.error("unable to send message : "+message);
            } else {
                logger.info("sent message : "+message+" " +
                        "with offset : "+result.getRecordMetadata().offset());
            }
            return null;
        });
    }

    public  void saveCreateUserLog(User user){
        CompletableFuture<SendResult<String,User>> future = userKafkaTemplate.send(userTopicName,user);
        future.handle((result,exception) -> {
           if (exception != null){
               logger.error("user created : "+user);
           }else {
               logger.info("user created : "+user+"" +
                       " with offset : "+result.getRecordMetadata().offset());
           }

            return null;
        });
    }
}


