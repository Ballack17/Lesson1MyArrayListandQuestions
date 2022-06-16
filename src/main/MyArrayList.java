package main;


import java.util.Arrays;

public class MyArrayList<T extends Comparable<T>> {
    private T[] list;
    private int size = 0;
    private final int DEFAULT_CAPACITY = 10;

    public MyArrayList(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("capacity can't be: " + capacity);
        }
        list = (T[]) new Comparable[capacity];
    }

    public MyArrayList() {
        list = (T[]) new Comparable[DEFAULT_CAPACITY];
    }

    public void add(T item) {
        grow();
        list[size] = item;
        size++;
    }

    public void add(int index, T item) {
        checkCorrectIndex(index);
        grow();
        for (int i = size; i > index; i--) {
            list[i] = list[i - 1];
        }
        list[index] = item;
        size++;
    }

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

    public T get(int index) {
        checkCorrectIndex(index);
        return list[index];
    }

    public void set(int index, T item) {
        checkCorrectIndex(index);
        list[index] = item;
    }

    public int size() {
        return size;
    }

    private void checkCorrectIndex(int index) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Index can't be: " + index);
        }
    }

    public final int indexOf(T item) {
        for (int i = 0; i < size; i++) {
            if (list[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    public boolean contains(T item) {
        return indexOf(item) > -1;
    }

    private boolean less(T item1, T item2) {
        return item1.compareTo(item2) < 0;
    }

    private void swap(int index1, int index2) {
        T temp = list[index1];
        list[index1] = list[index2];
        list[index2] = temp;
    }

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

    public void quickSort() {
        quickSort(0, size - 1);
    }

    public void trimToSize() {
        if (size < list.length) {
            if (size == 0) {list =(T[]) new Comparable[DEFAULT_CAPACITY];}
            else Arrays.copyOf(list, size);
        }
    }

    private void grow() {
        if ((double) size/list.length >= 0.7) {
            list = Arrays.copyOf(list, list.length*2);
        }
    }

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