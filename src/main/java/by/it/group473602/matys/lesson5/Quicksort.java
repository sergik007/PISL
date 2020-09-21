package by.it.group473602.matys.lesson5;

import java.util.Random;

public class Quicksort <T extends Comparable<T>> {
    public static final Random RND = new Random();
    private void swap(T[] array, int i, int j) {
        T tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }
    private int partition(T[] array, int begin, int end) {
        int index = begin + RND.nextInt(end - begin + 1);
        T pivot = array[index];
        swap(array, index, end);
        for (int i = index = begin; i < end; ++ i) {
            if (array[i].compareTo(pivot) <= 0) {
                swap(array, index++, i);
            }
        }
        swap(array, index, end);
        return (index);
    }
    private void qsort(T[] array, int begin, int end) {
        if (end > begin) {
            int index = partition(array, begin, end);
            qsort(array, begin, index - 1);
            qsort(array, index + 1,  end);
        }
    }
    public void sort(T[] array) {
        qsort(array, 0, array.length - 1);
    }
}