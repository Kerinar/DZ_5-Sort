import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Дополнительное задание №4: Многопоточный подсчёт количества вхождений элемента N в коллекцию.
 * Участник 7
 */
public class OccurrenceCounter {

    public static <T> int countOccurrences(List<T> collection, T target) {
        if (collection == null || collection.isEmpty()) {
            System.out.println("⚠ Коллекция пуста или null");
            return 0;
        }

        int processors = Runtime.getRuntime().availableProcessors();
        int threads = Math.min(processors, collection.size());
        
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║     МНОГОПОТОЧНЫЙ ПОДСЧЁТ              ║");
        System.out.println("╠════════════════════════════════════════╣");
        System.out.println("║ Доступно процессоров: " + String.format("%-10d", processors) + "║");
        System.out.println("║ Используется потоков: " + String.format("%-10d", threads) + "║");
        System.out.println("║ Размер коллекции: " + String.format("%-10d", collection.size()) + "║");
        System.out.println("╚════════════════════════════════════════╝");

        ExecutorService executor = Executors.newFixedThreadPool(threads);
        AtomicInteger counter = new AtomicInteger(0);

        int chunkSize = collection.size() / threads;
        if (chunkSize == 0) chunkSize = 1;

        long startTime = System.currentTimeMillis();

        for (int t = 0; t < threads; t++) {
            final int start = t * chunkSize;
            final int end = (t == threads - 1) ? collection.size() : (t + 1) * chunkSize;

            executor.submit(() -> {
                for (int i = start; i < end; i++) {
                    T element = collection.get(i);
                    if (element != null && element.equals(target)) {
                        counter.incrementAndGet();
                    }
                }
            });
        }

        executor.shutdown();
        try {
            executor.awaitTermination(30, TimeUnit.SECONDS);
            long endTime = System.currentTimeMillis();
            System.out.println("\n⏱ Время выполнения: " + (endTime - startTime) + " мс");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("❌ Ошибка: " + e.getMessage());
        }

        return counter.get();
    }

    public static int countStudentOccurrences(List<Student> students, Student target) {
        return countOccurrences(students, target);
    }
}