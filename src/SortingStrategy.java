import java.util.Comparator;
import java.util.List;

public interface SortingStrategy {
    void sort(List<Student> students, Comparator<Student> comparator);
}