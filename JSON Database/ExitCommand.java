package server;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.LinkedHashMap;
import java.util.Map;

public class ExitCommand implements Command{
    @Override
    public JsonObject execute() {
        JsonObject response = new JsonObject();
        response.addProperty("response", "OK");
        return response;
    }
}
