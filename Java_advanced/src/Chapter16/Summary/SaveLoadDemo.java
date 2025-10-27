package Chapter16.Summary;

import java.io.*;

class Player implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private int level;
    private transient int health; // not saved

    public Player(String name, int level, int health) {
        this.name = name;
        this.level = level;
        this.health = health;
    }

    @Override
    public String toString() {
        return "Player{name='" + name + "', level=" + level + ", health=" + health + "}";
    }
}

public class SaveLoadDemo {
    public static void main(String[] args) {
        Player player = new Player("Miles", 10, 85);
        String fileName = "playerData.ser";

        // --- Serialize (save) ---
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName))) {
            out.writeObject(player);
            System.out.println("Player saved: " + player);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // --- Deserialize (load) ---
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName))) {
            Player loaded = (Player) in.readObject();
            System.out.println("Player loaded: " + loaded);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
