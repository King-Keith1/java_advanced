package Chapter11.Summary;

import java.util.*;

class User {
    private String name;
    private int score;
    public User(String name, int score) {
        this.name = name;
        this.score = score;
    }
    public String getName() { return name; }
    public int getScore() { return score; }
    @Override
    public String toString() {
        return name + "(" + score + ")";
    }
}

public class DemoCollections {
    public static void main(String[] args) {
        // Use a List with generics
        List<User> users = new ArrayList<>();
        users.add(new User("Alice", 120));
        users.add(new User("Bob", 95));
        users.add(new User("Alice", 120)); // duplicate

        System.out.println("List (with possible duplicates): " + users);

        // Use a Set to remove duplicates based on equals/hashCode — but note: we did not override equals/hashCode here,
        // so Set will treat duplicates as different unless we implement those methods.
        Set<User> userSet = new HashSet<>(users);
        System.out.println("Set (duplicates removed only if equals/hashCode defined): " + userSet);

        // Sort the list by score descending using a Comparator
        users.sort(Comparator.comparingInt(User::getScore).reversed());
        System.out.println("Sorted list by score descending: " + users);

        // Use a Map for lookup by name
        Map<String, User> mapByName = new HashMap<>();
        for (User u : users) {
            mapByName.put(u.getName(), u);
        }
        System.out.println("Lookup map: " + mapByName);

        // Demonstrate generics compile-time safety:
        // users.add("not a user"); // <- compile-time error if uncommented
    }
}

