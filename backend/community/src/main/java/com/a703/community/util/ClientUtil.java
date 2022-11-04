package com.a703.community.util;

import com.a703.community.dto.response.connection.DogInfoDto;
import com.a703.community.dto.response.connection.UserInfoDto;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Component
public class ClientUtil {


    public UserInfoDto requestUserInfo(Map<String,Object>token) throws Exception {

        String url = "http://localhost:8080/api/v1/test";
        RestTemplate restTemplate = new RestTemplate();

        // Header set
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.add("Authorization",(String) token.get("authorization"));

        // Body set
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();


        // Message
        HttpEntity<?> requestMessage = new HttpEntity<>(body, httpHeaders);

        // Request
        HttpEntity<String> response = restTemplate.getForEntity(url,String.class);

        // Response 파싱
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
        UserInfoDto userInfoDto = objectMapper.readValue(response.getBody(), UserInfoDto.class);

        return userInfoDto;
    }

    public List<Long> requestSearchDogInfo(int breedCode) throws Exception {

        String url = "http://localhost:8080/api/v1/test";
        RestTemplate restTemplate = new RestTemplate();

        // Header set
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        // Body set
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("breedCode", String.valueOf(breedCode));

        // Message
        HttpEntity<?> requestMessage = new HttpEntity<>(body, httpHeaders);

        // Request
        Long[] response = restTemplate.getForObject(url,Long[].class);

        // Response 파싱
//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
        List<Long> dogIdxList = Arrays.asList(response);

        return dogIdxList;
    }

    public List<DogInfoDto> requestDogInfo(List<Long> dogIdx) throws Exception {

        String url = "http://localhost:8080/api/v1/test";
        RestTemplate restTemplate = new RestTemplate();

        // Header set
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        // Body set
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("dogIdx", String.valueOf(dogIdx));

        // Message
        HttpEntity<?> requestMessage = new HttpEntity<>(body, httpHeaders);

        // Request
        DogInfoDto[] response = restTemplate.getForObject(url,DogInfoDto[].class);


        // Response 파싱
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
        List<DogInfoDto> dogInfoDto = Arrays.asList(response);

        return dogInfoDto;
    }

    //목록 보여줄 때 유저인덱스 하나씩 보내서 통신하는 것보다 리스트로 받는게 좋을 것 같음
    public String getUserName(int userIdx) throws Exception {

        String url = "http://localhost:8080/api/v1/test";
        RestTemplate restTemplate = new RestTemplate();

        // Header set
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        // Body set
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("userIdx", String.valueOf(userIdx));

        // Message
        HttpEntity<?> requestMessage = new HttpEntity<>(body, httpHeaders);

        // Request
        String response = restTemplate.getForObject(url,String.class);

        // Response 파싱
//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
        String userNaem = response;

        return userNaem;
    }

}