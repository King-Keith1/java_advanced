package Chapter15.Summary;

import java.io.*;
import java.net.*;

// --- THREAD DEMO ---
class CounterThread extends Thread {
    private String name;
    public CounterThread(String name) {
        this.name = name;
    }
    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println(name + " count: " + i);
            try { Thread.sleep(500); } catch (InterruptedException e) {}
        }
    }
}

// --- NETWORK DEMO ---
public class DemoThreadsAndNetworking {
    public static void main(String[] args) throws Exception {
        // Start two threads
        new CounterThread("Alpha").start();
        new CounterThread("Beta").start();

        // Start a simple local server in a thread
        new Thread(() -> startServer()).start();

        // Give server a moment to start
        Thread.sleep(1000);

        // Connect as a client
        try (Socket socket = new Socket("localhost", 5050);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            out.println("Hello Server!");
            System.out.println("Server replied: " + in.readLine());
        }
    }

    // Simple echo server
    public static void startServer() {
        try (ServerSocket server = new ServerSocket(5050)) {
            System.out.println("Server ready on port 5050...");
            Socket client = server.accept();
            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            PrintWriter out = new PrintWriter(client.getOutputStream(), true);

            String message = in.readLine();
            out.println("You said: " + message);
            client.close();
        } catch (IOException e) {
            System.out.println("Server error: " + e.getMessage());
        }
    }
}

