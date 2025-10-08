package Chapter13.Page435;

// Main class to run the laundry and handle exceptions
public class WashingMachine {
    public static void main(String[] args) {
        Laundry laundry = new Laundry();
        String[] clothes = {"pants", "lingerie", "tee"};

        for (String type : clothes) {

            try {
                laundry.doLaundry(type);
            } catch (TeeShirtException tex) {
                // Recovery from TeeShirtException
                System.out.println("Caught TeeShirtException: " + tex);
            } catch (LingerieException lex) {
                // Recovery from LingerieException
                System.out.println("Caught LingerieException: " + lex);
            } catch (ClothingException cex) {
                // Recovery from all other ClothingExceptions
                System.out.println("Caught ClothingException: " + cex);
            }

            System.out.println("Laundry process complete.");
        }
    }
}