package AlarmClock;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.time.LocalTime;
import java.util.Scanner;

public class AlarmClock implements Runnable {

    // ===============================
    // CLASS: AlarmClock
    // ===============================
    // This class represents an alarm clock that runs on a separate thread.
    // It continuously checks the current time and, when the specified alarm time
    // is reached, plays a sound file until the user stops it manually.

    private final LocalTime alarmTime;  // The time when the alarm should go off
    private final String filePath;      // Path to the alarm sound file (.wav)
    private final Scanner scanner;      // Scanner object to receive user input

    // Constructor: Initializes the alarm time, sound file path, and scanner
    AlarmClock(LocalTime alarmTime, String filePath, Scanner scanner) {
        this.alarmTime = alarmTime;
        this.filePath = filePath;
        this.scanner = scanner;
    }

    @Override
    public void run() {
        // This method runs when the thread is started.
        // It continuously checks the system time until it matches or exceeds the alarm time.

        while (LocalTime.now().isBefore(alarmTime)) {
            try {
                // Wait for 1 second before checking again to reduce CPU usage
                Thread.sleep(1000);

                // Get the current system time
                LocalTime now = LocalTime.now();

                // Print the current time on the same console line (like a live clock)
                System.out.printf("\r%02d:%02d:%02d",
                        now.getHour(),
                        now.getMinute(),
                        now.getSecond());

            } catch (InterruptedException e) {
                // Handles interruption if the thread is externally stopped
                System.out.println("Thread was interrupted");
            }
        }

        // Once the current time reaches the alarm time, trigger the alarm
        System.out.println("\n*ALARM NOISES*");
        playSound(filePath);
    }

    /**
     * Plays the alarm sound using the Java Sound API.
     * The sound continues until the user presses Enter.
     *
     * @param filePath The path to the .wav audio file to play.
     */
    private void playSound(String filePath) {

        File audioFile = new File(filePath);  // Load the audio file

        try (AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile)) {
            // Obtain a Clip (a short audio player)
            Clip clip = AudioSystem.getClip();

            // Open and start playing the audio file
            clip.open(audioStream);
            clip.start();

            System.out.println("Press Enter to stop the alarm: ");
            scanner.nextLine();  // Wait for user input to stop the sound

            // Stop the audio and close the scanner
            clip.stop();
            scanner.close();

        } catch (UnsupportedAudioFileException e) {
            // If the file format is not supported
            System.err.println("Unsupported Audio File");
        } catch (LineUnavailableException e) {
            // If the audio line is not available for playback
            System.err.println("Audio line unavailable");
        } catch (IOException e) {
            // If there's a problem reading the audio file
            System.err.println("Error reading Audio File");
        }
    }
}
