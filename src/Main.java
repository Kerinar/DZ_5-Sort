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

        List<Student> students = new ArrayList<>();
        students.add(s1);
        students.add(s2);
        students.add(s3);

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\nВыберите алгоритм сортировки:");
            System.out.println("1 - Bubble Sort");
            System.out.println("2 - Quick Sort");
            System.out.println("3 - Selection Sort");
            System.out.println("0 - Выход");
            int algo = scanner.nextInt();

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
            int field = scanner.nextInt();

            Comparator<Student> comparator;
            switch (field) {
                case 1:  comparator = Student.BY_GPA;           break;
                case 2:  comparator = Student.BY_GROUP;         break;
                case 3:  comparator = Student.BY_RECORD_BOOK;   break;
                default:
                    System.out.println("Неверный выбор поля");
                    continue;
            }

            SortingStrategy strategy;
            switch (algo) {
                case 1:  strategy = new BubbleSort();    break;
                case 2:  strategy = new QuickSort();     break;
                case 3:  strategy = new SelectionSort(); break;
                default:  continue;
            }

            strategy.sort(students, comparator);

            System.out.println("\nОтсортированный список:");
            for (Student s : students) {
                System.out.println(s);
            }
        }
    }
}