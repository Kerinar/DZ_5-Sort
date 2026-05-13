package file;

import Student;
import com.group.app.collections.MyCustomList;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FileWriterUtil {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    
    public static void appendToFile(String filename, MyCustomList<Student> collection) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(filename, true))) {
            pw.println();
            pw.println("=== ЗАПИСЬ ОТ " + LocalDateTime.now().format(formatter) + " ===");
            pw.println("Всего записей: " + collection.size());
            
            for (int i = 0; i < collection.size(); i++) {
                Student student = collection.get(i);
                pw.println(student.getGroupNumber() + "," + 
                          student.getGpa() + "," + 
                          student.getRecordBookNumber());
            }
            
            pw.println("=========================================");
            
            System.out.println("=== ДОП. ЗАДАНИЕ №2 ===");
            System.out.println("Данные добавлены в файл: " + filename);
            System.out.println("Режим: добавление (append)");
            System.out.println("=====================\n");
            
        } catch (IOException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }
}