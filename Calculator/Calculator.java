import java.util.Scanner;

public class Calculator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите выражение (например, 2 * 6.5): ");
        String line = scanner.nextLine();
        scanner.close();

        try {
            double result = calculate(line);
            System.out.println("Результат: " + result);
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    public static double calculate(String line) {
        String[] parts = line.trim().split("\\s+");

        if (parts.length != 3) {
            throw new IllegalArgumentException(
                "Ожидается формат: число операция число, например 2 * 6.5");
        }

        double a = parseNumber(parts[0]);
        String op = parts[1];
        double b = parseNumber(parts[2]);

        switch (op) {
            case "+": return a + b;
            case "-": return a - b;
            case "*": return a * b;
            case "/":
                if (b == 0) {
                    throw new IllegalArgumentException("Деление на ноль!");
                }
                return a / b;
            default:
                throw new IllegalArgumentException("Неизвестная операция: " + op);
        }
    }

    private static double parseNumber(String s) {
        try {
            return Double.parseDouble(s);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("\"" + s + "\" — не число");
        }
    }
}
