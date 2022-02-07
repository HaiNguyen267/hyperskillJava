package server;

import com.google.gson.JsonObject;

public class DeleteCommand implements Command {
    private Database database;
    private String keyString;

    public DeleteCommand(Database database, String keyString) {
        this.database = database;
        this.keyString = keyString;
    }

    @Override
    public JsonObject execute() {
        return this.database.delete(keyString);
    }
}
