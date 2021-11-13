package com.example.bootserverresttemplate.model;

import com.fasterxml.jackson.annotation.JsonAnySetter;

public class Person {
    private Integer id;
    private String firstName;
    private Address address;

    public Person() {
    }

    public Person(Integer id, String firstName, Address address) {
        this.id = id;
        this.firstName = firstName;
        this.address = address;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }


    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", address=" + address +
                '}';
    }
}
