package com.example.client.model;

import java.sql.Date;
import java.sql.Time;

public class Context {
    private String interfaceId;
    private String channelId;
    private String channelRefNumber;
    private String reqSequenceNumber;
    private Date requestDate;
    private Time requestTime;


    public Context() {
    }

    public String getInterfaceId() {
        return interfaceId;
    }

    public void setInterfaceId(String interfaceId) {
        this.interfaceId = interfaceId;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getChannelRefNumber() {
        return channelRefNumber;
    }

    public void setChannelRefNumber(String channelRefNumber) {
        this.channelRefNumber = channelRefNumber;
    }

    public String getReqSequenceNumber() {
        return reqSequenceNumber;
    }

    public void setReqSequenceNumber(String reqSequenceNumber) {
        this.reqSequenceNumber = reqSequenceNumber;
    }

    public Date getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }

    public Time getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(Time requestTime) {
        this.requestTime = requestTime;
    }

    public Context(String interfaceId, String channelId, String channelRefNumber, String reqSequenceNumber, Date requestDate, Time requestTime) {
        this.interfaceId = interfaceId;
        this.channelId = channelId;
        this.channelRefNumber = channelRefNumber;
        this.reqSequenceNumber = reqSequenceNumber;
        this.requestDate = requestDate;
        this.requestTime = requestTime;
    }

    @Override
    public String toString() {
        return "Context{" +
                "interfaceId='" + interfaceId + '\'' +
                ", channelId='" + channelId + '\'' +
                ", channelRefNumber='" + channelRefNumber + '\'' +
                ", reqSequenceNumber='" + reqSequenceNumber + '\'' +
                ", requestDate=" + requestDate +
                ", requestTime=" + requestTime +
                '}';
    }
}
