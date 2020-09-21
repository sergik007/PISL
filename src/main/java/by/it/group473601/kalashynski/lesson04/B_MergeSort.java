package by.it.group473601.kalashynski.lesson04;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

/*
Реализуйте сортировку слиянием для одномерного массива.
Сложность алгоритма должна быть не хуже, чем O(n log n)

Первая строка содержит число 1<=n<=10000,
вторая - массив A[1…n], содержащий натуральные числа, не превосходящие 10E9.
Необходимо отсортировать полученный массив.

Sample Input:
5
2 3 9 2 9
Sample Output:
2 2 3 9 9
*/
public class B_MergeSort {

    int[] getMergeSort(InputStream stream) throws FileNotFoundException {
        Scanner scanner = new Scanner(stream);
        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        a = merge_sort(a, a.length);
        return a;
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group473601/kalashynski/lesson04/dataB.txt");
        B_MergeSort instance = new B_MergeSort();
        //long startTime = System.currentTimeMillis();
        int[] result = instance.getMergeSort(stream);
        //long finishTime = System.currentTimeMillis();
        for (int index : result) {
            System.out.print(index + " ");
        }
    }

    int [] merge_sort(int a[], int n) {
        int temp[], b[] = new int[n];
        int c, k = 1, d, e;
        while (k <= 2 * n) {
            for (c = 0; c < n; c += k * 2) {
                d = c + k < n ? c + k : n;
                e = c + 2 * k < n ? c + 2 * k : n;
                merge(a, b, c, d, e);
            }
            temp = a;
            a = b;
            b = temp;
            k *= 2;
        }
        return a;
    }

    void merge(int a[], int b[], int c, int d, int e) {
        int p1 = c, p2 = d, pres = c;
        while (p1 < d && p2 < e)
            if (a[p1] < a[p2])
                b[pres++] = a[p1++];
            else
                b[pres++] = a[p2++];
        while (p1 < d)
            b[pres++] = a[p1++];
        while (p2 < e)
            b[pres++] = a[p2++];
    }
}
