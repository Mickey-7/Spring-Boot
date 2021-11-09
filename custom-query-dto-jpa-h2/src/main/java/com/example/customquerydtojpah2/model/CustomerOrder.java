package com.example.customquerydtojpah2.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CustomerOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String description;
    private String orderStatus;
    private Integer customerId;

    public CustomerOrder() {
    }

    public CustomerOrder(Integer id, String name, String description, String orderStatus, Integer customerId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.orderStatus = orderStatus;
        this.customerId = customerId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    @Override
    public String toString() {
        return "CustomerOrder{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", orderStatus='" + orderStatus + '\'' +
                ", customerId=" + customerId +
                '}';
    }
}
