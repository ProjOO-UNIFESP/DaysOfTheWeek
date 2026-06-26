package daysoftheweek;

/** Sunday strategy: plan the next week. */
public class SundayStrategy implements DayStrategy {
    @Override
    public Behavior execute(String userInfo) {
        String message = "Dia de planejamento: organize a próxima semana com foco em \""
                + userInfo + "\".";
        return new Behavior(message, Priority.LOW);
    }
}
