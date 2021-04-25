package br.com.nubank.application;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public interface JsonParser {

    static <T> T parseJsonToObject(String json, Class<T> clazz) {
        try {
            return objectMapper().readValue(json, clazz);
        } catch (Exception e) {
            return null;
        }
    }

    static String parseObjectToJson(Object object) {
        try {
            return objectMapper().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            return null;
        }
    }

    static ObjectMapper objectMapper() {
        return new JsonMapper()
                .registerModule(new JavaTimeModule())
                .enable(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES)
                .enable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
                .disable(SerializationFeature.WRITE_DATE_KEYS_AS_TIMESTAMPS);
    }
}
