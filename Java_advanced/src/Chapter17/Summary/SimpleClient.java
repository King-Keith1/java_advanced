package Chapter17.Summary;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class SimpleClient {
    public static void main(String[] args) throws IOException {
        Socket sock = new Socket("localhost", 5000);
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(sock.getInputStream())
        );
        PrintWriter writer = new PrintWriter(sock.getOutputStream(), true);

        System.out.println("Server says: " + reader.readLine());

        Scanner sc = new Scanner(System.in);
        String msg;
        do {
            System.out.print("You: ");
            msg = sc.nextLine();
            writer.println(msg);
        } while (!msg.equalsIgnoreCase("bye"));

        sc.close();
        writer.close();
        reader.close();
        sock.close();
    }
}

