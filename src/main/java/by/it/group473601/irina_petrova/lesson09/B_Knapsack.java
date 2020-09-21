package by.it.group473601.irina_petrova.lesson09;

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
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        Scanner scanner = new Scanner(stream);
        int sizeOfBox = scanner.nextInt();
        int countOfBar = scanner.nextInt();
        int arrayOfBarSize[] = new int[countOfBar + 1];
        for (int i = 1; i <= countOfBar; i++) {
            arrayOfBarSize[i] = scanner.nextInt();
        }
        int[][] D = new int[countOfBar + 1][sizeOfBox + 1];

        for (int k = 1; k <= countOfBar; k++) {
            for (int z = 1; z <= sizeOfBox; z++) {
                D[k][z] = D[k - 1][z];
                if (z >= arrayOfBarSize[k]) {
                    D[k][z] = Math.max(D[k][z], D[k - 1][z - arrayOfBarSize[k]] + arrayOfBarSize[k]);
                }
            }
        }
        /*for (int i = 0; i <= sizeOfBox; i++) System.out.print(i + " ");
        System.out.println();
        for (int[] t : D) {
            for (int f : t) System.out.print(f + " ");
            System.out.println();
        }*/

        int result = D[countOfBar][sizeOfBox];
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group473601/irina_petrova/lesson09/dataB.txt");
        B_Knapsack instance = new B_Knapsack();
        int res = instance.getMaxWeight(stream);
        System.out.println(res);
    }

}

