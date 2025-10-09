package Chapter13.Summary;

// Example to demonstrate exception handling concepts
class InvalidAgeException extends Exception { // Custom checked exception
    public InvalidAgeException(String message) {
        super(message);
    }
}

public class ExceptionDemo {

    // Method that throws a checked exception
    static void checkAge(int age) throws InvalidAgeException {
        if (age < 18) {
            throw new InvalidAgeException("You must be 18 or older!");
        } else {
            System.out.println("Access granted.");
        }
    }

    public static void main(String[] args) {
        try {
            // Unchecked exception example
            int result = 10 / 0; // This will cause ArithmeticException
            System.out.println("Result: " + result);

            // Checked exception example
            checkAge(15);
        }
        catch (ArithmeticException e) { // Handling unchecked exception
            System.out.println("Error: You tried to divide by zero.");
        }
        catch (InvalidAgeException e) { // Handling checked exception
            System.out.println("Custom Exception: " + e.getMessage());
        }
        finally { // Always runs, even if an exception occurs
            System.out.println("Execution finished. Cleaning up resources...");
        }

        System.out.println("Program continues running safely...");
    }
}

