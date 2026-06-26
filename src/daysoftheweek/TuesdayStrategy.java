package daysoftheweek;

/** Tuesday strategy: make progress on pending tasks. */
public class TuesdayStrategy implements DayStrategy {
    @Override
    public Behavior execute(String userInfo) {
        String message = "Dia de produção: avance na tarefa pendente \""
                + userInfo + "\".";
        return new Behavior(message, Priority.HIGH);
    }
}
