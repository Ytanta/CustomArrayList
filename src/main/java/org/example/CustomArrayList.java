package org.example;

/**
 * Реализация динамического массива (аналог ArrayList) без потокобезопасности.
 * Поддерживает основные операции: добавление, удаление, получение элементов, сортировку и очистку.
 *
 * @param <T> тип элементов, хранящихся в списке
 */
public class CustomArrayList<T> {

    /**
     * Внутренний массив для хранения элементов.
     */
    private Object[] elements;

    /**
     * Текущее количество элементов в списке.
     */
    private int size;

    /**
     * Создает пустой список.
     */
    public CustomArrayList() {
        elements = new Object[0];
        size = 0;
    }

    /**
     * Добавляет элемент в конец списка.
     *
     * @param element элемент, который нужно добавить
     */
    public void add(T element) {
        resizeIfNeeded();
        elements[size++] = element;
    }

    /**
     * Добавляет элемент в указанную позицию, сдвигая последующие элементы.
     *
     * @param index позиция, куда вставить элемент
     * @param element элемент, который нужно добавить
     * @throws IndexOutOfBoundsException если индекс выходит за пределы допустимого диапазона
     */
    public void add(int index, T element) {
        checkIndex(index, true);
        resizeIfNeeded();
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = element;
        size++;
    }

    /**
     * Получает элемент по индексу.
     *
     * @param index индекс элемента
     * @return элемент по указанному индексу
     * @throws IndexOutOfBoundsException если индекс выходит за пределы допустимого диапазона
     */
    @SuppressWarnings("unchecked")
    public T get(int index) {
        checkIndex(index, false);
        return (T) elements[index];
    }

    /**
     * Удаляет элемент по индексу, сдвигая последующие элементы влево.
     *
     * @param index индекс элемента для удаления
     * @throws IndexOutOfBoundsException если индекс выходит за пределы допустимого диапазона
     */
    public void remove(int index) {
        checkIndex(index, false);
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        elements[--size] = null;
    }

    /**
     * Очищает список, удаляя все элементы.
     */
    public void clear() {
        elements = new Object[0];
        size = 0;
    }

    /**
     * Заменяет элемент по указанному индексу новым значением.
     *
     * @param index индекс заменяемого элемента
     * @param element новый элемент
     * @throws IndexOutOfBoundsException если индекс выходит за пределы допустимого диапазона
     */
    public void set(int index, T element) {
        checkIndex(index, false);
        elements[index] = element;
    }

    /**
     * Сортирует список с использованием переданного компаратора.
     *
     * @param comparator компаратор для сортировки элементов
     */
    @SuppressWarnings("unchecked")
    public void sort(java.util.Comparator<? super T> comparator) {
        java.util.Arrays.sort((T[]) elements, 0, size, comparator);
    }

    /**
     * Возвращает текущий размер списка.
     *
     * @return количество элементов в списке
     */
    public int size() {
        return size;
    }

    /**
     * Проверяет, необходимо ли расширить массив, и выполняет расширение при необходимости.
     */
    private void resizeIfNeeded() {
        if (size == elements.length) {
            int newCapacity = (size == 0) ? 1 : size * 2;
            Object[] newArray = new Object[newCapacity];
            System.arraycopy(elements, 0, newArray, 0, size);
            elements = newArray;
        }
    }

    /**
     * Проверяет, находится ли индекс в пределах массива.
     * Используется как для обычных операций (get, remove, set), так и для добавления элемента.
     *
     * @param index индекс для проверки
     * @param forAdd если true, проверка для добавления элемента (допускает index == size)
     * @throws IndexOutOfBoundsException если индекс выходит за пределы допустимого диапазона
     */
    private void checkIndex(int index, boolean forAdd) {
        if (index < 0 || index > size || (!forAdd && index == size)) {
            throwIndexOutOfBounds(index);
        }
    }

    /**
     * Выбрасывает исключение IndexOutOfBoundsException с указанием неверного индекса.
     *
     * @param index недопустимый индекс
     */
    private void throwIndexOutOfBounds(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Индекс " + index + " вне диапазона. Размер: " + size);
        }
    }
}