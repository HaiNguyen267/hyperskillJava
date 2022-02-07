package server;

import com.google.gson.JsonObject;

import java.util.Map;

public interface Command {
    JsonObject execute();
}
