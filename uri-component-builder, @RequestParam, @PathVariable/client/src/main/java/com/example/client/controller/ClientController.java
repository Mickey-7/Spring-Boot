package com.example.client.controller;

import com.example.client.model.Context;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

@RestController
public class ClientController {

    private static final String url = "http://localhost:8080/bill-payment/";

    @Autowired
    private Gson gson;

    public Context getContext(){
        Context context = new Context();
        context.setInterfaceId("101");
        context.setChannelId("123");
        context.setChannelRefNumber("456");
        context.setReqSequenceNumber("789");
        context.setRequestDate(Date.valueOf(LocalDate.now()));
        context.setRequestTime(Time.valueOf(LocalTime.now()));
        return context;
    }

    //for @RequestParam consumption - using UriComponentsBuilder
    //https://stackoverflow.com/questions/8297215/spring-resttemplate-get-with-parameters
    public void usingUriCompBuilder(){

        String urlTemplate = UriComponentsBuilder
                .fromHttpUrl(url+"/status?")
                .queryParam("interfaceId","{interfaceId}")
                .queryParam("channelId","{channelId}")
                .queryParam("channelRefNumber","{channelRefNumber}")
                .queryParam("reqSequenceNumber","{reqSequenceNumber}")
                .queryParam("requestDate","{requestDate}")
                .queryParam("requestTime","{requestTime}")
                .encode()
                .toUriString();

        Map<String,Object> params = new HashMap<>();
        Context context = getContext();
        params.put("interfaceId",context.getInterfaceId());
        params.put("channelId",context.getChannelId());
        params.put("channelRefNumber",context.getChannelRefNumber());
        params.put("reqSequenceNumber",context.getReqSequenceNumber());
        params.put("requestDate",context.getRequestDate());
        params.put("requestTime",context.getRequestTime());

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Context> responseEntity = restTemplate.getForEntity(urlTemplate,Context.class,params);
        String json = gson.toJson(responseEntity);
        System.out.println(json);
        System.out.println(responseEntity.getStatusCode().toString());
    }


    //for @RequestParam consumption
    public void getForEntity(){
        Context context = getContext();
        Map<String,Object> map = new HashMap<>();
        map.put("interfaceId",context.getInterfaceId());
        map.put("channelId",context.getChannelId());
        map.put("channelRefNumber",context.getChannelRefNumber());
        map.put("reqSequenceNumber",context.getReqSequenceNumber());
        map.put("requestDate",context.getRequestDate());
        map.put("requestTime",context.getRequestTime());
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Context> responseEntity = restTemplate.getForEntity(url+
                "/status?" +
                "interfaceId={interfaceId}" +
                "&channelId={channelId}" +
                "&channelRefNumber={channelRefNumber}" +
                "&reqSequenceNumber={reqSequenceNumber}" +
                "&requestDate={requestDate}" +
                "&requestTime={requestTime}",
                Context.class,map);
        String json = gson.toJson(responseEntity);
        System.out.println(json);
        System.out.println(responseEntity.getStatusCode().toString());

    }




    //for @PathVariable
    public void getForEntityTry() {
        Map<String, String> map = new HashMap<>();
        map.put("interfaceId", "123");
        map.put("channelId", "456");
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Context> responseEntity = restTemplate.getForEntity(url + "/fetch/{interfaceId}/{channelId}", Context.class, map);
        String jsonResponse = responseEntity.toString();
        System.out.println(jsonResponse);
    }
}
