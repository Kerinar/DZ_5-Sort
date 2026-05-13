import java.util.Comparator;
import java.util.List;

public class BubbleSort implements SortingStrategy {
    @Override
    public void sort(List<Student> students, Comparator<Student> comparator) {
        int n = students.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (comparator.compare(students.get(j), students.get(j + 1)) > 0) {
                    Student temp = students.get(j);
                    students.set(j, students.get(j + 1));
                    students.set(j + 1, temp);
                }
            }
        }
    }
}