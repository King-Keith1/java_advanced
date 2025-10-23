package Chapter18.Summary;

import java.rmi.*;

public class GreetingClient {
    public static void main(String[] args) {
        try {
            GreetingService service =
                    (GreetingService) Naming.lookup("rmi://localhost:5000/Greet");
            System.out.println(service.sayHello("Pierre"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
