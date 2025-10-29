package SoccerApp;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Map<String, Integer> teamPoints = new HashMap<>();

        // Path: src/SoccerApp/matches.txt
        File file = new File(System.getProperty("user.dir"), "src/SoccerApp/matches.txt");
        System.out.println("Reading file from: " + file.getAbsolutePath());

        // If file missing or empty — create a valid one
        if (!file.exists() || file.length() == 0) {
            System.out.println("⚠️ File missing or empty — creating sample matches.txt...");
            try (PrintWriter writer = new PrintWriter(file)) {
                writer.println("Liverpool 3, ManchesterUnited 3");
                writer.println("Tarantulas2 1, FC Awesome 0");
                writer.println("Lions 1, FC Awesome 1");
                writer.println("Tarantulas2 3, ManchesterUnited 1");
                writer.println("Lions 4, Grouches 0");
            } catch (IOException e) {
                System.err.println("❌ Could not create sample file: " + e.getMessage());
                return;
            }
        }

        // Read from file
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (isValidMatchLine(line)) {
                    processMatch(line, teamPoints);
                } else {
                    System.out.println("⚠️ Skipping invalid or scoreboard line: " + line);
                }
            }
        } catch (IOException e) {
            System.err.println("❌ Error reading file: " + e.getMessage());
            return;
        }

        // Sort by points desc, then name asc
        List<Map.Entry<String, Integer>> sortedTeams = new ArrayList<>(teamPoints.entrySet());
        sortedTeams.sort((a, b) -> {
            int comparePoints = b.getValue().compareTo(a.getValue());
            if (comparePoints != 0) return comparePoints;
            return a.getKey().compareToIgnoreCase(b.getKey());
        });

        // Print scoreboard
        printScoreboard(sortedTeams);
    }

    /** Checks if a line is a valid match format like "Lions 3, FC Awesome 1" */
    private static boolean isValidMatchLine(String line) {
        if (line == null || line.trim().isEmpty()) return false;
        // Must contain a comma separating two teams
        if (!line.contains(",")) return false;
        // Should have at least one number (a score)
        return line.matches(".*\\d+.*\\,.*\\d+.*");
    }

    private static void processMatch(String line, Map<String, Integer> teamPoints) {
        String[] parts = line.split(",");
        if (parts.length != 2) {
            System.err.println("⚠️ Invalid format: " + line);
            return;
        }

        String[] team1Data = parts[0].trim().split(" ");
        String[] team2Data = parts[1].trim().split(" ");

        try {
            int score1 = Integer.parseInt(team1Data[team1Data.length - 1]);
            int score2 = Integer.parseInt(team2Data[team2Data.length - 1]);

            String team1 = String.join(" ", Arrays.copyOf(team1Data, team1Data.length - 1));
            String team2 = String.join(" ", Arrays.copyOf(team2Data, team2Data.length - 1));

            teamPoints.putIfAbsent(team1, 0);
            teamPoints.putIfAbsent(team2, 0);

            if (score1 > score2) {
                teamPoints.put(team1, teamPoints.get(team1) + 3);
            } else if (score1 < score2) {
                teamPoints.put(team2, teamPoints.get(team2) + 3);
            } else {
                teamPoints.put(team1, teamPoints.get(team1) + 1);
                teamPoints.put(team2, teamPoints.get(team2) + 1);
            }

        } catch (NumberFormatException e) {
            System.err.println("⚠️ Error parsing scores in line: " + line);
        }
    }

    private static void printScoreboard(List<Map.Entry<String, Integer>> sortedTeams) {
        int rank = 1;
        int prevPoints = -1;
        int displayRank = 1;

        System.out.println("\n===== FINAL SCOREBOARD =====");
        for (Map.Entry<String, Integer> entry : sortedTeams) {
            String team = entry.getKey();
            int points = entry.getValue();

            if (points != prevPoints) {
                displayRank = rank;
            }

            String ptsLabel = (points == 1) ? "pt" : "pts";
            System.out.printf("%d. %s, %d %s%n", displayRank, team, points, ptsLabel);

            prevPoints = points;
            rank++;
        }
    }
}
