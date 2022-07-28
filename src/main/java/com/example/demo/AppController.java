package com.example.demo;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Service
@RestController
@RequestMapping("/app")
public class AppController {

    final ObjectMapper mapper;

    AppController(ObjectMapper mapper) {
        this.mapper = mapper;
    }


    @PostMapping
    public String app(@RequestBody Payload payload) throws Exception {
        var json = mapper.writeValueAsString(payload);

        var jsonNode = mapper.readTree(json);



        var names = jsonNode.fieldNames();

        while (names.hasNext()) {
           var a = jsonNode.get(names.next()).getNodeType();
            a.name();
//            if (jsonNode.get(names.next()) instanceof JSONPObject) {
//
//            }
        }


        jsonNode.get()


        return null;
    }

}
