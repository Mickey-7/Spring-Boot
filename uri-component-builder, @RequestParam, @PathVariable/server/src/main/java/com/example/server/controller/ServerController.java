package com.example.server.controller;

import com.example.server.model.Context;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.sql.Time;

@RestController
@RequestMapping("/bill-payment/")
public class ServerController {

    //using @RequestParam
    @RequestMapping(value = "/status")
    public Context getForEntity(
            @RequestParam(value = "interfaceId") String interfaceId,
            @RequestParam(value = "channelId") String channelId,
            @RequestParam(value = "channelRefNumber") String channelRefNumber,
            @RequestParam(value = "reqSequenceNumber") String reqSequenceNumber,
            @RequestParam(value = "requestDate") Date requestDate,
            @RequestParam(value = "requestTime") Time requestTime
    ){
        Context context = new Context();
        context.setInterfaceId(interfaceId);
        context.setChannelId(channelId);
        context.setChannelRefNumber(channelRefNumber);
        context.setReqSequenceNumber(reqSequenceNumber);
        context.setRequestDate(requestDate);
        context.setRequestTime(requestTime);
        System.out.println(context);
        return context;
    }


    //using @PathVariable
    @RequestMapping(value = "/fetch/{interfaceId}/{channelId}")
    public Context getForEntityTry(
            @PathVariable(value = "interfaceId") String interfaceId,
            @PathVariable(value = "channelId") String channelId
    ){
        Context context = new Context();
        context.setInterfaceId(interfaceId);
        context.setChannelId(channelId);
        return context;
    }
}
