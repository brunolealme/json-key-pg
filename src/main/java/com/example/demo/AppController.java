package com.example.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
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
        this.mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
    }

    @PostMapping
    public String app(@RequestBody Payload payload) throws Exception {
        Map<String, Object> response = new HashMap<>();

        var json = mapper.writeValueAsString(payload);
        JSONObject object = new JSONObject(json);

        fillFlatJson(response, object);
        return mapper.writeValueAsString(response);
    }

    private void fillFlatJson(Map<String, Object> response, JSONObject object) {
        var keys = object.keys();

        //campos flat
        while (keys.hasNext()) {
            var name = keys.next();
            var field = object.get(name);

            //checa composicao
            if (field instanceof JSONObject) {
                var nestedField = object.getJSONObject(name).toMap();

              //  fillFlatJson(nestedField, field);
                // campos composicao
                nestedField.forEach((k, v) -> response.put(name.concat("_").concat(k), v));
                continue;
            }
            response.put(name, field);
        }

    }

}
