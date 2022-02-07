package server;

import com.google.gson.*;

import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;



public class Database {
    private JsonObject jo;
    private ReadWriteLock lock = new ReentrantReadWriteLock();
    private Lock read = lock.readLock();
    private Lock write = lock.writeLock();

    public Database() {
        this.jo = new JsonObject();
        loadDatabase();
    }

    private void loadDatabase() {
        String fileName = "C:\\Users\\NKcomputer\\IdeaProjects\\JSON Database\\JSON Database\\task\\src\\server\\data\\db.json";

        try {
            JsonObject old = new JsonParser()
                    .parse(new String(Files.readAllBytes(Paths.get(fileName))))
                    .getAsJsonObject();
            if (old != null) {
                this.jo = old;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public JsonObject set(String keyString, String valueString) {
        write.lock();
        JsonObject parent = this.jo;

        // key can either string or JsonArray, value can be either string or JsonObject
        JsonArray keyJsonArray = Utility.parseJsonArray(keyString);
        JsonObject valueObject = Utility.parseJsonObject(valueString);
        String childKey;
        // if the key is JsonArray
        if (keyJsonArray != null) {
            String[] keyStringArray = Utility.parseStringArrayFromJsonArray(keyJsonArray);

            // go deeper into the nested objects
            for (int i = 0; i < keyStringArray.length - 1; i++) {
                String key = keyStringArray[i];

                // if the key doesn't exist, then create it
                if (parent.get(key).getAsJsonObject() == null) {
                    write.unlock();
                    return createNoSuchKeyResponse();
                }

                parent = parent.get(key).getAsJsonObject(); // go deeper to the nested jsonObject
            }

            childKey = keyStringArray[keyStringArray.length - 1];

        } else {
            childKey = keyString; // if the key is not jsonArray, it is a string, and it is the childKey itself.
        }

        if (valueObject != null) {
            // if the value is jsonObject, then the childKey is a key of an object
            parent.add(childKey,valueObject);
        } else {
            // if the valud is not jsonObject, then it is a string and the childKey is the key of a property

            if (valueString.startsWith("\"") && valueString.endsWith("\"")) {
                valueString = valueString.substring(1, valueString.length() - 1); // remove extra " if any
            }
            parent.addProperty(childKey, valueString);
        }

        JsonObject response = new JsonObject();
        response.addProperty("response", "OK");
        updateDatabase(); // update the database

        write.unlock();
        return response;
    }

    public JsonObject get(String keyString) {
        read.lock();
        JsonObject parent = this.jo;
        JsonArray keyJsonArray = Utility.parseJsonArray(keyString);
        String childKey;
        // if the key is jsonArray
        if (keyJsonArray != null) {
            String[] keyStringArray = Utility.parseStringArrayFromJsonArray(keyJsonArray);
            // go deeper into the nested objects
            for (int i = 0; i < keyStringArray.length - 1; i++) {
                String key = keyStringArray[i];

                // if there is a key doesn't exist, return NoSuchKeyResponse
                if (parent.get(key) == null) {
                    read.unlock();
                    return createNoSuchKeyResponse();
                }
                parent = parent.get(key).getAsJsonObject();
            }

            // the childKey is the last element in the keyArray
            childKey = keyStringArray[keyStringArray.length - 1];

        } else {
            // if the key is a string, it is the childKey
            childKey = keyString;
        }
        String valueString = parent.get(childKey).toString();

        if (valueString == null) {
            read.unlock();
            return createNoSuchKeyResponse();
        } else {
            JsonObject valueObject = Utility.parseJsonObject(valueString);
            JsonObject response = new JsonObject();
            response.addProperty("response", "OK");

            // if the valueString can be parsed to jsonObject, it is jsonObject, childKey is the key of an object
            if (valueObject != null) {
                response.add("value", valueObject);
            } else {
                // if the valueString is not jsonObject, childKey is the key of a property
                /*
                 remove extra " because it was added to make parent.get(childKey) to be a string
                 , but if it is a string already, it is added " again
                 , so extra " should be replaced in case parent.get(childKey) already returns a string
                 */
                valueString = valueString.substring(1, valueString.length() - 1);
                response.addProperty("value", valueString);
            }

            read.unlock();
            return response;
        }
    }

    public JsonObject delete(String keyString) {
        write.lock();
        JsonObject parent = this.jo;
        JsonArray keyJsonArray = Utility.parseJsonArray(keyString);
        String childKey;
        // if the key is jsonArray
        if (keyJsonArray != null) {
            String[] keyStringArray = Utility.parseStringArrayFromJsonArray(keyJsonArray);

            // go deeper into nested objects
            for (int i = 0; i < keyStringArray.length - 1; i++) {
                String key = keyStringArray[i];

                // if there is a key doesn't exist, return NoSuchKeyResponse
                if (parent.get(key).getAsJsonObject() == null) {
                    write.unlock();
                    return createNoSuchKeyResponse();
                }

                parent = parent.get(key).getAsJsonObject();
            }

            childKey = keyStringArray[keyStringArray.length - 1]; // the childKey is the last key in the keyArray
        } else {
            childKey = keyString; // if the key is a string, then it is childKey itself
        }

        if (childKey == null) {
            write.unlock();
            return createNoSuchKeyResponse();
        } else {
            parent.remove(childKey);
            updateDatabase(); // update the database

            JsonObject response = new JsonObject();
            response.addProperty("response", "OK");

            write.unlock();
            return response;
        }
    }

    private void updateDatabase() {
        String fileName = "C:\\Users\\NKcomputer\\IdeaProjects\\JSON Database\\JSON Database\\task\\src\\server\\data\\db.json";

        try (PrintWriter writer = new PrintWriter(fileName)) {
            String prettyJson = new GsonBuilder().setPrettyPrinting().create().toJson(this.jo);
            writer.println(prettyJson);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private JsonObject createNoSuchKeyResponse() {

        JsonObject response = new JsonObject();
        response.addProperty("response", "ERROR");
        response.addProperty("reason", "No such key");

        return response;
    }
}
