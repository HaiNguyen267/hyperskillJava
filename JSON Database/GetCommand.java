package server;

import com.google.gson.JsonObject;

import java.util.Map;

public class GetCommand implements Command {
    private Database database;
    private String keyString;

    public GetCommand(Database database, String keyString) {
        this.database = database;
        this.keyString = keyString;
    }

    @Override
    public JsonObject execute() {
        return this.database.get(keyString);
    }
}