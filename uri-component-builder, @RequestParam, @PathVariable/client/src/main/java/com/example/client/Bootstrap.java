package com.example.client;

import com.example.client.controller.ClientController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class Bootstrap {
    @Autowired
    private ClientController controller;


    @EventListener(ContextRefreshedEvent.class)
    public void init(){
        controller.usingUriCompBuilder();
        //controller.getForEntity();
        //controller.getForEntityTry();
    }
}
