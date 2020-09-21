package by.it.group473601.borovsky.lesson01.lesson09;

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

    int getMaxWeight(InputStream stream ) {
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        Scanner scanner = new Scanner(stream);
        int capacity=scanner.nextInt();
        int n=scanner.nextInt();
        int gold[]=new int[n];
        for (int i = 0; i < n; i++) {
            gold[i]=scanner.nextInt();
        }


        int result = 0;

        int [][] destination = new int[capacity + 1][n + 1];
        for(int i = 0; i < capacity + 1; i++){
            destination[i] = new int[n + 1];
        }

        for(int i = 0; i < capacity; i++){
            destination[i][0] = 0;
        }
        for(int j = 0; j < n; j++){
            destination[0][j] = 0;
        }

        for(int i = 1; i < n + 1; i++){
            for(int j = 1; j < capacity + 1; j++){
                destination[j][i] = destination[j][i - 1];
                if(gold[i - 1] <= j){
                    int w = gold[i - 1];
                    int c = gold[i - 1];
                    int sum = destination[j - w][i - 1] + c;
                    destination[j][i] = getMax(destination[j][i],sum);
                }
            }
        }
        result = destination[capacity][n];
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
    }

    private static int getMax(int first, int second){
        return first >= second ? first : second;
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group473601/borovsky/lesson01/lesson09/dataB.txt");
        B_Knapsack instance = new B_Knapsack();
        int res=instance.getMaxWeight(stream);
        System.out.println(res);
    }

}

