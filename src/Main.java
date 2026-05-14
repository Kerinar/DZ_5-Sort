import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Student s1 = new Student.Builder("ЗК-001")
                .groupNumber("ИТ-21").gpa(3.8).build();

        Student s2 = new Student.Builder("ЗК-002")
                .groupNumber("ИТ-21").gpa(2.5).build();

        Student s3 = new Student.Builder("ЗК-003")
                .groupNumber("ИТ-22").gpa(4.9).build();

        System.out.println(s1);
        System.out.println(s2);
        System.out.println(s3);

        System.out.println("\nПо GPA (убыв): " + Student.BY_GPA.compare(s1, s2));
        System.out.println("По группе:     " + Student.BY_GROUP.compare(s1, s3));

        Scanner scanner = new Scanner(System.in);

        List<Student> students = InputHandler.fillCollection(scanner);
        if (students.isEmpty()) {
            System.out.println("Коллекция пуста, завершение работы.");
            return;
        }

        boolean running = true;

        while (running) {
            System.out.println("\nВыберите алгоритм сортировки:");
            System.out.println("1 - Bubble Sort");
            System.out.println("2 - Quick Sort");
            System.out.println("3 - Selection Sort");
            System.out.println("0 - Выход");

            int algo = 0;
            try {
                algo = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Неверный ввод, попробуйте снова.");
                continue;
            }

            if (algo == 0) {
                running = false;
                break;
            }

            if (algo < 1 || algo > 3) {
                System.out.println("Неверный выбор алгоритма");
                continue;
            }

            System.out.println("Выберите поле для сортировки:");
            System.out.println("1 - GPA (по убыванию)");
            System.out.println("2 - Группа (по возрастанию)");
            System.out.println("3 - Номер зачётки (по возрастанию)");

            int field = 0;
            try {
                field = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Неверный ввод, попробуйте снова.");
                continue;
            }

            Comparator<Student> comparator;
            String fieldName;
            switch (field) {
                case 1:
                    comparator = Student.BY_GPA;
                    fieldName = "GPA (убыв.)";
                    break;
                case 2:
                    comparator = Student.BY_GROUP;
                    fieldName = "Группа";
                    break;
                case 3:
                    comparator = Student.BY_RECORD_BOOK;
                    fieldName = "Номер зачётки";
                    break;
                default:
                    System.out.println("Неверный выбор поля");
                    continue;
            }

            SortingStrategy strategy;
            String algoName;
            switch (algo) {
                case 1:
                    strategy = new BubbleSort();
                    algoName = "Bubble Sort";
                    break;
                case 2:
                    strategy = new QuickSort();
                    algoName = "Quick Sort";
                    break;
                case 3:
                    strategy = new SelectionSort();
                    algoName = "Selection Sort";
                    break;
                default:
                    continue;
            }

            strategy.sort(students, comparator);

            System.out.println("\nОтсортированный список:");
            for (Student s : students) {
                System.out.println(s);
            }

            System.out.print("\nСохранить результат в файл? (1 - да, 2 - нет): ");
            String saveChoice = scanner.nextLine().trim();
            if ("1".equals(saveChoice)) {
                String comment = algoName + " по полю " + fieldName;
                FileWriterUtil.appendToFile("sorted_students.txt", students, comment);
            }
        }
    }
}