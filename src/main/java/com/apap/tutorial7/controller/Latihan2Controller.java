package com.apap.tutorial7.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.apap.tutorial7.rest.Setting;

@RestController
public class Latihan2Controller {

    @Autowired
    private RestTemplate restTemplate;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }


    @GetMapping(value = "/model")
    private  ResponseEntity<String> getStatus(@RequestParam("factory") String namaProdusen) throws Exception{
        String path = Setting.produsenUrl + "&make=" + namaProdusen;
        System.out.println(path);
        ResponseEntity<String> response = restTemplate.getForEntity(path, String.class);
        return response;
    }
}