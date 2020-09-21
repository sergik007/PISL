package by.it.group473602.matys.lesson6;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

/*
Задача на программирование: наибольшая возростающая подпоследовательность
см.     https://ru.wikipedia.org/wiki/Задача_поиска_наибольшей_увеличивающейся_подпоследовательности
        https://en.wikipedia.org/wiki/Longest_increasing_subsequence

Дано:
    целое число 1≤n≤1000
    массив A[1…n] натуральных чисел, не превосходящих 2E9.

Необходимо:
    Выведите максимальное 1<=k<=n, для которого гарантированно найдётся
    подпоследовательность индексов i[1]<i[2]<…<i[k] <= длины k,
    для которой каждый элемент A[i[k]]больше любого предыдущего
    т.е. для всех 1<=j<k, A[i[j]]<A[i[j+1]].

Решить задачу МЕТОДАМИ ДИНАМИЧЕСКОГО ПРОГРАММИРОВАНИЯ

    Sample Input:
    5
    1 3 3 2 6

    Sample Output:
    3
*/

public class A_LIS {

    public int lengthOfLIS(int[] numbers) {
	if (numbers == null || numbers.length == 0) {
	    return 0;
	}

	int[] max = new int[numbers.length];

	for (int i = 0; i < numbers.length; i++) {
	    max[i] = 1;
	    for (int j = 0; j < i; j++) {
		if (numbers[i] > numbers[j]) {
		    max[i] = Math.max(max[i], max[j] + 1);
		}
	    }
	}

	int result = 0;
	for (int i = 0; i < max.length; i++) {
	    if (max[i] > result) {
		result = max[i];
	    }
	}
	return result;
    }

    int getSeqSize(InputStream stream) throws FileNotFoundException {
	// подготовка к чтению данных
	Scanner scanner = new Scanner(stream);
	// !!!!!!!!!!!!!!!!!!!!!!!!! НАЧАЛО ЗАДАЧИ !!!!!!!!!!!!!!!!!!!!!!!!!
	// общая длина последовательности
	int n = scanner.nextInt();
	int[] m = new int[n];
	// читаем всю последовательность
	for (int i = 0; i < n; i++) {
	    m[i] = scanner.nextInt();
	}
	scanner.close();
	
	int result = lengthOfLIS(m);

	return result;
    }

    public static void main(String[] args) throws FileNotFoundException {
	String root = System.getProperty("user.dir") + "/src/";
	InputStream stream = new FileInputStream(root + "by/it/group473602/matys/lesson6/dataA.txt");
	A_LIS instance = new A_LIS();
	int result = instance.getSeqSize(stream);
	System.out.print(result);
    }
}
