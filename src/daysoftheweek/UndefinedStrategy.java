package daysoftheweek;

/**
 * Null Object strategy.
 *
 * Safely represents the absence of an associated strategy — for instance when
 * the user types an invalid or non-existing day (functional requirement 5).
 * Because it implements the same interface as the real strategies, the main
 * program never has to check for null values.
 */
public class UndefinedStrategy implements DayStrategy {
    @Override
    public Behavior execute(String userInfo) {
        String message = "Não há estratégia associada ao dia informado. "
                + "Nenhuma ação será executada para \"" + userInfo + "\".";
        return new Behavior(message, Priority.LOW);
    }
}
