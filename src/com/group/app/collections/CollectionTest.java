package com.group.app.collections;

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

        if (testList.size() == 3) {
            System.out.println("✅ Тест размера: Пройден");
        } else {
            System.out.println("❌ Тест размера: Ошибка");
        }

        // Проверка получения значения
        if (testList.get(1) == 20) {
            System.out.println("✅ Тест получения элемента: Пройден");
        } else {
            System.out.println("❌ Тест получения элемента: Ошибка");
        }

        // Проверка метода set
        testList.set(1, 50);
        if (testList.get(1) == 50) {
            System.out.println("✅ Тест метода set: Пройден");
        }

        System.out.println("--- Конец теста ---");
    }
}