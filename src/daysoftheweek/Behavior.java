package daysoftheweek;

/**
 * Result produced by a strategy: the main message plus the recommended priority
 * for the day (functional requirements 3 and 6).
 */
public class Behavior {
    private final String message;
    private final Priority priority;

    public Behavior(String message, Priority priority) {
        this.message = message;
        this.priority = priority;
    }

    public String message() {
        return message;
    }

    public Priority priority() {
        return priority;
    }
}
