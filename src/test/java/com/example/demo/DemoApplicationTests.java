package com.example.demo;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;


class DemoApplicationTests {
	JSONObject json;

	@BeforeEach
	void setUp() {
		json = new JSONObject(Payload.createPayload());
	}

	//checar se JsonObject ou se MAP
// appendar o nome da key

	// -> chegar até o último nome da key
// fazer um put em MAp
	@Test
	void shouldFlatJson() {
		Map<String, Object> data = json.toMap();
		Map<String, Object> result = new HashMap<>();
		generateFlatMap("", data, result);

		System.out.println(result);
	}

	private void generateFlatMap(String key, Map<String, Object> data, Map<String, Object> result) {
		data.entrySet().iterator().forEachRemaining((nxt) -> {
			String currentKey = key + (key.isEmpty() ? "" : '.') + nxt.getKey();
			Object value = nxt.getValue();

			if (value instanceof Map) {
				generateFlatMap(currentKey, ((Map<String, Object>) value), result);
			} else {
				result.put(currentKey, value);
			}
		});

	}


}