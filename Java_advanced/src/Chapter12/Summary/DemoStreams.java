package Chapter12.Summary;

import java.util.*;
import java.util.stream.*;

class Person {
    private String name;
    private int age;
    public Person(String name, int age) {
        this.name = name; this.age = age;
    }
    public String getName() { return name; }
    public int getAge() { return age; }
    @Override public String toString() {
        return name + " (" + age + ")";
    }
}

public class DemoStreams {
    public static void main(String[] args) {
        List<Person> people = Arrays.asList(
                new Person("Alice", 32),
                new Person("Bob", 45),
                new Person("Charlie", 29),
                new Person("Diana", 38),
                new Person("Eve", 45)
        );

        // Example: find distinct ages sorted, then map to name-age string, then collect to list
        List<String> result = people.stream()
                .filter(p -> p.getAge() >= 30)                 // keep only age >= 30
                .sorted(Comparator.comparingInt(Person::getAge)) // sort by age ascending
                .map(p -> p.getName() + ": " + p.getAge())     // map to string
                .distinct()                                     // remove duplicates (based on string equality)
                .collect(Collectors.toList());                 // terminal operation: collect into list

        System.out.println("Filtered, sorted, mapped list: " + result);

        // Another example: check if any person is older than 40
        boolean anyOlderThan40 = people.stream()
                .anyMatch(p -> p.getAge() > 40);

        System.out.println("Is any person older than 40? " + anyOlderThan40);
    }
}
