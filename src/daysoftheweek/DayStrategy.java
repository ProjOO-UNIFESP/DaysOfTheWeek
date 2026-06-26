package daysoftheweek;

/**
 * Strategy abstraction (Strategy pattern).
 *
 * Every weekday — and also the absence of a day — implements this interface, so
 * the main program can treat all cases uniformly, without a long chain of
 * conditionals to pick the message (see the assignment's implementation
 * constraints).
 */
public interface DayStrategy {

    /**
     * Runs the day's behavior using the extra information provided by the user
     * (name, pending task, weekly goal, ...).
     *
     * @param userInfo extra information typed by the user
     * @return the message and the recommended priority
     */
    Behavior execute(String userInfo);
}
