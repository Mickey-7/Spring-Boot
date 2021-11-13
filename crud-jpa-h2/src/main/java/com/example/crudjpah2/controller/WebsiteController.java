package com.example.crudjpah2.controller;

import com.example.crudjpah2.dto.WebsiteDto;
import com.example.crudjpah2.model.Website;
import com.example.crudjpah2.service.WebsiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class WebsiteController {
    @Autowired
    private WebsiteService websiteService;

    @GetMapping("/website/{id}")
    public ResponseEntity<WebsiteDto> getWebsite(@PathVariable Long id) {
        return new ResponseEntity<>(websiteService.getWebsiteById(id), HttpStatus.OK);
    }
    @GetMapping("/website")
    public ResponseEntity<List<WebsiteDto>> getWebsiteList() {
        return new ResponseEntity<>(websiteService.getWebsiteList(), HttpStatus.OK);
    }
    @PostMapping("/website")
    public ResponseEntity<String> saveWebsite(@RequestBody WebsiteDto websiteDto) {
        websiteService.saveWebsite(websiteDto);
        return new ResponseEntity<>("New website successfully saved", HttpStatus.OK);
    }
    @PutMapping("/website/{id}")
    public ResponseEntity<String> updateWebsite(@PathVariable Long id, @RequestBody WebsiteDto websiteDto) {
        websiteService.updateWebsite(id,websiteDto);
        return new ResponseEntity<>("New website successfully updated", HttpStatus.OK);
    }
    @DeleteMapping("/website/{id}")
    public ResponseEntity<String> deleteWebsite(@PathVariable Long id) {
        websiteService.delete(id);
        return new ResponseEntity<>("New website successfully deleted", HttpStatus.OK);
    }
}
