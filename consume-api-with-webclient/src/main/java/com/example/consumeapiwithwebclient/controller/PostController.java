package com.example.consumeapiwithwebclient.controller;

import com.example.consumeapiwithwebclient.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

@RestController
public class PostController {
    private final String POST_OF_API="https://jsonplaceholder.typicode.com/posts/9";
    private final String POST_BY_ID_API="https://jsonplaceholder.typicode.com/posts/{iid}";
    private final String POST_API="https://jsonplaceholder.typicode.com/posts";

    @Autowired
    private WebClient.Builder webClientBuilder;

    /**
     * getPost() method call the defined API (static post)
     */
    @GetMapping("/consume")
    public Post getPost(){
        return webClientBuilder
                .build()
                .get()
                .uri(POST_OF_API)
                .retrieve()
                .bodyToMono(Post.class)
                .block();
    }
    //The description of the method:
    //build() method build a WebClient instance.
    //get() method denote, you are making an HTTP.GET request. You can change it accordingly like post(), put(), delete() etc.
    //uri() method specifies the URI (API) that we wish to consume.
    //retrieve() method perform the HTTP request and retrieve the response body.
    //bodyToMono(YourPOJOClass.class) method map the response of the API to the POJO class.
    //block() method return the value

    /**
     * getPostById() method call the API with the post id send by the user (dynamic post)
     */
    @GetMapping("/consume/{id}")
    public Post getPostById(@PathVariable("id") Integer postId){
        return webClientBuilder
                .build()
                .get()
                .uri(POST_BY_ID_API,postId)
                .retrieve()
                .bodyToMono(Post.class)
                .block();
    }

    /**
     * getAllPost() method call the API which returns the array or list of post
     */
    @GetMapping("/consume/all")
    public Post[] getAllPost(){
        return webClientBuilder
                .build()
                .get()
                .uri(POST_API)
                .retrieve()
                .bodyToMono(Post[].class)
                .block();
    }
}
