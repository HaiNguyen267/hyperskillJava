package server;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;




//    final static String databaseFilePath = "C:\\Users\\NKcomputer\\IdeaProjects\\JSON Database\\JSON Database\\task\\src\\server\\data\\db.json";



public class Main {
    static Database database = new Database();
    public static void main(String[] args) {
        final String SERVER_ADDRESS = "127.0.0.1";
        final int PORT = 23456;

        ExecutorService executor = Executors.newSingleThreadExecutor();

        try (ServerSocket server = new ServerSocket(PORT, 50, InetAddress.getByName(SERVER_ADDRESS))) {

            while (true) {
                Session session = new Session(server, server.accept()); // the loop stops by the exception caused in the session
                executor.submit(session);
            }
        } catch (Exception e) {
            /* when the client sends "exit" request, the server socket will be closed in the session,
            which will cause "java.net.SocketException: Socket closed" in this method
            , and that when the program is terminated by the statement below

            */
           System.exit(0);
        }
    }
}

class Session implements Runnable {
    private ServerSocket server;
    private Socket socket;

    public Session(ServerSocket server, Socket socket) {
        this.server = server;
        this.socket = socket;
    }

    @Override
    public void run() {

        try (
                DataInputStream input = new DataInputStream(this.socket.getInputStream());
                DataOutputStream output = new DataOutputStream(this.socket.getOutputStream());
        ) {
            Controller controller = new Controller();
            Command command;

            JsonObject request = Utility.parseJsonObject(input.readUTF());
            String type = request.get("type").getAsString();

            // create command based on the type of the request
            if (type.equals("exit")) {
                command = new ExitCommand();
            } else {
                Object key = request.get("key"); // because the key can be a string or jsonObject, so it is stored in an Object first, and then cast to a string

                if (type.equals("set")) {
                    Object value = request.get("value"); // because the value can be a string and JsonObject, so first it is stored in an Object instance, then cast to string
                    command = new SetCommand(Main.database, key.toString(), value.toString());
                } else if (type.equals("get")) {
                    command = new GetCommand(Main.database, key.toString());
                } else {
                    command = new DeleteCommand(Main.database, key.toString());
                }
            }

            controller.setCommand(command);
            JsonObject response = controller.executeCommand();
            output.writeUTF(response.toString());


            if (type.equals("exit")) {
                this.server.close();
            }

            this.socket.close();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
