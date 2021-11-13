package com.example.crudjpah2.service;

import com.example.crudjpah2.dto.WebsiteDto;
import com.example.crudjpah2.model.Website;

import java.util.List;

public interface WebsiteService {
    List<WebsiteDto> getWebsiteList();
    WebsiteDto getWebsiteById(Long id);
    void saveWebsite(WebsiteDto websiteDto);
    void updateWebsite(Long id, WebsiteDto websiteDto);
    void delete(Long id);

}
