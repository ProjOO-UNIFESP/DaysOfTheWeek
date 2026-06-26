package daysoftheweek;

import java.util.EnumMap;
import java.util.Map;
import java.util.Optional;

/**
 * Component responsible for selecting the right strategy for a day.
 *
 * The day -> strategy associations live in a map, which replaces the long chain
 * of conditionals forbidden by the assignment. When there is no associated
 * strategy, it returns the Null Object strategy instead of {@code null},
 * avoiding repeated null checks in the main program.
 */
public class StrategySelector {

    private final Map<Weekday, DayStrategy> strategies = new EnumMap<>(Weekday.class);
    private final DayStrategy fallback = new UndefinedStrategy();

    public StrategySelector() {
        strategies.put(Weekday.MONDAY, new MondayStrategy());
        strategies.put(Weekday.TUESDAY, new TuesdayStrategy());
        strategies.put(Weekday.WEDNESDAY, new WednesdayStrategy());
        strategies.put(Weekday.THURSDAY, new ThursdayStrategy());
        strategies.put(Weekday.FRIDAY, new FridayStrategy());
        strategies.put(Weekday.SATURDAY, new SaturdayStrategy());
        strategies.put(Weekday.SUNDAY, new SundayStrategy());
    }

    /** Selects the strategy for a known day. */
    public DayStrategy select(Weekday day) {
        return strategies.getOrDefault(day, fallback);
    }

    /**
     * Selects the strategy from free-form user text. Invalid text yields the
     * Null Object strategy (functional requirement 5).
     */
    public DayStrategy select(String dayText) {
        Optional<Weekday> day = Weekday.parse(dayText);
        return day.map(this::select).orElse(fallback);
    }
}
