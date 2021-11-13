package com.example.bootserverresttemplate.controller;

import com.example.bootserverresttemplate.model.Address;
import com.example.bootserverresttemplate.model.Person;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/rest-template/data/")
public class ServerController {
    @Autowired
    private Gson gson;

    //getForObject
    @RequestMapping(value = "/fetch/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Person getForObject(@PathVariable(value = "id") Integer id){
        Address address = new Address("village1","district1","state1");
        return new Person(id,"person1",address);
    }

    //exchange
    @RequestMapping(value = "/exchange/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Person> exchange(@PathVariable(value = "id") Integer id){
        Address address = new Address("village2","district2","state2");
        Person person = new Person(id, "person2",address);
        return new ResponseEntity<>(person, HttpStatus.OK);
    }

    //headForHeaders
    @RequestMapping(value = "/fetch/{id}", method = RequestMethod.HEAD)
    public ResponseEntity<Void> getManuallyCreatedJSON(@PathVariable(value = "id") Integer id){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(headers, HttpStatus.NO_CONTENT);
    }

    //getForEntity
    @RequestMapping(value = "/fetch/{firstName}/{village}")
    public Person getForEntity(@PathVariable(value = "firstName") String name, @PathVariable(value = "village") String village){
        Address address = new Address(village,"district2","state2");
        return new Person(7,name,address);
    }

    //delete
    @RequestMapping(value = "/delete/{firstName}/{village}", method = RequestMethod.DELETE)
    public void delete(@PathVariable(value = "firstName") String firstName, @PathVariable(value = "village") String village){
        System.out.println("delete");
        System.out.println("deleting firstName : "+firstName+" and village : "+village);
        //Deleting name : Mahesh and village : Punjab
        //name and village value is from client
    }

    //put
    @RequestMapping(value = "/put/{id}/{name}", method = RequestMethod.PUT)
    public void put(@PathVariable(value = "id") Integer id, @PathVariable(value = "name") String name, @RequestBody Address address){
        System.out.println("====================");
        System.out.println("put");
        Person person = new Person();
        person.setId(id);
        person.setFirstName(name);
        person.setAddress(address);
        String jsonPerson = gson.toJson(person);
        System.out.println(jsonPerson);
        //{
        //  "id": 7,
        //  "firstName": "eminem",
        //  "address": {
        //    "village": "village3",
        //    "district": "district3",
        //    "state": "state3"
        //  }
        //}
        //all values are from client
    }

    //postForObject
    @RequestMapping(value = "/post/{id}/{name}",method = RequestMethod.POST)
    public ResponseEntity<Person> postForObject(@PathVariable(value = "id") Integer id,@PathVariable(value = "name") String name,@RequestBody Address address){
        Person person = new Person(id,name,address);
        System.out.println("====================");
        System.out.println("postForObject");
        String jsonPerson = gson.toJson(person);
        System.out.println(jsonPerson);
        return new ResponseEntity<>(person,HttpStatus.CREATED);
        //{
        //  "id": 7,
        //  "firstName": "gunner",
        //  "address": {
        //    "village": "village",
        //    "district": "district",
        //    "state": "state"
        //  }
        //}
        //all values are from client
    }

    //postForLocation
    @RequestMapping(value = "/uri/location/{id}/{name}",method = RequestMethod.POST)
    public ResponseEntity<Void> postForLocation(
            @PathVariable(value = "id") Integer id,
            @PathVariable(value = "name") String name,
            @RequestBody Address address,
            UriComponentsBuilder builder
    ){
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/uri/location/{id}/{name}")
                .buildAndExpand(id,name).toUri());
        System.out.println("====================");
        System.out.println("postForLocation");
        String jsonHeaders = gson.toJson(headers);
        System.out.println(jsonHeaders);
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
        //{
        //  "Location": [
        //    "http://localhost:8080/uri/location/7/gunner"
        //  ]
        //}
    }

    //postForEntity
    @RequestMapping(value="/saveinfo/{id}/{name}", method = RequestMethod.POST)
    public ResponseEntity<Person> saveInfo(@PathVariable(value = "id") Integer id,
                                           @PathVariable(value = "name") String name,
                                           @RequestBody Address address) {
        Person person = new Person(id, name, address);
        System.out.println("====================");
        System.out.println("postForEntity");
        String jsonPserson = gson.toJson(person);
        System.out.println(jsonPserson);
        return new ResponseEntity<>(person, HttpStatus.CREATED);
        //{
        //  "id": 111,
        //  "firstName": "Shyam",
        //  "address": {
        //    "village": "Dhananjaypur",
        //    "district": "Varanasi",
        //    "state": "UP"
        //  }
        //}
    }

    //method to test
    @RequestMapping(value = "/error", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> errorMethod(@RequestBody List<Address> addresses){
        System.out.println("====================");
        System.out.println("errorMethod");
        return new ResponseEntity<>("error method test",HttpStatus.NOT_FOUND);
    }
}
