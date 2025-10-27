package Chapter18.Summary;

// Example: Race condition vs immutable safety
class Counter {
    private int count = 0;

    public void increment() {
        count++; // Not atomic – race condition risk!
    }

    public int getCount() {
        return count;
    }
}

// Immutable version
final class ImmutableCounter {
    private final int count;

    public ImmutableCounter(int count) {
        this.count = count;
    }

    public ImmutableCounter increment() {
        return new ImmutableCounter(count + 1); // returns a new instance
    }

    public int getCount() {
        return count;
    }
}

public class RaceDemo {
    public static void main(String[] args) throws InterruptedException {
        Counter c = new Counter();

        Thread t1 = new Thread(() -> { for (int i = 0; i < 1000; i++) c.increment(); });
        Thread t2 = new Thread(() -> { for (int i = 0; i < 1000; i++) c.increment(); });

        t1.start(); t2.start();
        t1.join(); t2.join();

        System.out.println("Non-thread-safe count: " + c.getCount()); // Often < 2000

        // Immutable version
        ImmutableCounter ic = new ImmutableCounter(0);
        for (int i = 0; i < 1000; i++) ic = ic.increment();
        System.out.println("Immutable count: " + ic.getCount()); // Always 1000
    }
}

