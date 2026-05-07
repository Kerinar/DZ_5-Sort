package com.group.app.collections;

/**
 * Кастомная реализация динамического массива (аналог ArrayList).
 * Выполнено в рамках Задания №2.
 * @param <T> тип хранимых элементов
 */
public class MyCustomList<T> {


    private Object[] elements;


    private int size = 0;


    private static final int DEFAULT_CAPACITY = 10;

    public MyCustomList() {
        this.elements = new Object[DEFAULT_CAPACITY];
    }

    /**
     * Добавляет элемент в конец списка.
     * Если массив заполнен, он автоматически расширяется.
     */
    public void add(T element) {
        if (size == elements.length) {
            grow();
        }
        elements[size++] = element;
    }

    /**
     * Возвращает элемент по указанному индексу.
     */

    public T get(int index) {
        checkIndex(index);
        return (T) elements[index];
    }

    /**
     * Заменяет элемент в указанной позиции.
     * Метод необходим для работы алгоритмов сортировки.
     */
    public void set(int index, T element) {
        checkIndex(index);
        elements[index] = element;
    }

    /**
     * Возвращает текущее количество элементов в коллекции.
     */
    public int size() {
        return size;
    }

    /**
     * Внутренний метод для увеличения размера массива.
     * Увеличивает вместимость примерно в 1.5 раза.
     */
    private void grow() {
        int oldCapacity = elements.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1) + 1;
        Object[] newElements = new Object[newCapacity];


        System.arraycopy(elements, 0, newElements, 0, oldCapacity);
        elements = newElements;
    }

    /**
     * Проверка корректности индекса.
     */
    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Индекс: " + index + ", Размер: " + size);
        }
    }
}