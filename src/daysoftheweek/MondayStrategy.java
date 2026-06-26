package daysoftheweek;

/** Monday strategy: organize your priorities. */
public class MondayStrategy implements DayStrategy {
    @Override
    public Behavior execute(String userInfo) {
        String message = "Início de semana: organize suas prioridades em torno de \""
                + userInfo + "\".";
        return new Behavior(message, Priority.HIGH);
    }
}
