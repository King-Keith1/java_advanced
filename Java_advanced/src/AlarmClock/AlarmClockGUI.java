package AlarmClock;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * AlarmClockGUI
 * ---------------
 * A simple digital alarm clock built using Java Swing.
 * It displays the current time, allows setting an alarm,
 * and plays a sound when the alarm time is reached.
 */
public class AlarmClockGUI extends JFrame implements Runnable {

    // --- Time management ---
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss"); // Time format
    private LocalTime alarmTime; // Stores the user’s alarm time
    private boolean alarmSet = false; // True if alarm has been set
    private boolean alarmRinging = false; // True if alarm is currently ringing

    // --- GUI components ---
    private final JTextField timeField;        // User input for alarm time
    private final JLabel currentTimeLabel;     // Displays current system time
    private final JButton setAlarmButton;      // Button to set alarm
    private final JButton stopAlarmButton;     // Button to stop alarm

    // --- Sound configuration ---
    private final String filePath = "src/AlarmClock/rooster alarm _ Sound Effect.wav"; // Sound file location
    private Clip clip; // Used to play the alarm sound

    /**
     * Constructor — sets up the GUI layout, event listeners, and starts the clock thread.
     */
    public AlarmClockGUI() {
        super("Alarm Clock"); // Window title

        // --- Create and style components ---
        currentTimeLabel = new JLabel();
        currentTimeLabel.setFont(new Font("Arial", Font.BOLD, 32));
        currentTimeLabel.setHorizontalAlignment(SwingConstants.CENTER);

        timeField = new JTextField(8);
        timeField.setFont(new Font("Arial", Font.PLAIN, 18));
        timeField.setHorizontalAlignment(SwingConstants.CENTER);
        timeField.setToolTipText("Enter time as HH:mm:ss");

        setAlarmButton = new JButton("Set Alarm");
        stopAlarmButton = new JButton("Stop Alarm");
        stopAlarmButton.setEnabled(false); // Disabled until an alarm is set

        // --- Layout section ---
        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Alarm Time: "));
        inputPanel.add(timeField);
        inputPanel.add(setAlarmButton);
        inputPanel.add(stopAlarmButton);

        setLayout(new BorderLayout(10, 10));
        add(currentTimeLabel, BorderLayout.CENTER);
        add(inputPanel, BorderLayout.SOUTH);

        // --- Button functionality ---
        setAlarmButton.addActionListener(e -> setAlarm());
        stopAlarmButton.addActionListener(e -> stopAlarm());

        // --- Window setup ---
        setSize(400, 200);
        setLocationRelativeTo(null); // Center on screen
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

        // --- Start the clock thread ---
        new Thread(this).start();
    }

    /**
     * Reads the time entered by the user and sets the alarm.
     */
    private void setAlarm() {
        try {
            // Parse user input (e.g., "07:30:00")
            alarmTime = LocalTime.parse(timeField.getText(), formatter);
            alarmSet = true;
            JOptionPane.showMessageDialog(this, "Alarm set for " + alarmTime);
            setAlarmButton.setEnabled(false);
            stopAlarmButton.setEnabled(true);
        } catch (DateTimeParseException e) {
            JOptionPane.showMessageDialog(this,
                    "Invalid time format. Please use HH:mm:ss",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Stops the alarm sound and resets alarm state.
     */
    private void stopAlarm() {
        alarmSet = false;
        alarmRinging = false;
        setAlarmButton.setEnabled(true);
        stopAlarmButton.setEnabled(false);

        // Stop any sound that’s currently playing
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }
    }

    /**
     * Loads and plays the alarm sound file using Java’s sound API.
     */
    private void playSound() {
        try {
            File audioFile = new File(filePath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start(); // Start playback
            alarmRinging = true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Error playing sound: " + e.getMessage());
        }
    }

    /**
     * Continuously updates the clock every second and triggers the alarm when needed.
     */
    @Override
    public void run() {
        while (true) {
            LocalTime now = LocalTime.now();

            // Update current time label on the Swing thread
            SwingUtilities.invokeLater(() ->
                    currentTimeLabel.setText(now.format(formatter))
            );

            // If alarm is set and current time matches or passes alarm time
            if (alarmSet && now.isAfter(alarmTime.minusSeconds(1)) && !alarmRinging) {
                playSound();
                JOptionPane.showMessageDialog(this, "Wake up! It’s " + alarmTime + "!");
            }

            try {
                Thread.sleep(1000); // Wait 1 second before updating time again
            } catch (InterruptedException ignored) {}
        }
    }

    /**
     * Main method — entry point to start the application.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(AlarmClockGUI::new);
    }
}
