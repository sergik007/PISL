package by.it.group473602.matys.lesson4;

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

    int[] merge(int[] first, int[] second) {
	int max = first.length + second.length;
	int[] result = new int[max];
	int m = 0, n = 0;
	for (int i = 0; i < max; i++) {
	    if (m >= first.length & n < second.length) {
		result[i] = second[n];
		n++;
	    } else if (n >= second.length & m < first.length) {
		result[i] = first[m];
		m++;
	    } else if (first[m] <= second[n] & m < first.length) {
		result[i] = first[m];
		m++;
	    } else {
		result[i] = second[n];
		n++;
	    }
	}
	return result;
    }

    int[] mergeSort(int[] array, int left, int right) {
	int[] result = new int[1];
	int middle = (int) (left + right) / 2;
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
	    System.out.println(a[i]);

	}
	scanner.close();

	a = mergeSort(a, 0, a.length - 1);

	return a;
    }

    public static void main(String[] args) throws FileNotFoundException {
	String root = System.getProperty("user.dir") + "/src/";
	InputStream stream = new FileInputStream(root + "by/it/group473602/matys/lesson4/dataB.txt");
	B_MergeSort instance = new B_MergeSort();
	// long startTime = System.currentTimeMillis();
	int[] result = instance.getMergeSort(stream);
	// long finishTime = System.currentTimeMillis();
	for (int index : result) {
	    System.out.print(index + " ");
	}
    }

}
