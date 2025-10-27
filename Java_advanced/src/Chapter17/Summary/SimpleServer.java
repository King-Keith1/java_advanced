package Chapter17.Summary;

import java.io.*;
import java.net.*;

public class SimpleServer {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(5000)) {
            System.out.println("Server waiting on port 5000...");
            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connected!");

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream())
            );

            String message;
            while ((message = reader.readLine()) != null) {
                System.out.println("Client says: " + message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

