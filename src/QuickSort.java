import java.util.Comparator;
import java.util.List;

public class QuickSort implements SortingStrategy {
    @Override
    public void sort(List<Student> students, Comparator<Student> comparator) {
        quickSort(students, 0, students.size() - 1, comparator);
    }

    private void quickSort(List<Student> list, int low, int high, Comparator<Student> comparator) {
        if (low < high) {
            int pi = partition(list, low, high, comparator);
            quickSort(list, low, pi - 1, comparator);
            quickSort(list, pi + 1, high, comparator);
        }
    }

    private int partition(List<Student> list, int low, int high, Comparator<Student> comparator) {
        Student pivot = list.get(high);
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (comparator.compare(list.get(j), pivot) <= 0) {
                i++;
                Student temp = list.get(i);
                list.set(i, list.get(j));
                list.set(j, temp);
            }
        }
        Student temp = list.get(i + 1);
        list.set(i + 1, list.get(high));
        list.set(high, temp);
        return i + 1;
    }
}