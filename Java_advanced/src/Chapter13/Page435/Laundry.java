package Chapter13.Page435;

// Laundry class that might throw clothing-related exceptions
public class Laundry {

    // This method declares that it might throw PantsException or LingerieException
    public void doLaundry(String clothes) throws PantsException, LingerieException, TeeShirtException {
        System.out.println("Starting laundry...");

        switch(clothes){
            case "pants":
                throw new PantsException();
            case "lingerie":
                throw new LingerieException();
            case "tee":
                throw new TeeShirtException();
            default:
                System.out.println("No exception");
        }
//        // For now, we are only throwing PantsException
//        throw new PantsException(); // You can change this line to test other exceptions
//
//        // Example:
//        // throw new LingerieException();
//        // throw new TeeShirtException();
    }
}
