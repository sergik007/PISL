package by.it.group473601.irina_petrova.lesson07;

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

    private static int[][] MatrixD;
    private char[] first;
    private char[] second;

    int getDistanceEdinting(String firstString, String secondString) {
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        first = firstString.toCharArray();
        second = secondString.toCharArray();

        int firstLenght = first.length + 1;
        int secondLenght = second.length + 1;
        MatrixD = new int[firstLenght][secondLenght];

        for (int i = 0; i < firstLenght; i++) {
            for (int j = 0; j < secondLenght; j++) {
                MatrixD[i][j] = Integer.MAX_VALUE;
            }
        }

        int result = editDistTD(firstLenght - 1, secondLenght - 1);

        return result;
    }

    private int editDistTD(int i, int j) {
        if (MatrixD[i][j] == Integer.MAX_VALUE) {
            if (i == 0) MatrixD[i][j] = j;
            else if (j == 0) MatrixD[i][j] = i;
            else {
                int ins = editDistTD(i, j - 1) + 1;
                int del = editDistTD(i - 1, j) + 1;
                int rep;
                if (first[i - 1] == second[j - 1]) {
                    rep = editDistTD(i - 1, j - 1);    //совпадение
                } else {
                    rep = editDistTD(i - 1, j - 1) + 1;  //замена
                }
                MatrixD[i][j] = findMin(ins, del, rep);
            }
        }
        return MatrixD[i][j];
    }

    int findMin(int insert, int substitution, int delete) {
        int minValue = insert;
        if (minValue > substitution) {
            minValue = substitution;
        }
        if (minValue > delete) {
            minValue = delete;
        }
        return minValue;
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group473601/irina_petrova/lesson07/dataABC.txt");
        A_EditDist instance = new A_EditDist();
        Scanner scanner = new Scanner(stream);
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(), scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(), scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(), scanner.nextLine()));
    }
}

