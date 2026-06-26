package daysoftheweek;

/** Thursday strategy: collaborate with a teammate. */
public class ThursdayStrategy implements DayStrategy {
    @Override
    public Behavior execute(String userInfo) {
        String message = "Dia de colaboração: compartilhe o andamento de \""
                + userInfo + "\" com alguém da equipe.";
        return new Behavior(message, Priority.MEDIUM);
    }
}
