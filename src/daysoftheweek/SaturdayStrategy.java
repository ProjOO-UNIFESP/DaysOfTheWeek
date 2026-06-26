package daysoftheweek;

/** Saturday strategy: free study or rest. */
public class SaturdayStrategy implements DayStrategy {
    @Override
    public Behavior execute(String userInfo) {
        String message = "Fim de semana: aproveite para estudo livre ou descanso. "
                + "Que tal revisar \"" + userInfo + "\" sem pressa?";
        return new Behavior(message, Priority.LOW);
    }
}
