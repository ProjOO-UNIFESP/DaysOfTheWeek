package daysoftheweek;

/** Wednesday strategy: review the progress of activities. */
public class WednesdayStrategy implements DayStrategy {
    @Override
    public Behavior execute(String userInfo) {
        String message = "Dia de revisão: verifique o andamento da atividade \""
                + userInfo + "\".";
        return new Behavior(message, Priority.MEDIUM);
    }
}
