package Chapter17.Summary;

import java.io.*;
import java.net.*;

public class SimpleClient {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 5000);
             PrintWriter writer = new PrintWriter(socket.getOutputStream(), true)) {

            System.out.println("Connected to server. Sending messages...");
            writer.println("Hello, server! 👋🏽");
            writer.println("This is Miles checking in.");
            writer.println("Goodbye!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

