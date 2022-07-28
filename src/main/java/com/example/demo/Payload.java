package com.example.demo;

import java.util.Map;

public class Payload {

    String identifier;

    Map<String, Object> data;


    public Payload() {
    }

    public Payload(String identifier, Map<String, Object> data) {
        this.identifier = identifier;
        this.data = data;
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
