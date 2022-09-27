package com.example.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/app")
public class AppController {

    final ObjectMapper mapper;

    AppController(ObjectMapper mapper) {
        this.mapper = mapper;
        this.mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
    }

    @PostMapping
    public String app(@RequestBody Map<String, Object> request) throws Exception {

        Map<String, Object> response = new HashMap<>();
        flatteningJson("", request, response);
        return mapper.writeValueAsString(response);
    }

    private void flatteningJson(String key, Map<String, Object> data, Map<String, Object> result) {
        data.entrySet().iterator().forEachRemaining((nxt) -> {
            String currentKey = key + (key.isEmpty() ? "" : '.') + nxt.getKey();
            Object value = nxt.getValue();

            if (value instanceof Map) {
                flatteningJson(currentKey, ((Map<String, Object>) value), result);
            } else {
                result.put(currentKey, value);
            }
        });
    }
}
