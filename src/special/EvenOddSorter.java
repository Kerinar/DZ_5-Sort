package special;

import Student;
import com.group.app.collections.MyCustomList;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class EvenOddSorter {
    
    public static void sortByEvenRecordBook(MyCustomList<Student> collection, Comparator<Student> comparator) {
        List<Student> evenStudents = new ArrayList<>();
        List<Integer> evenIndices = new ArrayList<>();
        
        for (int i = 0; i < collection.size(); i++) {
            Student student = collection.get(i);
            String recordBook = student.getRecordBookNumber();
            
            int lastNumber = extractNumber(recordBook);
            if (lastNumber % 2 == 0) {
                evenStudents.add(student);
                evenIndices.add(i);
            }
        }
        
        evenStudents.sort(comparator);
        
        for (int i = 0; i < evenIndices.size(); i++) {
            collection.set(evenIndices.get(i), evenStudents.get(i));
        }
        
        System.out.println("=== ДОП. ЗАДАНИЕ №1 ===");
        System.out.println("Сортировка четных элементов выполнена");
        System.out.println("Отсортировано: " + evenStudents.size() + " элементов");
        System.out.println("=====================\n");
    }
    
    private static int extractNumber(String recordBook) {
        if (recordBook == null || recordBook.isEmpty()) return 0;
        String digits = recordBook.replaceAll("\\D", "");
        if (digits.isEmpty()) return 0;
        return Integer.parseInt(digits);
    }
}