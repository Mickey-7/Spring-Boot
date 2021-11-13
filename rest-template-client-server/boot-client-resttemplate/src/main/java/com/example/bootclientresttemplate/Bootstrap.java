package com.example.bootclientresttemplate;

import com.example.bootclientresttemplate.controller.ClientController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class Bootstrap {

    @Autowired
    private ClientController clientController;

    @EventListener(ContextRefreshedEvent.class)
    public void onInit() {
        System.out.println("getForObject");
        clientController.getForObject();
        System.out.println("=====================");
        System.out.println("exchange");
        clientController.exchange();
        System.out.println("=====================");
        System.out.println("headForHeaders");
        clientController.headForHeaders();
        System.out.println("=====================");
        System.out.println("getForEntity");
        clientController.getForEntity();
        System.out.println("=====================");
        System.out.println("delete");
        clientController.delete();
        System.out.println("=====================");
        System.out.println("put");
        clientController.put();
        System.out.println("=====================");
        System.out.println("postForObject");
        clientController.postForObject();
        System.out.println("=====================");
        System.out.println("postForLocation");
        clientController.postForLocation();
        System.out.println("=====================");
        System.out.println("postForEntity");
        clientController.saveInfo();
        System.out.println("=====================");
        System.out.println("errorMethod");
        clientController.errorMethod();
    }

}
