package AlarmClock;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // ===============================
        // JAVA ALARM CLOCK (Main class)
        // ===============================
        // This program allows the user to set an alarm time in the format HH:mm:ss.
        // When the system time matches the alarm time, a sound will be played.
        // The alarm runs in a separate thread to allow continuous checking of the time.

        Scanner scanner = new Scanner(System.in);  // Used to read user input from the console

        // Define the format in which the time should be entered and parsed
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        LocalTime alarmTime = null;  // Variable to store the valid alarm time entered by the user

        // Path to the alarm sound file (make sure this file exists at this path)
        String filePath = "src/AlarmClock/rooster alarm _ Sound Effect.wav";

        // Loop until the user enters a valid alarm time
        while (alarmTime == null) {
            try {
                System.out.print("Enter alarm time (HH:mm:ss): ");
                String inputTime = scanner.nextLine();  // Read time as a string

                // Parse the input string into a LocalTime object using the defined formatter
                alarmTime = LocalTime.parse(inputTime, formatter);

                // Confirm the alarm time to the user
                System.out.println("Alarm set for " + alarmTime);
            }
            catch (DateTimeParseException e) {
                // If the user enters an invalid format, show an error message and retry
                System.out.println("Invalid time format. Please use HH:mm:ss");
            }
        }

        // Create an instance of the AlarmClock class
        // Pass in the alarm time, file path, and scanner for user input
        AlarmClock alarmClock = new AlarmClock(alarmTime, filePath, scanner);

        // Create a new thread to run the alarm clock independently
        Thread alarmClockThread = new Thread(alarmClock);

        // Start the thread so the alarm can continuously check the current time
        alarmClockThread.start();
    }
}
