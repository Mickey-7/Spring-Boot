package com.example.bootclientresttemplate.controller;

import com.example.bootclientresttemplate.model.Address;
import com.example.bootclientresttemplate.model.Person;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ClientController {
    private static final String url = "http://localhost:8080/rest-template/data/";
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private Gson gson;

    //getForObject
    //getForObject() : It retrieves an entity using HTTP GET method on the given URL.
    public void getForObject(){
        Person person = restTemplate.getForObject(url+"/fetch/{id}",Person.class, 7);
        String jsonPerson = gson.toJson(person);
        System.out.println(jsonPerson);
        //{
        //  "id": 7,
        //  "address": {
        //    "village": "village1",
        //    "district": "district1",
        //    "state": "state1"
        //  }
        //}
        //notice that the name is null because the field name on the server is firstName while on the client is name only
        //need to use @JsonProperty("firstName")
        //{
        //  "id": 7,
        //  "name": "person1",
        //  "address": {
        //    "village": "village1",
        //    "district": "district1",
        //    "state": "state1"
        //  }
        //}
    }

    //exchange
    //exchange() : Executes the HTTP method for the given URI. It returns ResponseEntity. It can communicate using any HTTP method.
    public void exchange(){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> requestEntity = new HttpEntity<>("This is request body", headers);
        ResponseEntity<Person> responseEntity = restTemplate.exchange(url+"/exchange/{id}", HttpMethod.GET, requestEntity,Person.class, 7);
        String jsonResponse = gson.toJson(responseEntity);
        System.out.println(jsonResponse);
        //{
        //  "status": 200,
        //  "headers": {
        //    "Content-Type": [
        //      "application/json"
        //    ],
        //    "Transfer-Encoding": [
        //      "chunked"
        //    ],
        //    "Date": [
        //      "Sun, 07 Nov 2021 02:25:04 GMT"
        //    ],
        //    "Keep-Alive": [
        //      "timeout\u003d60"
        //    ],
        //    "Connection": [
        //      "keep-alive"
        //    ]
        //  },
        //  "body": {
        //    "id": 7,
        //    "name": "person2",
        //    "address": {
        //      "village": "village2",
        //      "district": "district2",
        //      "state": "state2"
        //    }
        //  }
        //}
    }

    //headForHeaders
    //headForHeaders() : Retrieves all headers. It uses HTTP HEAD method.
    public void headForHeaders(){
        HttpHeaders headers = restTemplate.headForHeaders(url+"/fetch/{id}", 100);
        String jsonHeaders = gson.toJson(headers);
        System.out.println(jsonHeaders);
        //{
        //  "Content-Type": [
        //    "application/json"
        //  ],
        //  "Date": [
        //    "Sun, 07 Nov 2021 02:28:29 GMT"
        //  ],
        //  "Keep-Alive": [
        //    "timeout\u003d60"
        //  ],
        //  "Connection": [
        //    "keep-alive"
        //  ]
        //}
    }

    //getForEntity
    //getForEntity() : It retrieves an entity by using HTTP GET method for the given URL. It returns ResponseEntity.
    public void getForEntity(){
        Map<String,String> map = new HashMap<>();
        map.put("name","Mahesh");
        map.put("village","Punjab");
        ResponseEntity<Person> responseEntity = restTemplate.getForEntity(url+"/fetch/{name}/{village}", Person.class, map);
        String jsonResponse = gson.toJson(responseEntity);
        System.out.println(jsonResponse);
        //{
        //  "status": 200,
        //  "headers": {
        //    "Content-Type": [
        //      "application/json"
        //    ],
        //    "Transfer-Encoding": [
        //      "chunked"
        //    ],
        //    "Date": [
        //      "Sun, 07 Nov 2021 02:33:20 GMT"
        //    ],
        //    "Keep-Alive": [
        //      "timeout\u003d60"
        //    ],
        //    "Connection": [
        //      "keep-alive"
        //    ]
        //  },
        //  "body": {
        //    "id": 7,
        //    "name": "Mahesh",
        //    "address": {
        //      "village": "Punjab",
        //      "district": "district2",
        //      "state": "state2"
        //    }
        //  }
        //}
    }

    //delete
    //delete() : Deletes the resources at the given URL. It uses HTTP DELETE method.
    public void delete(){
        Map<String,String> map = new HashMap<>();
        map.put("name","Mahesh");
        map.put("village","Punjab");
        restTemplate.delete(url+"/delete/{name}/{village}",map);
    }

    //put
    //put(): It creates new resource or update for the given URL using HTTP PUT method.
    public void put(){
        Map<String,String> map = new HashMap<>();
        map.put("id","7");
        map.put("name","eminem");
        Address address = new Address("village3","district3","state3");
        restTemplate.put(url+"/put/{id}/{name}",address,map);
    }

    //postForObject()
    //postForObject(): It creates new resource using HTTP PATCH method and returns an entity.
    public void postForObject(){
        Map<String, String> map = new HashMap<>();
        map.put("id","7");
        map.put("name","gunner");
        Address address = new Address("village","district","state");
        Person person = restTemplate.postForObject(url+"/post/{id}/{name}",address,Person.class,map);
        String jsonPerson = gson.toJson(person);
        System.out.println(jsonPerson);
        //{
        //  "id": 7,
        //  "name": "gunner",
        //  "address": {
        //    "village": "village",
        //    "district": "district",
        //    "state": "state"
        //  }
        //}

    }


    //postForLocation
    //postForLocation(): It creates new resource using HTTP POST method and returns the location of created new resource.
    public void postForLocation(){
        Address address = new Address("village4","district4","state4");
        URI uri = restTemplate.postForLocation(url+"/uri/location/{id}/{name}",address,7,"gunner");
        String jsonUri = gson.toJson(uri.getPath());
        System.out.println(jsonUri);
        //"/uri/location/7/gunner"
    }


    //postForEntity(): It creates news resource using HTTP POST method to the given URI template. It returns ResponseEntity.
    public void saveInfo(){
        Map<String, String> map = new HashMap<String, String>();
        map.put("id", "111");
        map.put("name", "Shyam");
        Address address = new Address("Dhananjaypur", "Varanasi", "UP");
        ResponseEntity<Person> entity= restTemplate.postForEntity(url+"/saveinfo/{id}/{name}", address, Person.class, map);
        String jsonEntity = gson.toJson(entity);
        System.out.println(jsonEntity);
        //{
        //  "status": 201,
        //  "headers": {
        //    "Content-Type": [
        //      "application/json"
        //    ],
        //    "Transfer-Encoding": [
        //      "chunked"
        //    ],
        //    "Date": [
        //      "Sun, 07 Nov 2021 03:01:47 GMT"
        //    ],
        //    "Keep-Alive": [
        //      "timeout\u003d60"
        //    ],
        //    "Connection": [
        //      "keep-alive"
        //    ]
        //  },
        //  "body": {
        //    "id": 111,
        //    "name": "Shyam",
        //    "address": {
        //      "village": "Dhananjaypur",
        //      "district": "Varanasi",
        //      "state": "UP"
        //    }
        //  }
        //}

    }


    //method to test RestTemplateResponseErrorHandler
    public void errorMethod(){
        List<Address> addresses = new ArrayList<>();
        Address address = new Address("add","dist","Sta");
        addresses.add(address);
        Address address1 = new Address("add1","dist","Sta");
        addresses.add(address1);
        Address address2 = new Address("add2","dist","Sta");
        addresses.add(address2);
        HttpEntity<List<Address>> requestEntity = new HttpEntity<List<Address>>(addresses);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url+"/error",HttpMethod.POST,requestEntity,String.class);
        String jsonResponse = gson.toJson(responseEntity);
        System.out.println(jsonResponse);
        //2021-11-07 11:35:00.088  INFO 10092 --- [           main] c.e.b.u.RestTemplateResponseErrorHandler : Not Found
        //{
        //  "status": 404,
        //  "headers": {
        //    "Content-Type": [
        //      "text/plain;charset\u003dUTF-8"
        //    ],
        //    "Content-Length": [
        //      "17"
        //    ],
        //    "Date": [
        //      "Sun, 07 Nov 2021 03:34:59 GMT"
        //    ],
        //    "Keep-Alive": [
        //      "timeout\u003d60"
        //    ],
        //    "Connection": [
        //      "keep-alive"
        //    ]
        //  },
        //  "body": "error method test"
        //}
        //
        //Not Found on logger is from util class
        //body if from server
    }
}
