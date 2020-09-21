package by.it.group473601.Zarudny.lesson05;

import java.util.Random;

/**
 * Created by zaruda on 08.04.2017.
 */
@SuppressWarnings("rawtypes")
public class Quicksort3 {

    public static final Random RND = new Random();

    private Quicksort3() {
    }

    private static void swap(Comparable[] array, int i, int j) {
        Comparable tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    @SuppressWarnings("unchecked")
    private static void qsort(Comparable[] a, int lo, int hi) {
        if (hi <= lo)
            return;
        int lt = lo, gt = hi;
        Comparable v = a[lo];
        int i = lo;
        while (i <= gt) {
            int cmp = a[i].compareTo(v);
            if (cmp < 0) {
                swap(a, lt++, i++);
            } else if (cmp > 0) {
                swap(a, i, gt--);
            } else {
                i++;
            }
        }

        // Elumination of tail recursion
        qsort(a, lo, lt - 1);
        lo = lt - 1;
    }

    public static void sort(Comparable[] a) {
        qsort(a, 0, a.length - 1);
    }

}
