package main;


import java.util.Arrays;

/**
 * Автор Давыденка Даниил
 * */
public class MyArrayList<T extends Comparable<T>> {

    /**
     * Поля
     * T[] - массив объектов класса T
     * size - количество присутствующих объектов в массиве
     * DEFAULT_CAPACITY - размер массива по умолчанию при инициализации пустого конструктора
     * */
    private T[] list;
    private int size = 0;
    private final int DEFAULT_CAPACITY = 10;

    /**
     * конструктор с задаваемым размером
     * */
    public MyArrayList(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("capacity can't be: " + capacity);
        }
        list = (T[]) new Comparable[capacity];
    }

    /**
     *  конструктор по умолчанию, размер 10
     */
    public MyArrayList() {
        list = (T[]) new Comparable[DEFAULT_CAPACITY];
    }

    /**
     * добавление объекта в массив
     */
    public void add(T item) {
        grow();
        list[size] = item;
        size++;
    }

    /**
     * добавление объекта в массив по конкретному индексу,
     * происходит смещение всех объектов массива вверх по индексу на 1, от вставляемой позиции
     */
    public void add(int index, T item) {
        checkCorrectIndex(index);
        grow();
        for (int i = size; i > index; i--) {
            list[i] = list[i - 1];
        }
        list[index] = item;
        size++;
    }

    /**
     * удаление объекта по значению
     */
    public boolean remove(T item) {
        int i = 0;
        while (i < size && !list[i].equals(item)) {
            i++;
        }
        if (i == size) {
            return false;
        }
        for (int j = i; j < size - 1; j++) {
            list[j] = list[j + 1];
        }
        size--;
        list[size] = null;
        return true;
    }

    /**
     * получить объект по индексу
     */
        public T get(int index) {
        checkCorrectIndex(index);
        return list[index];
    }

    /**
     * заменить объект по индексу
     */
    public void set(int index, T item) {
        checkCorrectIndex(index);
        list[index] = item;
    }

    /**
     * получить количество присутствующих объектов в массиве
     */
    public int size() {
        return size;
    }

    /**
     * проверка корректности вводимого индекса
     */
    private void checkCorrectIndex(int index) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Index can't be: " + index);
        }
    }

    /**
     * получить индекс по значению объекта ( возвращает первый индекс элемента с подходящим значением)
     */
    public final int indexOf(T item) {
        for (int i = 0; i < size; i++) {
            if (list[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * проверка наличия объекта по значению
     */
    public boolean contains(T item) {
        return indexOf(item) > -1;
    }

    /**
     * метод сравнения объектов
     */
    private boolean less(T item1, T item2) {
        return item1.compareTo(item2) < 0;
    }

    /**
     * метод перемены мест двух объектов
     */
    private void swap(int index1, int index2) {
        T temp = list[index1];
        list[index1] = list[index2];
        list[index2] = temp;
    }

    /**
     * внутренний метод быстрой сортировки используемый для рекурсии в методе быстрой сортировки
     */
    private void quickSort(int lo, int hi) {
        if (lo >= hi) {
            return;
        }
        int mid = lo + (hi - lo) / 2;
        T pillar = list[mid];

        int i = lo;
        int j = hi;
        while (i <= j) {
            while (less(list[i], pillar)) {
                i++;
            }
            while (less(pillar, list[j])) {
                j--;
            }
            if (i <= j) {
                swap(i, j);
                i++;
                j--;
            }
        }
        if (lo < j) {
            quickSort(lo, j);
        }
        if (hi > i) {
            quickSort(i, hi);
        }
    }

    /**
     * метод быстрой сортировки
     */
    public void quickSort() {
        quickSort(0, size - 1);
    }

    /**
     * обрезает массив данных до количества элементов
     */
    public void trimToSize() {
        if (size < list.length) {
            if (size == 0) {list =(T[]) new Comparable[DEFAULT_CAPACITY];}
            else Arrays.copyOf(list, size);
        }
    }

    /**
     * внутренний метод автоматического увеличения массива при достижении загрузки в 0,7 от допустимого объёма
     */
    private void grow() {
        if ((double) size/list.length >= 0.7) {
            list = Arrays.copyOf(list, list.length*2);
        }
    }

    /**
     * переопределение метода вывода данных в консоль
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[ ");
        for (int i = 0; i < size; i++) {
            sb.append(list[i]).append(", ");
        }
        sb.setLength(sb.length() - 2);
        sb.append(" ]");
        return sb.toString();
    }
}