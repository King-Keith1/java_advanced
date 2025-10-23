package Chapter14.Summary;

import java.io.*;

// A simple serializable class
class Player implements Serializable {
    private static final long serialVersionUID = 1L;
    String name;
    int score;
    transient String password; // won't be saved

    public Player(String name, int score, String password) {
        this.name = name;
        this.score = score;
        this.password = password;
    }

    @Override
    public String toString() {
        return name + " scored " + score + " (password=" + password + ")";
    }
}

public class DemoFileIO {
    public static void main(String[] args) {
        Player p1 = new Player("Alice", 120, "secret123");

        // --- Write object to file ---
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("player.ser"))) {
            out.writeObject(p1);
            System.out.println("Player saved!");
        } catch (IOException e) {
            System.out.println("Save error: " + e.getMessage());
        }

        // --- Read object back ---
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("player.ser"))) {
            Player loaded = (Player) in.readObject();
            System.out.println("Player loaded: " + loaded);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Load error: " + e.getMessage());
        }

        // --- Simple text file writing ---
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("scores.txt"))) {
            bw.write("Alice: 120\nBob: 95\nCharlie: 78");
            System.out.println("Text file written!");
        } catch (IOException e) {
            System.out.println("Text write error: " + e.getMessage());
        }
    }
}

