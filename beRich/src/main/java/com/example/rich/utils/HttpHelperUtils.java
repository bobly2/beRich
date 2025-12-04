package com.example.rich.utils;


import com.fasterxml.jackson.core.JsonProcessingException;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.InetSocketAddress;
import java.net.Proxy;


/**
 * @Author: SC19002999
 * @Description:
 * @Date: 2021/12/2 9:49
 * @Version: 1.0
 */
@Slf4j
@Component
public class HttpHelperUtils {
    @Autowired
    private ObjectMapper objectMapper;

    public <T, TR> String postForMy(String url, TR request) throws JsonProcessingException {
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setConnectTimeout(60 * 1000);// 设置超时
        requestFactory.setReadTimeout(60 * 1000);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(org.springframework.http.MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<TR> httpEntity = new HttpEntity<TR>(request, headers);
        RestTemplate restTemplate = new RestTemplate(requestFactory);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, httpEntity, String.class);
        return responseEntity.getBody();
    }

    public <T, TR> T postMult(String url, TR request, Class<T> type) throws JsonProcessingException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(org.springframework.http.MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<TR> httpEntity = new HttpEntity<TR>(request, headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, httpEntity, String.class);
        T result = objectMapper.readValue(responseEntity.getBody(), type);
        return result;
    }

    public String getMethods(String url) {
        RestTemplate restTemplate = new RestTemplate();
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setProxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress("localhost", 7897)));
        restTemplate.setRequestFactory(requestFactory);
        return restTemplate.getForObject(url, String.class);
    }

    public String getMethods2(String url) {
        return getMethods(url);
    }
}
