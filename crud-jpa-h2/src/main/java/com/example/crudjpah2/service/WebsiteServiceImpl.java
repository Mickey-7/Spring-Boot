package com.example.crudjpah2.service;

import com.example.crudjpah2.dto.WebsiteDto;
import com.example.crudjpah2.model.Website;
import com.example.crudjpah2.repository.WebsiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WebsiteServiceImpl implements WebsiteService{
    @Autowired
    private WebsiteRepository websiteRepository;

    @Override
    public List<WebsiteDto> getWebsiteList() {
        List<Website> websites = websiteRepository.findAll();
        List<WebsiteDto> websiteDtos = websites.stream()
                .map(website ->
                    new WebsiteDto(website.getId(),website.getName(),website.getUrl())
                ).collect(Collectors.toList());
        return websiteDtos;
    }

    @Override
    public WebsiteDto getWebsiteById(Long id) {
        Website website = websiteRepository.findById(id).orElse(null);
        WebsiteDto websiteDto = new WebsiteDto();
        websiteDto.setId(id);
        websiteDto.setName(website.getName());
        websiteDto.setUrl(website.getUrl());
        return websiteDto;
    }

    @Override
    public void saveWebsite(WebsiteDto websiteDto) {
        Website website = new Website();
        website.setName(websiteDto.getName());
        website.setUrl(websiteDto.getUrl());
        websiteRepository.save(website);
    }

    @Override
    public void updateWebsite(Long id, WebsiteDto websiteDto) {
        websiteRepository.findById(id).map(
                website1 -> {
                    website1.setName(websiteDto.getName());
                    website1.setUrl(websiteDto.getUrl());
                    return websiteRepository.save(website1);
                }
        );
    }

    @Override
    public void delete(Long id) {
        websiteRepository.deleteById(id);
    }
}
