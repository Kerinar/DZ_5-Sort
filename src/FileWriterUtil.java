import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Дополнительное задание №2: Запись отсортированных коллекций в файл в режиме добавления.
 * Участник 7
 */
public class FileWriterUtil {

    private static final DateTimeFormatter DATE_FORMATTER = 
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * Записывает коллекцию студентов в файл в режиме добавления (append).
     *
     * @param filename имя файла
     * @param students список студентов
     */
    public static void appendToFile(String filename, List<Student> students) {
        appendToFile(filename, students, "Без описания");
    }

    /**
     * Записывает коллекцию студентов в файл с комментарием.
     */
    public static void appendToFile(String filename, List<Student> students, String comment) {
        if (students == null || students.isEmpty()) {
            System.out.println("⚠ Нет данных для сохранения");
            return;
        }

        try (PrintWriter pw = new PrintWriter(new FileWriter(filename, true))) {
            pw.println();
            pw.println("╔════════════════════════════════════════════════════════════╗");
            pw.println("║ Запись: " + LocalDateTime.now().format(DATE_FORMATTER) + " ║");
            pw.println("║ " + centerText(comment, 60) + " ║");
            pw.println("╠════════════════════════════════════════════════════════════╣");
            pw.println("║ Всего записей: " + String.format("%-47d", students.size()) + "║");
            pw.println("╠════════════════════════════════════════════════════════════╣");

            int counter = 1;
            for (Student s : students) {
                String line = String.format("║ %-3d │ %-20s │ GPA: %-5.2f │ Зачётка: %-10s ║",
                        counter++,
                        truncate(s.getGroupNumber(), 20),
                        s.getGpa(),
                        truncate(s.getRecordBookNumber(), 10));
                pw.println(line);
            }

            pw.println("╚════════════════════════════════════════════════════════════╝");
            pw.println();

            System.out.println("✅ Данные успешно добавлены в файл: " + filename);
            System.out.println("   Добавлено записей: " + students.size());

        } catch (IOException e) {
            System.err.println("❌ Ошибка записи в файл: " + e.getMessage());
        }
    }

    /**
     * Простая запись в CSV формате.
     */
    public static void appendToCsv(String filename, List<Student> students) {
        if (students == null || students.isEmpty()) {
            return;
        }

        try (PrintWriter pw = new PrintWriter(new FileWriter(filename, true))) {
            for (Student s : students) {
                pw.printf("%s,%s,%.2f%n",
                        s.getRecordBookNumber(),
                        s.getGroupNumber(),
                        s.getGpa());
            }
            System.out.println("✅ CSV данные добавлены в: " + filename);
        } catch (IOException e) {
            System.err.println("❌ Ошибка: " + e.getMessage());
        }
    }

    private static String centerText(String text, int width) {
        if (text.length() >= width) return text.substring(0, width);
        int padding = (width - text.length()) / 2;
        return " ".repeat(padding) + text + " ".repeat(width - text.length() - padding);
    }

    private static String truncate(String s, int maxLength) {
        if (s == null) return "null";
        if (s.length() <= maxLength) return s;
        return s.substring(0, maxLength - 3) + "...";
    }
}