package thread;

import Student;
import com.group.app.collections.MyCustomList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class OccurrenceCounter {
    
    public static int countOccurrences(MyCustomList<Student> collection, String recordBookNumber) {
        if (collection == null || collection.size() == 0) {
            return 0;
        }
        
        AtomicInteger counter = new AtomicInteger(0);
        int numberOfThreads = Math.min(Runtime.getRuntime().availableProcessors(), collection.size());
        ExecutorService executor = Executors.newFixedThreadPool(numberOfThreads);
        
        int chunkSize = collection.size() / numberOfThreads;
        
        for (int i = 0; i < numberOfThreads; i++) {
            final int start = i * chunkSize;
            final int end = (i == numberOfThreads - 1) ? collection.size() : (i + 1) * chunkSize;
            
            executor.submit(() -> {
                for (int j = start; j < end; j++) {
                    if (collection.get(j).getRecordBookNumber().equals(recordBookNumber)) {
                        counter.incrementAndGet();
                    }
                }
            });
        }
        
        executor.shutdown();
        try {
            executor.awaitTermination(30, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        System.out.println("=== ДОП. ЗАДАНИЕ №4 ===");
        System.out.println("Многопоточный подсчет вхождений");
        System.out.println("Номер зачетки: " + recordBookNumber);
        System.out.println("Результат: " + counter.get() + " вхождений");
        System.out.println("=====================\n");
        
        return counter.get();
    }
}