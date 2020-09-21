package by.it.group473602.prilepin.lesson07;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

/*
Задача на программирование: расстояние Левенштейна
    https://ru.wikipedia.org/wiki/Расстояние_Левенштейна
    http://planetcalc.ru/1721/

Дано:
    Две данных непустые строки длины не более 100, содержащие строчные буквы латинского алфавита.

Необходимо:
    Решить задачу МЕТОДАМИ ДИНАМИЧЕСКОГО ПРОГРАММИРОВАНИЯ
    Рекурсивно вычислить расстояние редактирования двух данных непустых строк

    Sample Input 1:
    ab
    ab
    Sample Output 1:
    0

    Sample Input 2:
    short
    ports
    Sample Output 2:
    3

    Sample Input 3:
    distance
    editing
    Sample Output 3:
    5

*/

public class A_EditDist {


    int getDistanceEdinting(String one, String two) {
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        int n = one.length()+1;
        int m = two.length()+1;
        int [][] d = new int[n][m];
        for (int i = 0; i < n; i++){
            for (int j = 0; j < m; j++){
                d[i][j]=1000;
            }
        }
        int result = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                result = EditDistTD(i, j, d, one, two);
            }
        }
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
    }

    int EditDistTD(int i, int j, int [][]d, String one, String two){
        if (d[i][j] == 1000){
            if (i==0){
                d[i][j]=j;
            }
            else{
                if (j==0) {
                    d[i][j]=i;
                }
                else{
                    int ins = EditDistTD(i, j-1, d, one, two)+1;
                    int del = EditDistTD(i-1, j, d, one, two)+1;
                    int sub = EditDistTD(i-1, j-1, d, one, two) + getDiff(one.charAt(i-1), two.charAt(j-1));
                    d[i][j] = getMin(ins,del,sub);
                }
            }
        }
        return d[i][j];
    }

    private static int getDiff(char one, char two){
        return one != two ? 1: 0;
    }

    private static int getMin(int one, int two, int three){
        int min = -1;
        if (two > one){
            min = one;
        }else{
            min = two;
        }
        if (three<min){
            min = three;
        }
        return min;
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson08/dataABC.txt");
        A_EditDist instance = new A_EditDist();
        Scanner scanner = new Scanner(stream);
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
    }
}

