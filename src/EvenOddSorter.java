import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Дополнительное задание №1: Сортировка объектов с чётными значениями числового поля.
 * Объекты с чётными значениями сортируются в натуральном порядке,
 * объекты с нечётными остаются на исходных позициях.
 * Участник 7
 */
public class EvenOddSorter {

    /**
     * Сортировка студентов по чётности номера зачётной книжки.
     * У студентов с ЧЁТНЫМ номером зачётки сортируются по указанному компаратору.
     * Студенты с НЕЧЁТНЫМ номером остаются на своих местах.
     *
     * @param students список студентов
     * @param comparator компаратор для сортировки
     */
    public static void sortByEvenRecordBook(List<Student> students, Comparator<Student> comparator) {
        if (students == null || students.size() <= 1) {
            return;
        }

        // Собираем студентов с чётным номером зачётки
        List<Student> evenStudents = new ArrayList<>();
        List<Integer> evenIndices = new ArrayList<>();

        for (int i = 0; i < students.size(); i++) {
            Student s = students.get(i);
            // Пытаемся извлечь число из номера зачётки
            int recordNumber = extractNumberFromRecordBook(s.getRecordBookNumber());
            if (recordNumber % 2 == 0) {
                evenStudents.add(s);
                evenIndices.add(i);
            }
        }

        // Сортируем только чётных студентов
        evenStudents.sort(comparator);

        // Возвращаем на места
        for (int i = 0; i < evenIndices.size(); i++) {
            students.set(evenIndices.get(i), evenStudents.get(i));
        }

        System.out.println("✅ Специальная сортировка (чётные элементы) выполнена");
        System.out.println("   Отсортировано студентов с чётным номером зачётки: " + evenStudents.size());
        System.out.println("   Студенты с нечётным номером остались на своих местах");
    }

    /**
     * Альтернативная версия: сортировка по чётности GPA (умножаем на 100 для целого числа).
     */
    public static void sortByEvenGpa(List<Student> students, Comparator<Student> comparator) {
        if (students == null || students.size() <= 1) {
            return;
        }

        List<Student> evenStudents = new ArrayList<>();
        List<Integer> evenIndices = new ArrayList<>();

        for (int i = 0; i < students.size(); i++) {
            Student s = students.get(i);
            int gpaInt = (int) (s.getGpa() * 100); // Превращаем 4.50 -> 450
            if (gpaInt % 2 == 0) {
                evenStudents.add(s);
                evenIndices.add(i);
            }
        }

        evenStudents.sort(comparator);

        for (int i = 0; i < evenIndices.size(); i++) {
            students.set(evenIndices.get(i), evenStudents.get(i));
        }

        System.out.println("✅ Специальная сортировка (чётные GPA) выполнена");
    }

    /**
     * Извлекает числовое значение из номера зачётки.
     * Например: "ЗК-12345" -> 12345, "2021001" -> 2021001
     */
    private static int extractNumberFromRecordBook(String recordBook) {
        if (recordBook == null) return 0;
        // Удаляем всё, кроме цифр
        String digitsOnly = recordBook.replaceAll("[^0-9]", "");
        if (digitsOnly.isEmpty()) return 0;
        return Integer.parseInt(digitsOnly);
    }
}