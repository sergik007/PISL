package by.it.group473602.matys.lesson9;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

/*
Задача на программирование: рюкзак без повторов

Первая строка входа содержит целые числа
    1<=W<=100000     вместимость рюкзака
    1<=n<=300        число золотых слитков
                    (каждый можно использовать только один раз).
Следующая строка содержит n целых чисел, задающих веса каждого из слитков:
  0<=w[1]<=100000 ,..., 0<=w[n]<=100000

Найдите методами динамического программирования
максимальный вес золота, который можно унести в рюкзаке.

Sample Input:
10 3
1 4 8
Sample Output:
9

*/

public class B_Knapsack {

    int getMaxWeight(InputStream stream) {
	
	Scanner scanner = new Scanner(stream);
	int weight = scanner.nextInt();
	int number = scanner.nextInt();
	int gold[] = new int[number];
	for (int i = 0; i < number; i++) {
	    gold[i] = scanner.nextInt();
	}
	scanner.close();
	
	return solveKnapsackWithoutRepetition(weight, number, gold);
    }

    public static int solveKnapsackWithoutRepetition(int weight, int number, int[] values) {
	int[][] maxWeight = new int[weight + 1][number + 1];
	for (int i = 0; i < weight + 1; i++) {
	    maxWeight[i][0] = 0;
	}
	for (int i = 0; i < number + 1; i++) {
	    maxWeight[0][i] = 0;
	}

	for (int i = 1; i < number + 1; i++) {
	    for (int w = 1; w < weight + 1; w++) {
		maxWeight[w][i] = maxWeight[w][i - 1];
		if (values[i - 1] <= w) {
		    maxWeight[w][i] = Math.max(maxWeight[w][i], maxWeight[w - values[i - 1]][i - 1] + values[i - 1]);
		}
	    }
	}

	return maxWeight[weight][number];
    }

    public static void main(String[] args) throws FileNotFoundException {
	String root = System.getProperty("user.dir") + "/src/";
	InputStream stream = new FileInputStream(root + "by/it/group473602/matys/lesson9/dataB.txt");
	B_Knapsack instance = new B_Knapsack();
	int res = instance.getMaxWeight(stream);
	System.out.println(res);
    }

}
