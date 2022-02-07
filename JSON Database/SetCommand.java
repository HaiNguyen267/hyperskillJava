package server;

import com.google.gson.JsonObject;

import java.util.Map;

public class SetCommand implements Command {
    private Database database;
    private String keyString;
    private String valueString;

    public SetCommand(Database database, String keyString, String valueString) {
        this.database = database;
        this.keyString = keyString;
        this.valueString = valueString;
    }

    @Override
    public JsonObject execute() {
        return this.database.set(keyString, valueString);
    }
}
