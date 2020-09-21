package by.it.group473602.matys.lesson6;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

/*
Задача на программирование: наибольшая кратная подпоследовательность

Дано:
    целое число 1≤n≤1000
    массив A[1…n] натуральных чисел, не превосходящих 2E9.

Необходимо:
    Выведите максимальное 1<=k<=n, для которого гарантированно найдётся
    подпоследовательность индексов i[1]<i[2]<…<i[k] <= длины k,
    для которой каждый элемент A[i[k]] делится на предыдущий
    т.е. для всех 1<=j<k, A[i[j+1]] делится на A[i[j]].

Решить задачу МЕТОДАМИ ДИНАМИЧЕСКОГО ПРОГРАММИРОВАНИЯ

    Sample Input:
    4
    3 6 7 12

    Sample Output:
    3
*/

public class B_LongDivComSubSeq {

    public int lengthOfLongDivComSubSeq(int[] numbers) {
	if (numbers == null || numbers.length == 0) {
	    return 0;
	}

	int[] maxLength = new int[numbers.length];

	for (int i = 0; i < numbers.length; i++) {
	    maxLength[i] = 1;
	    for (int j = 0; j < i; j++) {
		if (numbers[i] % numbers[j] == 0 && maxLength[j] + 1 > maxLength[i]) {
		    maxLength[i] = maxLength[j] + 1;
		}
	    }
	}

	int result = 0;
	for (int i = 0; i < maxLength.length; i++) {
	    if (maxLength[i] > result) {
		result = maxLength[i];
	    }
	}

	return result;
    }

    int getDivSeqSize(InputStream stream) throws FileNotFoundException {
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
	
	int result = lengthOfLongDivComSubSeq(m);

	return result;
    }

    public static void main(String[] args) throws FileNotFoundException {
	String root = System.getProperty("user.dir") + "/src/";
	InputStream stream = new FileInputStream(root + "by/it/group473602/matys/lesson6/dataB.txt");
	B_LongDivComSubSeq instance = new B_LongDivComSubSeq();
	int result = instance.getDivSeqSize(stream);
	System.out.print(result);
    }

}
