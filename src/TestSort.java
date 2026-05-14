import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TestSort {
    void main(){
        Student s1 = new Student.Builder("ЗК-001")
                .groupNumber("ИТ-21").gpa(3.8).build();

        Student s2 = new Student.Builder("ЗК-002")
                .groupNumber("ИТ-21").gpa(2.5).build();

        Student s3 = new Student.Builder("ЗК-003")
                .groupNumber("ИТ-22").gpa(4.9).build();

        List<Student> students1 = new ArrayList<>();
        students1.add(s1);
        students1.add(s2);
        students1.add(s3);

        List<Student> students2 = new ArrayList<>();
        students2.add(s1);
        students2.add(s2);
        students2.add(s3);

        List<Student> students3 = new ArrayList<>();
        students3.add(s1);
        students3.add(s2);
        students3.add(s3);

        IO.println("");
        IO.println(students1);
        IO.println("");

        Comparator<Student> comparator1 = Student.BY_GPA;
        Comparator<Student> comparator2 = Student.BY_GROUP;
        Comparator<Student> comparator3 = Student.BY_RECORD_BOOK;

        SortingStrategy sort1 = new BubbleSort();
        SortingStrategy sort2 = new QuickSort();
        SortingStrategy sort3 = new SelectionSort();

        sort1.sort(students1, comparator1);
        sort1.sort(students2, comparator2);
        sort1.sort(students3, comparator3);

        for(int i = 0; i < 2; i++){
            if(comparator1.compare(students1.get(i), students1.get(i+1)) > 0){
                IO.println("Сортировка пузырьком по баллу не работает");
            }
        }

        for(int i = 0; i < 2; i++){
            if(comparator2.compare(students2.get(i), students2.get(i+1)) > 0){
                IO.println("Сортировка пузырьком по группе не работает");
            }
        }

        for(int i = 0; i < 2; i++){
            if(comparator3.compare(students3.get(i), students3.get(i+1)) > 0){
                IO.println("Сортировка пузырьком по зачетке не работает");
            }
        }

        IO.println("Bubble Sort");
        IO.println(students1);
        IO.println(students2);
        IO.println(students3);
        //-------------------------------------------------------
        sort2.sort(students2, comparator1);
        sort2.sort(students3, comparator2);
        sort2.sort(students1, comparator3);

        for(int i = 0; i < 2; i++){
            if(comparator1.compare(students2.get(i), students2.get(i+1)) > 0){
                IO.println("Быстрая сортировка по баллу не работает");
            }
        }

        for(int i = 0; i < 2; i++){
            if(comparator2.compare(students3.get(i), students3.get(i+1)) > 0){
                IO.println("Быстрая сортировка по группе не работает");
            }
        }

        for(int i = 0; i < 2; i++){
            if(comparator3.compare(students1.get(i), students1.get(i+1)) > 0){
                IO.println("Быстрая сортировка по зачетке не работает");
            }
        }

        IO.println("Quick Sort");
        IO.println(students2);
        IO.println(students3);
        IO.println(students1);
        //-------------------------------------------------------
        sort3.sort(students3, comparator1);
        sort3.sort(students1, comparator2);
        sort3.sort(students2, comparator3);

        for(int i = 0; i < 2; i++){
            if(comparator1.compare(students3.get(i), students3.get(i+1)) > 0){
                IO.println("Сортировка вставками по баллу не работает");
            }
        }

        for(int i = 0; i < 2; i++){
            if(comparator2.compare(students1.get(i), students1.get(i+1)) > 0){
                IO.println("Сортировка вставками по группе не работает");
            }
        }

        for(int i = 0; i < 2; i++){
            if(comparator3.compare(students2.get(i), students2.get(i+1)) > 0){
                IO.println("Сортировка вставками по зачетке не работает");
            }
        }

        IO.println("Selection Sort");
        IO.println(students3);
        IO.println(students1);
        IO.println(students2);
    }
}
