import java.util.Comparator;
import java.util.List;

public class SelectionSort implements SortingStrategy {
    @Override
    public void sort(List<Student> students, Comparator<Student> comparator) {
        int n = students.size();
        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < n; j++) {
                if (comparator.compare(students.get(j), students.get(minIdx)) < 0) {
                    minIdx = j;
                }
            }
            if (minIdx != i) {
                Student temp = students.get(i);
                students.set(i, students.get(minIdx));
                students.set(minIdx, temp);
            }
        }
    }
}