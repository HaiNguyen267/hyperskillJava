package server;

import com.google.gson.JsonObject;

import java.util.Map;

public class Controller {
    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public JsonObject executeCommand() {
        return this.command.execute();
    }
}
