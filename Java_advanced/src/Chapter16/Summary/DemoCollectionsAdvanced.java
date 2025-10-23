package Chapter16.Summary;

import java.util.*;

class Student implements Comparable<Student> {
    String name;
    int grade;

    public Student(String name, int grade) {
        this.name = name;
        this.grade = grade;
    }

    @Override
    public int compareTo(Student other) {
        return Integer.compare(this.grade, other.grade); // sort by grade ascending
    }

    @Override
    public String toString() {
        return name + " (" + grade + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        Student s = (Student) o;
        return Objects.equals(name, s.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}

public class DemoCollectionsAdvanced {
    public static void main(String[] args) {
        // --- Set Example ---
        Set<Student> classSet = new HashSet<>();
        classSet.add(new Student("Alice", 90));
        classSet.add(new Student("Bob", 85));
        classSet.add(new Student("Alice", 90)); // duplicate ignored

        System.out.println("Unique students: " + classSet);

        // --- Sorting with Comparable ---
        List<Student> classList = new ArrayList<>(classSet);
        Collections.sort(classList);
        System.out.println("Sorted by grade: " + classList);

        // --- Sorting with Comparator ---
        classList.sort(Comparator.comparing(s -> s.name));
        System.out.println("Sorted by name: " + classList);

        // --- Map Example ---
        Map<String, Integer> scores = new TreeMap<>();
        scores.put("Zane", 82);
        scores.put("Lina", 95);
        scores.put("Kyle", 89);

        System.out.println("Scores in alphabetical order: " + scores);

        // --- Reverse Map iteration ---
        scores.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .forEach(e -> System.out.println(e.getKey() + " => " + e.getValue()));
    }
}

