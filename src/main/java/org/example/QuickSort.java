package org.example;
import java.util.Comparator;

/**
 * Внешний класс для сортировки списка с использованием QuickSort.
 */
public class QuickSort {

    /**
     * Сортирует переданный список с использованием QuickSort.
     *
     * @param list       список, который нужно отсортировать
     * @param comparator компаратор для сравнения элементов; если {@code null}, используется Comparable<T>
     * @param <T>        тип элементов списка
     * @throws IllegalArgumentException если элементы не Comparable и не передан Comparator
     */
    public static <T> void sort(CustomArrayList<T> list, Comparator<T> comparator) {
        if (list.size() <= 1) return;

        if (comparator == null) {
            throw new NullPointerException("Comparator cannot be null");
        }

        quickSort(list, 0, list.size() - 1, comparator);
    }

    /**
     * Внутренний метод быстрой сортировки (QuickSort).
     *
     * @param list       список
     * @param low        начальный индекс
     * @param high       конечный индекс
     * @param comparator компаратор для сравнения элементов
     * @param <T>        тип элементов списка
     */
    private static <T> void quickSort(CustomArrayList<T> list, int low, int high, Comparator<T> comparator) {
        if (low < high) {
            int pivotIndex = partition(list, low, high, comparator);
            quickSort(list, low, pivotIndex - 1, comparator);
            quickSort(list, pivotIndex + 1, high, comparator);
        }
    }

    /**
     * Разбиение списка для алгоритма QuickSort.
     *
     * @param list       список
     * @param low        начальный индекс
     * @param high       конечный индекс
     * @param comparator компаратор для сравнения элементов
     * @param <T>        тип элементов списка
     * @return индекс опорного элемента
     */
    private static <T> int partition(CustomArrayList<T> list, int low, int high, Comparator<T> comparator) {
        T pivot = list.get(high);
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (compare(list.get(j), pivot, comparator) < 0) {
                i++;
                swap(list, i, j);
            }
        }
        swap(list, i + 1, high);
        return i + 1;
    }

    /**
     * Сравнивает два элемента с использованием компаратора или Comparable.
     *
     * @param a          первый элемент
     * @param b          второй элемент
     * @param comparator компаратор для сравнения
     * @param <T>        тип элементов
     * @return отрицательное число, если a < b; 0, если a == b; положительное число, если a > b
     * @throws IllegalArgumentException если элементы не Comparable и не передан Comparator
     */
    private static <T> int compare(T a, T b, Comparator<T> comparator) {
        if (comparator != null) {
            return comparator.compare(a, b);
        } else if (a instanceof Comparable) {
            return ((Comparable<T>) a).compareTo(b);
        } else {
            throw new IllegalArgumentException("Элементы не Comparable и не передан Comparator");
        }
    }

    /**
     * Обменивает местами два элемента в списке.
     *
     * @param list список
     * @param i    индекс первого элемента
     * @param j    индекс второго элемента
     * @param <T>  тип элементов списка
     */
    private static <T> void swap(CustomArrayList<T> list, int i, int j) {
        T temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }
}