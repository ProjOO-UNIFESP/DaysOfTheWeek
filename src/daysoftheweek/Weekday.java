package daysoftheweek;

import java.text.Normalizer;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Optional;

/**
 * Days of the week, mapping the Portuguese label shown to the user to the
 * corresponding {@link java.time.DayOfWeek}.
 *
 * It is also responsible for parsing free-form user text into a Weekday,
 * tolerating accents, casing and the optional "-feira" suffix (e.g. "terca",
 * "Terça" and "TERÇA-FEIRA" all resolve to {@code TUESDAY}).
 */
public enum Weekday {
    MONDAY("segunda-feira", DayOfWeek.MONDAY),
    TUESDAY("terça-feira", DayOfWeek.TUESDAY),
    WEDNESDAY("quarta-feira", DayOfWeek.WEDNESDAY),
    THURSDAY("quinta-feira", DayOfWeek.THURSDAY),
    FRIDAY("sexta-feira", DayOfWeek.FRIDAY),
    SATURDAY("sábado", DayOfWeek.SATURDAY),
    SUNDAY("domingo", DayOfWeek.SUNDAY);

    private final String label;
    private final DayOfWeek javaDay;

    Weekday(String label, DayOfWeek javaDay) {
        this.label = label;
        this.javaDay = javaDay;
    }

    /** Portuguese label shown to the user (e.g. "quarta-feira"). */
    public String label() {
        return label;
    }

    /** Weekday matching the system's current date (functional requirement 1). */
    public static Weekday today() {
        return fromJavaDay(LocalDate.now().getDayOfWeek());
    }

    private static Weekday fromJavaDay(DayOfWeek javaDay) {
        for (Weekday day : values()) {
            if (day.javaDay == javaDay) {
                return day;
            }
        }
        throw new IllegalArgumentException("Unsupported DayOfWeek: " + javaDay);
    }

    /**
     * Parses free-form user text into a Weekday.
     *
     * @return the matching Weekday, or an empty Optional when the text does not
     *         correspond to any day.
     */
    public static Optional<Weekday> parse(String text) {
        if (text == null) {
            return Optional.empty();
        }
        String key = normalize(text);
        for (Weekday day : values()) {
            String fullLabel = normalize(day.label);
            String shortLabel = fullLabel.replace("-feira", "");
            if (key.equals(fullLabel) || key.equals(shortLabel)) {
                return Optional.of(day);
            }
        }
        return Optional.empty();
    }

    /** Lower-cases, strips accents and normalizes separators for tolerant matching. */
    private static String normalize(String text) {
        String withoutAccents = Normalizer
                .normalize(text.trim().toLowerCase(), Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "");
        return withoutAccents.replace('_', '-').replace(' ', '-');
    }
}
