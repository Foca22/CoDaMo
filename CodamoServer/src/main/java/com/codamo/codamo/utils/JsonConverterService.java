package com.codamo.codamo.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class JsonConverterService {

    ObjectMapper objectMapper;

    @PostConstruct
    void init() {
        objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @SneakyThrows
    public String convertToJson(Object obj) {
        return objectMapper.writeValueAsString(obj);
    }

    @SneakyThrows
    @SuppressWarnings("unchecked")
    public Object convertFromJson(String json, Class cls) {
        return objectMapper.readValue(json, cls);
    }
}
