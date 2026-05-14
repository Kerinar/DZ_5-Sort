// StudentInputHandler.java
import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class InputHandler {

    private static final Random RANDOM = new Random();
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    public static String generateRandomString(int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int index = RANDOM.nextInt(CHARACTERS.length());
            sb.append(CHARACTERS.charAt(index));
        }
        return sb.toString();
    }

    // ─────────────────────────────────────────────────────────────────────────
    // Главный метод для заполнения коллекции с выбором способа ввода
    // ─────────────────────────────────────────────────────────────────────────
    public static List<Student> fillCollection(Scanner scanner) {
        System.out.println("\n=== Заполнение коллекции студентов ===");
        System.out.println("Выберите способ заполнения:");
        System.out.println("1. Случайные данные");
        System.out.println("2. Ручной ввод");
        System.out.println("3. Загрузка из файла");
        System.out.print("Ваш выбор (1-3): ");

        int choice = readIntInput(scanner, 1, 3);

        System.out.print("Введите количество студентов: ");
        int size = readIntInput(scanner, 1, 1000);

        switch (choice) {
            case 1:
                return fillRandomly(size);
            case 2:
                return fillManually(scanner, size);
            case 3:
                System.out.print("Введите путь к файлу: ");
                String filePath = scanner.nextLine().trim();
                return fillFromFile(filePath, size);
            default: // неважно, уже проверили что-бы ввели от 1 до 3
                return fillRandomly(size);
        }
    }

    // ─────────────────────────────────────────────────────────────────────────
    // 1. Заполнение случайными данными с использованием Stream API
    // ─────────────────────────────────────────────────────────────────────────
    public static List<Student> fillRandomly(int size) {
        return Stream.generate(InputHandler::generateRandomStudent)
                .limit(size)
                .collect(Collectors.toList());
    }

    private static Student generateRandomStudent() {
        String recordBook = String.valueOf(RANDOM.nextInt(999999)); // Случайное число
        String group = generateRandomString(6); // случайная строка из букв и цифр
        double gpa = 2.0 + RANDOM.nextDouble() * 3.0; // случайное дробное число от 2.0 до 5.0

        return new Student.Builder(recordBook)
                .groupNumber(group)
                .gpa(Math.round(gpa * 100.0) / 100.0)
                .build();
    }

    // ─────────────────────────────────────────────────────────────────────────
    // 2. Ручной ввод с валидацией
    // ─────────────────────────────────────────────────────────────────────────
    public static List<Student> fillManually(Scanner scanner, int size) {
        List<Student> students = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            System.out.println("\n--- Ввод данных студента " + (i + 1) + " ---");

            String recordBook = validateRecordBookNumber(scanner);
            String group = validateGroupNumber(scanner);
            double gpa = validateGpa(scanner);

            try {
                Student student = new Student.Builder(recordBook)
                        .groupNumber(group)
                        .gpa(gpa)
                        .build();
                students.add(student);
                System.out.println("✓ Студент успешно добавлен: " + student);
            } catch (IllegalArgumentException e) {
                System.err.println("Ошибка при создании студента: " + e.getMessage());
                i--; // повторяем ввод для этого студента
            }
        }
        return students;
    }

    private static String validateRecordBookNumber(Scanner scanner) {
        while (true) {
            System.out.print("Номер зачётной книжки (не пустой, уникальный): ");
            String input = scanner.nextLine().trim();

            if (input.isBlank()) {
                System.err.println("Ошибка: Номер зачётной книжки не может быть пустым!");
                continue;
            }

            if (!input.matches("[0-9]+")) {
                System.err.println("Ошибка: Номер зачётной книжки может содержать только цифры!");
                continue;
            }

            return input;
        }
    }

    private static String validateGroupNumber(Scanner scanner) {
        while (true) {
            System.out.print("Номер группы (например: 13122, ММФ12): ");
            String input = scanner.nextLine().trim();

            if (input.isBlank()) {
                System.out.println("Группа не указана, будет установлено значение по умолчанию 'Без группы'");
                return "Без группы";
            }

            if (input.length() >= 3 && input.length() <= 10) {
                return input;
            }

            System.err.println("Ошибка: Номер группы должен быть длиной от 3 до 10 символов!");
        }
    }

    private static double validateGpa(Scanner scanner) {
        while (true) {
            System.out.print("Средний балл (от 0.0 до 5.0): ");
            try {
                String input = scanner.nextLine().trim().replace(",", ".");
                double gpa = Double.parseDouble(input);

                if (gpa >= 0.0 && gpa <= 5.0) {
                    return Math.round(gpa * 100.0) / 100.0;
                } else {
                    System.err.println("Ошибка: Средний балл должен быть в диапазоне 0.0 - 5.0!");
                }
            } catch (NumberFormatException e) {
                System.err.println("Ошибка: Введите корректное число!");
            }
        }
    }

    // ─────────────────────────────────────────────────────────────────────────
    // 3. Загрузка из файла с валидацией данных
    // ─────────────────────────────────────────────────────────────────────────
    public static List<Student> fillFromFile(String filePath, int expectedSize) {
        List<Student> students = new ArrayList<>();

        try {
            List<String> lines = Files.readAllLines(Paths.get(filePath));

            if (lines.isEmpty()) {
                System.err.println("Ошибка: Файл пуст!");
                return students;
            }

            for (int i = 0; i < lines.size() && students.size() < expectedSize; i++) {
                String line = lines.get(i).trim();
                if (line.isEmpty() || line.startsWith("#")) continue;

                Student student = parseStudentFromLine(line, i + 1);
                if (student != null) {
                    students.add(student);
                }
            }

            if (students.isEmpty()) {
                System.err.println("Предупреждение: Не удалось загрузить ни одного корректного студента из файла!");
            } else {
                System.out.println("✓ Загружено " + students.size() + " студентов из файла");
            }

        } catch (FileNotFoundException e) {
            System.err.println("Ошибка: Файл не найден - " + filePath);
        } catch (IOException e) {
            System.err.println("Ошибка при чтении файла: " + e.getMessage());
        }

        return students;
    }

    private static Student parseStudentFromLine(String line, int lineNumber) {
        // Ожидаемый формат: "номер_группы|средний_балл|номер_зачетки"
        // или "номер_группы, средний_балл, номер_зачетки"
        String[] parts = line.split("[|,]\\s*");

        if (parts.length != 3) {
            System.err.println("Ошибка в строке " + lineNumber +
                    ": ожидается 3 поля, получено " + parts.length);
            return null;
        }

        try {
            String groupNumber = parts[0].trim();
            if (groupNumber.isEmpty()) groupNumber = "Без группы";

            double gpa = Double.parseDouble(parts[1].trim().replace(",", "."));
            String recordBookNumber = parts[2].trim();

            // Валидация GPA
            if (gpa < 0.0 || gpa > 5.0) {
                System.err.println("Ошибка в строке " + lineNumber +
                        ": средний балл должен быть в диапазоне 0.0-5.0 (получено: " + gpa + ")");
                return null;
            }

            // Валидация номера зачетки
            if (recordBookNumber.isBlank()) {
                System.err.println("Ошибка в строке " + lineNumber + ": номер зачётной книжки не может быть пустым");
                return null;
            }

            return new Student.Builder(recordBookNumber)
                    .groupNumber(groupNumber)
                    .gpa(gpa)
                    .build();

        } catch (NumberFormatException e) {
            System.err.println("Ошибка в строке " + lineNumber + ": некорректный формат среднего балла");
            return null;
        } catch (IllegalArgumentException e) {
            System.err.println("Ошибка в строке " + lineNumber + ": " + e.getMessage());
            return null;
        }
    }

    // ─────────────────────────────────────────────────────────────────────────
    // Вспомогательные методы
    // ─────────────────────────────────────────────────────────────────────────
    private static int readIntInput(Scanner scanner, int min, int max) {
        while (true) {
            try {
                String input = scanner.nextLine().trim();
                int value = Integer.parseInt(input);
                if (value >= min && value <= max) {
                    return value;
                }
                System.err.println("Ошибка: Введите число от " + min + " до " + max);
            } catch (NumberFormatException e) {
                System.err.println("Ошибка: Введите целое число!");
            }
        }
    }

    // Пример формата файла для тестирования:
    // ДМ877|4.5|123456
    // 98822|3.8|874192
    // AI301,4.2,000000

    public static void saveExampleFileFormat(String filePath) throws IOException {
        List<String> exampleLines = Arrays.asList(
                "# Формат файла: номер_группы|средний_балл|номер_зачетки",
                "CS101|4.5|2021001",
                "SE202|3.8|2021002",
                "AI301|4.2|2021003",
                "DS101|2.9|2021004"
        );
        Files.write(Paths.get(filePath), exampleLines);
    }
}