package com.example.demo;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;


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

		Iterator<String> iterator = data.keySet().iterator();
		data.entrySet().stream()
				.filter(val -> val instanceof Map)
				.flatMap((x) -> x.)

				.collect(Collectors.toList());
	}
	@Test
	void callCode(){
		flatten(json.toMap());


	}


	@SuppressWarnings("unchecked")//recursive helper method
	private static Map<String, Object> flatten(final String key, final Map<String, Object> map,
											   final Map<String, Object> result) {
		final Set<Map.Entry<String, Object>> entries = map.entrySet();
		if (!entries.isEmpty()) {
			for (final Map.Entry<String, Object> entry : entries) {
				//iterate over entries
				final String currKey = key + (key.isEmpty() ? "" : '.') + entry.getKey();
				//append current key to previous key, adding a dot if the previous key was not an empty String
				final Object value = entry.getValue();
				if (value instanceof Map) {//current value is a Map
					flatten(currKey, (Map<String, Object>) value, result);//flatten Map
				} else if (value instanceof List) {//current value is a List
					final List<Object> list = (List<Object>) value;
					for (int i = 0, size = list.size(); i < size; i++) {
						result.put(currKey + '.' + (i + 1), list.get(i));
					}
					//iterate over the List and append the index to the current key when setting value
				} else {
					result.put(currKey, value);//set normal value
				}
			}
		}
		return result;
	}
	public static Map<String, Object> flatten(final Map<String, Object> map) {
		return flatten("", map, new HashMap<>());
		//use new TreeMap<>() to order map based on key
	}


}
