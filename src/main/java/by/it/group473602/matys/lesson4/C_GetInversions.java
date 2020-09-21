package by.it.group473602.matys.lesson4;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

/*
Рассчитать число инверсий одномерного массива.
Сложность алгоритма должна быть не хуже, чем O(n log n)

Первая строка содержит число 1<=n<=10000,
вторая - массив A[1…n], содержащий натуральные числа, не превосходящие 10E9.
Необходимо посчитать число пар индексов 1<=i<j<n, для которых A[i]>A[j]A[i]>A[j].

    (Такая пара элементов называется инверсией массива.
    Количество инверсий в массиве является в некотором смысле
    его мерой неупорядоченности: например, в упорядоченном по неубыванию
    массиве инверсий нет вообще, а в массиве, упорядоченном по убыванию,
    инверсию образуют каждые (т.е. любые) два элемента.
    )

Sample Input:
5
2 3 9 2 9
Sample Output:
2

Головоломка (т.е. не обязательно).
Попробуйте обеспечить скорость лучше, чем O(n log n) за счет многопоточности.
Докажите рост производительности замерами времени.
Большой тестовый массив можно прочитать свой или сгенерировать его программно.
*/

public class C_GetInversions {

    int calc(InputStream stream) throws FileNotFoundException {
	// подготовка к чтению данных
	Scanner scanner = new Scanner(stream);

	// размер массива
	int n = scanner.nextInt();
	// сам массив
	int[] a = new int[n];
	for (int i = 0; i < n; i++) {
	    a[i] = scanner.nextInt();
	}
	
	scanner.close();
	
	int result = count(a);

	return result;
    }

    public static int count(int[] a) {
	int[] b = new int[a.length];
	int[] aux = new int[a.length];
	for (int i = 0; i < a.length; i++) {
	    b[i] = a[i];
	}
	int inversions = count(a, b, aux, 0, a.length - 1);
	return inversions;
    }

    private static int count(int[] a, int[] b, int[] aux, int left, int right) {
	int inversions = 0;
	if (right <= left){
	    return 0;
	}
	int middle = left + (right - left) / 2;
	inversions += count(a, b, aux, left, middle);
	inversions += count(a, b, aux, middle + 1, right);
	inversions += merge(b, aux, left, middle, right);
	return inversions;
    }

    // merge and count
    private static int merge(int[] a, int[] aux, int left, int middle, int right) {
	int inversions = 0;

	// copy to aux[]
	for (int k = left; k <= right; k++) {
	    aux[k] = a[k];
	}

	// merge back to a[]
	int i = left;
	int j = middle + 1;
	for (int k = left; k <= right; k++) {
	    if (i > middle)
		a[k] = aux[j++];
	    else if (j > right)
		a[k] = aux[i++];
	    else if (aux[j] < aux[i]) {
		a[k] = aux[j++];
		inversions += (middle - i + 1);
	    } else
		a[k] = aux[i++];
	}
	return inversions;
    }

    public static void main(String[] args) throws FileNotFoundException {
	String root = System.getProperty("user.dir") + "/src/";
	InputStream stream = new FileInputStream(root + "by/it/group473602/matys/lesson4/dataC.txt");
	C_GetInversions instance = new C_GetInversions();
	// long startTime = System.currentTimeMillis();
	int result = instance.calc(stream);
	// long finishTime = System.currentTimeMillis();
	System.out.print(result);
    }
}
