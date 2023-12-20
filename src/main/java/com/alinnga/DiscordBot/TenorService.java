package com.alinnga.DiscordBot;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;

@Service
public class TenorService {

    @Value("${tenor.key}")
    private String tenorKey;

    public String getRandomGif(String searchTerm){
        String url = "https://tenor.googleapis.com/v2/search?q=%s&key=%s&limit=1&random=true".formatted(searchTerm, tenorKey);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<TenorResult> tenorResultResponse =
                restTemplate.exchange(url,
                        HttpMethod.GET, null, new ParameterizedTypeReference<>() {
                        });
        return Objects.requireNonNull(tenorResultResponse.getBody()).getResults()[0].getUrl();
    }
}
