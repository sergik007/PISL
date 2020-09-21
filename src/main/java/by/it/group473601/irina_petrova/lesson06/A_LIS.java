package by.it.group473601.irina_petrova.lesson06;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Arrays;
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
    1 3 7 3 2 6

    Sample Output:
    3
*/

public class A_LIS {

    int getSeqSize(InputStream stream) throws FileNotFoundException {
        //подготовка к чтению данных
        Scanner scanner = new Scanner(stream);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        //общая длина последовательности
        int sizeMass = scanner.nextInt();
        int[] massElements = new int[sizeMass];
        //читаем всю последовательность
        for (int i = 0; i < sizeMass; i++) {
            massElements[i] = scanner.nextInt();
        }
        //тут реализуйте логику задачи методами динамического программирования (!!!)
        int result = 0;
        int[] arraySuitableValues = new int[sizeMass];
        for (int countI = 0; countI < sizeMass; countI++) {
            arraySuitableValues[countI] = 1;
            System.out.println(countI);
            for (int countJ = 0; countJ < countI; countJ++) {
                System.out.println(" m[countI] = " + massElements[countI] + " m[countJ] = " + massElements[countJ] );
                if (massElements[countJ] < massElements[countI] && (arraySuitableValues[countJ] + 1) > arraySuitableValues[countI]) {
                    System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                    System.out.println(" m[countI] = " + massElements[countI] + " m[countJ] = " + massElements[countJ] );
                    arraySuitableValues[countI] = arraySuitableValues[countJ] + 1;
                    System.out.println("-------------------------------------------");
                    for (int i = 0; i < arraySuitableValues.length; i++) {
                        System.out.print(arraySuitableValues[i]);
                    }
                    System.out.println();
                }
            }
        }
        Arrays.sort(arraySuitableValues);
        System.out.println();
        System.out.println("-------------------------------------------");
        for (int i = 0; i < arraySuitableValues.length; i++) {
            System.out.println(arraySuitableValues[i]);
        }
        result = arraySuitableValues[sizeMass - 1];
        System.out.println("-------------------------------------------");
        System.out.println(result);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group473601/irina_petrova/lesson06/dataA.txt");
        A_LIS instance = new A_LIS();
        int result = instance.getSeqSize(stream);
        System.out.print(result);
    }
}

