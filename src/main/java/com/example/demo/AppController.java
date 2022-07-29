package com.example.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
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
    }

    @PostMapping
    public String app(@RequestBody Payload payload) throws Exception {
        Map<String, Object> response = new HashMap<>();

        var json = mapper.writeValueAsString(payload);
        JSONObject object = extractNode(json);

        var names = object.keys();

        //campos flat
        while (names.hasNext()) {
            var name = names.next();
            var field = object.get(name);

            //checa composicao
            if (field instanceof JSONObject) {
                var nestedField = object.getJSONObject(name).toMap();
                // campos composicao
                nestedField.forEach((k, v) -> response.put(k, v));
            }
            response.put(name, field);
        }


        return mapper.writeValueAsString(response);
    }

    private JSONObject extractNode(String key) {
        return new JSONObject(key);
    }

}
