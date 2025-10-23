package Chapter17.Summary;

import java.io.*;
import java.net.*;

public class SimpleServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSock = new ServerSocket(5000);
        System.out.println("Server ready... waiting for connection");
        Socket sock = serverSock.accept(); // Waits for client

        PrintWriter writer = new PrintWriter(sock.getOutputStream(), true);
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(sock.getInputStream())
        );

        writer.println("Hello from Server!");

        String msg;
        while ((msg = reader.readLine()) != null) {
            System.out.println("Client: " + msg);
            if (msg.equalsIgnoreCase("bye")) break;
        }

        writer.close();
        reader.close();
        sock.close();
        serverSock.close();
        System.out.println("Server closed.");
    }
}

