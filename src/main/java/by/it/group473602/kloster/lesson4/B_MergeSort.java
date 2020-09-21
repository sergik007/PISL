package by.it.group473602.kloster.lesson4;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

public class B_MergeSort {

    int[] merge(int[] arg1, int[] arg2) {
        int max = arg1.length + arg2.length;
        int[] result = new int[max];
        int m = 0, n = 0;
        for (int i = 0; i < max; i++) {
            if (m >= arg1.length & n < arg2.length) {
                result[i] = arg2[n];
                n++;
            } else if (n >= arg2.length & m < arg1.length) {
                result[i] = arg1[m];
                m++;
            } else if (arg1[m] <= arg2[n] & m < arg1.length) {
                result[i] = arg1[m];
                m++;
            } else {
                result[i] = arg2[n];
                n++;
            }
        }
        return result;
    }

    int[] mergeSort(int[] array, int left, int right) {
        int[] result = new int[1];
        int middle = (left + right) / 2;
        if (left < right) {
            return merge(mergeSort(array, left, middle), mergeSort(array, middle + 1, right));
        } else {
            result[0] = array[left];
            return result;
        }
    }

    int[] getMergeSort(InputStream stream) throws FileNotFoundException {
        // подготовка к чтению данных
        Scanner scanner = new Scanner(stream);

        // размер массива
        int n = scanner.nextInt();
        // сам массива
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
            System.out.print (a[i]+ " ");

        }
        System.out.println();
        scanner.close();

        a = mergeSort(a, 0, a.length - 1);

        return a;
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group473602/kloster/lesson4/dataB.txt");
        B_MergeSort instance = new B_MergeSort();
        // long startTime = System.currentTimeMillis();
        int[] result = instance.getMergeSort(stream);
        // long finishTime = System.currentTimeMillis();
        for (int index : result) {
            System.out.print(index + " ");
        }
    }

}
