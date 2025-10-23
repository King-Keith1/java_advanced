package Chapter18.Summary;

import java.rmi.*;
import java.rmi.server.*;

public class GreetingServer extends UnicastRemoteObject implements GreetingService {
    public GreetingServer() throws RemoteException {}

    public String sayHello(String name) {
        return "Hello, " + name + "! (from the remote server)";
    }

    public static void main(String[] args) {
        try {
            GreetingService service = new GreetingServer();
            Naming.rebind("rmi://localhost:5000/Greet", service);
            System.out.println("Server ready!");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

