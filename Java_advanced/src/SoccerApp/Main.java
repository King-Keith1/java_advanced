package SoccerApp;

// Importing Java libraries (built-in classes we use in our program)
import java.io.*;     // For File, FileReader, PrintWriter, BufferedReader
import java.util.*;   // For Map, HashMap, List, ArrayList, Collections

// This is the Main class where the program starts
public class Main {

    // main() = entry point of the program. Runs first.
    public static void main(String[] args) {

        // Map = a data structure to store key/value pairs
        // Key = Team name (String)
        // Value = Team points (Integer)
        Map<String, Integer> teamPoints = new HashMap<>();

        // Creating a File object representing matches.txt inside src/SoccerApp folder
        File file = new File(System.getProperty("user.dir"), "src/SoccerApp/matches.txt");
        System.out.println("Reading file from: " + file.getAbsolutePath());

        // Check if file does NOT exist or is empty → create sample file
        if (!file.exists() || file.length() == 0) {
            System.out.println("⚠️ File missing or empty — creating sample matches.txt...");

            // PrintWriter writes text into a file
            try (PrintWriter writer = new PrintWriter(file)) {
                writer.println("Liverpool 3, ManchesterUnited 3");
                writer.println("Tarantulas2 1, FC Awesome 0");
                writer.println("Lions 1, FC Awesome 1");
                writer.println("Tarantulas2 3, ManchesterUnited 1");
                writer.println("Lions 4, Grouches 0");
            } catch (IOException e) {
                System.err.println("❌ Could not create sample file: " + e.getMessage());
                return; // Exit program if file creation fails
            }
        }

        // BufferedReader reads the file line by line
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line; // Holds one line from the file at a time

            // Read file until there are no more lines
            while ((line = reader.readLine()) != null) {

                // Check line formatting before processing
                if (isValidMatchLine(line)) {
                    processMatch(line, teamPoints); // Call method to update points
                } else {
                    System.out.println("⚠️ Skipping invalid or scoreboard line: " + line);
                }
            }
        } catch (IOException e) {
            System.err.println("❌ Error reading file: " + e.getMessage());
            return;
        }

        // Convert Map entries to a List so we can sort them
        List<Map.Entry<String, Integer>> sortedTeams = new ArrayList<>(teamPoints.entrySet());

        // Sort teams: highest points first, alphabetically if tied
        sortedTeams.sort((a, b) -> {
            int comparePoints = b.getValue().compareTo(a.getValue());
            if (comparePoints != 0) return comparePoints;
            return a.getKey().compareToIgnoreCase(b.getKey());
        });

        // Print final scoreboard
        printScoreboard(sortedTeams);
    }

    /**
     * isValidMatchLine()
     * Checks if a line from the file has correct match format like:
     * "TeamA 3, TeamB 1"
     */
    private static boolean isValidMatchLine(String line) {
        if (line == null || line.trim().isEmpty()) return false; //Not empty
        if (!line.contains(",")) return false; // Must contain comma
        return line.matches(".*\\d+.*\\,.*\\d+.*"); // Must contain 2 scores
    }

    /**
     * processMatch()
     * Extracts team names + scores and updates the points table
     */
    private static void processMatch(String line, Map<String, Integer> teamPoints) {
        String[] parts = line.split(","); // Split into two teams

        if (parts.length != 2) {
            System.err.println("⚠️ Invalid format: " + line);
            return;
        }

        // Break team names & scores into parts
        String[] team1Data = parts[0].trim().split(" ");
        String[] team2Data = parts[1].trim().split(" ");

        try {
            // Last element = score
            int score1 = Integer.parseInt(team1Data[team1Data.length - 1]);
            int score2 = Integer.parseInt(team2Data[team2Data.length - 1]);

            // All other words before score make the team name
            String team1 = String.join(" ", Arrays.copyOf(team1Data, team1Data.length - 1));
            String team2 = String.join(" ", Arrays.copyOf(team2Data, team2Data.length - 1));

            // Add team to Map if not already there
            teamPoints.putIfAbsent(team1, 0);
            teamPoints.putIfAbsent(team2, 0);

            // Update points based on who won or draw
            if (score1 > score2) {
                teamPoints.put(team1, teamPoints.get(team1) + 3); // Team1 wins
            } else if (score1 < score2) {
                teamPoints.put(team2, teamPoints.get(team2) + 3); // Team2 wins
            } else {
                teamPoints.put(team1, teamPoints.get(team1) + 1); // Draw
                teamPoints.put(team2, teamPoints.get(team2) + 1);
            }

        } catch (NumberFormatException e) {
            System.err.println("⚠️ Error parsing scores in line: " + line);
        }
    }

    /**
     * printScoreboard()
     * Displays ranking table with tied teams sharing rank number
     */
    private static void printScoreboard(List<Map.Entry<String, Integer>> sortedTeams) {
        int rank = 1;        // Actual position in list
        int prevPoints = -1; // Track previous team's points
        int displayRank = 1; // Display rank (stays same for ties)

        System.out.println("\n===== FINAL SCOREBOARD =====");

        for (Map.Entry<String, Integer> entry : sortedTeams) {
            String team = entry.getKey();
            int points = entry.getValue();

            // If points change, update rank
            if (points != prevPoints) {
                displayRank = rank;
            }

            // Print "pt" if 1 point, "pts" if more
            String ptsLabel = (points == 1) ? "pt" : "pts";
            System.out.printf("%d. %s, %d %s%n", displayRank, team, points, ptsLabel);

            prevPoints = points;
            rank++;
        }
    }
}
