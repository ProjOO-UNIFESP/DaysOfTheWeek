package daysoftheweek;

import java.util.Optional;
import java.util.Scanner;

/**
 * Entry point of the "Behavior by Day of the Week" program.
 *
 * It demonstrates:
 *  - reading the current day (requirement 1);
 *  - delegating the action to the day's strategy (requirements 2 and 3);
 *  - querying a manually typed day (requirement 4);
 *  - handling invalid days safely through the Null Object strategy (req. 5).
 */
public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        StrategySelector selector = new StrategySelector();

        System.out.println("=== Comportamento por Dia da Semana ===");

        System.out.print("Informe seu nome: ");
        String name = readLine(input, "Usuário");

        System.out.print("Informe uma informação adicional (tarefa pendente ou meta semanal): ");
        String userInfo = readLine(input, "sua meta da semana");

        // Requirement 1: use the system's current date.
        Weekday today = Weekday.today();
        System.out.println();
        System.out.println(">> Comportamento sugerido para hoje:");
        print(name, today.label(), selector.select(today).execute(userInfo));

        // Requirement 4: allow querying any day manually.
        while (true) {
            System.out.println();
            System.out.print("Consultar outro dia? Digite o nome do dia (ou 'sair' para encerrar): ");
            String dayText = readLine(input, "");

            if (dayText.isEmpty() || dayText.equalsIgnoreCase("sair")) {
                break;
            }

            // Show the canonical label for a valid day; otherwise echo the typed text.
            Optional<Weekday> day = Weekday.parse(dayText);
            String label = day.map(Weekday::label).orElse(dayText);

            DayStrategy strategy = selector.select(dayText);
            System.out.println();
            print(name, label, strategy.execute(userInfo));
        }

        System.out.println();
        System.out.println("Encerrado. Bons estudos!");
        input.close();
    }

    /** Prints one result block in the format used by the assignment example. */
    private static void print(String name, String dayLabel, Behavior behavior) {
        System.out.println("Usuário: " + name);
        System.out.println("Dia consultado: " + dayLabel);
        System.out.println("Prioridade: " + behavior.priority().label());
        System.out.println("Mensagem: " + behavior.message());
    }

    /** Reads a trimmed line, falling back to a default when empty or absent. */
    private static String readLine(Scanner input, String fallback) {
        if (!input.hasNextLine()) {
            return fallback;
        }
        String line = input.nextLine().trim();
        return line.isEmpty() ? fallback : line;
    }
}
