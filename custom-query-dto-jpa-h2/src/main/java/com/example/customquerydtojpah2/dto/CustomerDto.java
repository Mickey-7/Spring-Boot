package com.example.customquerydtojpah2.dto;

public class CustomerDto {
   private Integer id;
   private String country;

    public CustomerDto() {
    }

    public CustomerDto(Integer id, String country) {
        this.id = id;
        this.country = country;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
