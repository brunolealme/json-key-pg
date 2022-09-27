package com.example.demo;

import java.util.HashMap;
import java.util.Map;

public class Payload {

    String identifier;

    Map<String, Object> data;

    private Payload(String identifier, Map<String, Object> data) {
        this.identifier = identifier;
        this.data = data;
    }

    private Payload(Map<String, Object> data) {
        this.data = data;
    }

    public static Payload createPayload() {
        Map<String, Object> data = new HashMap<>();
        data.put("endereco", Map.of("estado", Map.of("UF", "MA")));
        return new Payload(data);
    }


    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }
}
