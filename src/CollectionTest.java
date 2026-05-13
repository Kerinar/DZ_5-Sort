/**
 * Класс для ручного тестирования кастомной коллекции.
 * Это выполнение части задания №7 (Тесты).
 */
public class CollectionTest {
    public static void main(String[] args) {

        MyCustomList<Integer> testList = new MyCustomList<>();

        System.out.println("--- Запуск теста коллекции ---");

        // Проверка добавления и размера
        testList.add(10);
        testList.add(20);
        testList.add(30);

        // Вывод результатов для ручной проверки
        System.out.println("Текущий размер коллекции (ожидается 3): " + testList.size());
        System.out.println("Элемент под индексом 0 (ожидается 10): " + testList.get(0));
        System.out.println("Элемент под индексом 1 (ожидается 20): " + testList.get(1));
        System.out.println("Элемент под индексом 2 (ожидается 30): " + testList.get(2));

        // Проверка метода set
        testList.set(1, 99);
        System.out.println("После изменения - индекс 1 (ожидается 99): " + testList.get(1));

        // Итоговый статус ручного теста
        if (testList.size() == 3 && testList.get(0) == 10 && testList.get(1) == 99) {
            System.out.println("[УСПЕШНО] Ваш личный код MyCustomList прошел проверку!");
        } else {
            System.err.println("[ОШИБКА] Что-то работает не так.");
        }
    }
}


