package app.utils;

import java.time.Year;
import java.util.Scanner;

public class InputUtils {

    public static int readYear(Scanner scanner, String prompt) {
        int currentYear = Year.now().getValue();
        int year;

        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine();

            try {
                year = Integer.parseInt(input);
                if (year > 0 && year <= currentYear) {
                    return year;
                } else {
                    System.out.printf("Please enter a year between 1 and %d.%n", currentYear);
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid year.");
            }
        }
    }

    // Остальные методы остаются без изменений...

    public static int readInt(Scanner scanner, String prompt) {
        int value;
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine();
            try {
                value = Integer.parseInt(input);
                if (value > 0) return value;
                else System.out.println("Please enter a positive number.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid number. Try again.");
            }
        }
    }

    public static String capitalizeFirst(String input) {
        if (input == null || input.isEmpty()) return input;
        return input.substring(0, 1).toUpperCase() + input.substring(1).toLowerCase();
    }

    public static String capitalizeEachWord(String input) {
        String[] words = input.trim().split("\\s+");
        StringBuilder result = new StringBuilder();
        for (String word : words) {
            if (!word.isEmpty()) {
                result.append(capitalizeFirst(word)).append(" ");
            }
        }
        return result.toString().trim();
    }
}