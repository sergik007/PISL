package by.it.group473601.vabishchevich.lesson09;

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
        int W=scanner.nextInt();
        int n=scanner.nextInt();
        int gold[]=new int[n];
        for (int i = 0; i < n; i++) {
            gold[i]=scanner.nextInt();
        }
        int Array[][]= new int[W+1][n+1];
        for(int i=0;i<W+1;i++)
        {
            Array[i][0]=0;
        }
        for(int j=0;j<n+1;j++)
        {
            Array[0][j]=0;
        }
        for(int i=1;i<n+1;i++){
            for(int w=1;w<W+1;w++){
                Array[w][i]=Array[w][i-1];
                        if(gold[i-1]<=w)
                        {
                            Array[w][i]=Math.max(Array[w][i], Array[w-gold[i-1]][i-1]+gold[i-1]);
                        }
            }
        }
        int result = Array[W][n];
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream("C:\\Users\\Колобок\\IdeaProjects\\PISL2017-01-26\\src\\by\\it\\group473601\\vabishchevich\\lesson09\\dataB.txt");
        B_Knapsack instance = new B_Knapsack();
        int res=instance.getMaxWeight(stream);
        System.out.println(res);
    }

}

