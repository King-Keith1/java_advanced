package Chapter12.Page395;

public class Example {
    public static void main(String[] args) {
        // This line will NOT compile because Consumer<T> only takes one parameter.
        // Uncommenting this will cause a compiler error.

        //Consumer<String> c = (s1, s2) -> System.out.println(s1 + s2);
        //Does not complie
    }
}
