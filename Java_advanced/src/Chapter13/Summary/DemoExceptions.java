package Chapter13.Summary;

import java.io.*;

class InsufficientFundsException extends Exception {
    public InsufficientFundsException(String message) {
        super(message);
    }
}

public class DemoExceptions {
    public static void main(String[] args) {
        try {
            riskyDivision(10, 0);               // Unchecked exception example
        } catch (ArithmeticException e) {
            System.out.println("Math error: " + e.getMessage());
        }

        try {
            readFile("nonexistent.txt");        // Checked exception example
        } catch (IOException e) {
            System.out.println("File problem: " + e.getMessage());
        }

        try {
            withdraw(50, 100);                  // Custom exception example
        } catch (InsufficientFundsException e) {
            System.out.println("Bank error: " + e.getMessage());
        } finally {
            System.out.println("Transaction attempt finished.");
        }
    }

    static void riskyDivision(int a, int b) {
        System.out.println("Result: " + (a / b)); // may throw ArithmeticException
    }

    static void readFile(String filename) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filename));
        System.out.println(br.readLine());
        br.close();
    }

    static void withdraw(double balance, double amount) throws InsufficientFundsException {
        if (amount > balance)
            throw new InsufficientFundsException("Balance too low for withdrawal.");
        System.out.println("Withdrawal successful!");
    }
}
