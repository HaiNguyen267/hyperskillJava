package server;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Utility {
    public static JsonArray parseJsonArray(String json) {
        try {
            JsonArray ja = new JsonParser().parse(json).getAsJsonArray();
            return ja;
        } catch (Exception e) {
            return null;
        }
    }

    public static JsonObject parseJsonObject(String json) {
        try {
            JsonObject jo = new JsonParser().parse(json).getAsJsonObject();
            return jo;
        } catch (Exception e) {
            return null;
        }
    }

    public static String[] parseStringArrayFromJsonArray(JsonArray keyJsonArray) {
        String[] stringArray = new String[keyJsonArray.size()];

        for (int i = 0; i < keyJsonArray.size(); i++) {
            String key = keyJsonArray.get(i).toString();
            stringArray[i] = key.substring(1, key.length() - 1); // remove extra "
        }
        return stringArray;
    }
}
