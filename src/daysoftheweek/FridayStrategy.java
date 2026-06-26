package daysoftheweek;

/** Friday strategy: record what was completed. */
public class FridayStrategy implements DayStrategy {
    @Override
    public Behavior execute(String userInfo) {
        String message = "Fechamento da semana: registre o que foi concluído sobre \""
                + userInfo + "\".";
        return new Behavior(message, Priority.MEDIUM);
    }
}
