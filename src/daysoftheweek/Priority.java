package daysoftheweek;

/**
 * Recommended priority level for a given day.
 *
 * Functional requirement 6: besides the main message, each strategy must report
 * a priority recommendation — ALTA, MÉDIA or BAIXA.
 */
public enum Priority {
    HIGH("ALTA"),
    MEDIUM("MÉDIA"),
    LOW("BAIXA");

    private final String label;

    Priority(String label) {
        this.label = label;
    }

    /** Portuguese label shown to the user (matches the assignment output). */
    public String label() {
        return label;
    }
}
