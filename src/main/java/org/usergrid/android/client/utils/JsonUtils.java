package org.usergrid.android.client.utils;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.JsonNodeFactory;

import android.util.Log;

public class JsonUtils {

	private static final String TAG = "UsergridJsonUtils";

	static ObjectMapper mapper = new ObjectMapper();

	public static String getStringProperty(Map<String, JsonNode> properties,
			String name) {
		JsonNode value = properties.get(name);
		if (value != null) {
			return value.asText();
		}
		return null;
	}

	public static void setStringProperty(Map<String, JsonNode> properties,
			String name, String value) {
		if (value == null) {
			properties.remove(name);
		} else {
			properties.put(name, JsonNodeFactory.instance.textNode(value));
		}
	}

	public static Boolean getBooleanProperty(Map<String, JsonNode> properties,
			String name) {
		JsonNode value = properties.get(name);
		if (value != null) {
			return value.asBoolean();
		}
		return false;
	}

	public static void setBooleanProperty(Map<String, JsonNode> properties,
			String name, Boolean value) {
		if (value == null) {
			properties.remove(name);
		} else {
			properties.put(name, JsonNodeFactory.instance.booleanNode(value));
		}
	}

	public static UUID getUUIDProperty(Map<String, JsonNode> properties,
			String name) {
		JsonNode value = properties.get(name);
		if (value != null) {
			UUID uuid = null;
			try {
				uuid = UUID.fromString(value.asText());
			} catch (Exception e) {
			}
			return uuid;
		}
		return null;
	}

	public static void setUUIDProperty(Map<String, JsonNode> properties,
			String name, UUID value) {
		if (value == null) {
			properties.remove(name);
		} else {
			properties.put(name,
					JsonNodeFactory.instance.textNode(value.toString()));
		}
	}

	public static String toJsonString(Object obj) {
		try {
			return mapper.writeValueAsString(obj);
		} catch (JsonGenerationException e) {
			Log.e(TAG, "ERROR: " + e.getLocalizedMessage());
		} catch (JsonMappingException e) {
			Log.e(TAG, "ERROR: " + e.getLocalizedMessage());
		} catch (IOException e) {
			Log.e(TAG, "ERROR: " + e.getLocalizedMessage());
		}
		return "{}";
	}

	public static <T> T parse(String json, Class<T> c) {
		try {
			return mapper.readValue(json, c);
		} catch (JsonParseException e) {
			Log.e(TAG, "ERROR: " + e.getLocalizedMessage());
		} catch (JsonMappingException e) {
			Log.e(TAG, "ERROR: " + e.getLocalizedMessage());
		} catch (IOException e) {
			Log.e(TAG, "ERROR: " + e.getLocalizedMessage());
		}
		return null;
	}

}
