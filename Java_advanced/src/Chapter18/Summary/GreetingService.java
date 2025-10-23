package Chapter18.Summary;

import java.rmi.*;

public interface GreetingService extends Remote {
    String sayHello(String name) throws RemoteException;
}

