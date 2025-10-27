package Chapter15.Summary;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SimpleSwingDemo {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Simple Swing Demo");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new BorderLayout());

            JTextField textField = new JTextField();
            JButton button = new JButton("Submit");
            JLabel resultLabel = new JLabel("Enter text and press Submit");

            JPanel inputPanel = new JPanel(new BorderLayout());
            inputPanel.add(textField, BorderLayout.CENTER);
            inputPanel.add(button, BorderLayout.EAST);

            frame.add(inputPanel, BorderLayout.NORTH);
            frame.add(resultLabel, BorderLayout.CENTER);

            button.addActionListener(e -> {
                String input = textField.getText();
                resultLabel.setText("You entered: " + input);
            });

            frame.setSize(400, 120);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}


