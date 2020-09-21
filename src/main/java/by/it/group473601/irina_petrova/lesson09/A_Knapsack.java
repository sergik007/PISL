package by.it.group473601.irina_petrova.lesson09;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

/*
Задача на программирование: рюкзак с повторами

Первая строка входа содержит целые числа
    1<=W<=100000     вместимость рюкзака
    1<=n<=300        сколько есть вариантов золотых слитков
                     (каждый можно использовать множество раз).
Следующая строка содержит n целых чисел, задающих веса слитков:
  0<=w[1]<=100000 ,..., 0<=w[n]<=100000

Найдите методами динамического программирования
максимальный вес золота, который можно унести в рюкзаке.


Sample Input:
10 3
1 4 8
Sample Output:
10

Sample Input 2:
15 3
2 8 16
Sample Output 2:
14

*/
public class A_Knapsack {

    int getMaxWeight(InputStream stream) {
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        Scanner scanner = new Scanner(stream);
        int sizeOfBox = scanner.nextInt();
        int countOfBar = scanner.nextInt();
        int arrayOfBarSize[] = new int[countOfBar + 1];
        int[] D = new int[sizeOfBox + 1];

        for (int i = 1; i <= countOfBar; i++) {
            arrayOfBarSize[i] = scanner.nextInt();
        }

        for (int k = 1; k <= sizeOfBox; k++) {
            for (int z = 1; z <= countOfBar; z++) {
                if (arrayOfBarSize[z] <= k) {
                    D[k] = Math.max(arrayOfBarSize[z], D[k - arrayOfBarSize[z]] + arrayOfBarSize[z]);
                }
            }
        }

       /* for(int t : D) System.out.print(t + " ");
        System.out.println();*/

        int result = D[sizeOfBox];
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group473601/irina_petrova/lesson09/dataA.txt");
        A_Knapsack instance = new A_Knapsack();
        int res = instance.getMaxWeight(stream);
        System.out.println(res);
    }
}

