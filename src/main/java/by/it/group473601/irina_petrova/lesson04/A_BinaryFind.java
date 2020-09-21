package by.it.group473601.irina_petrova.lesson04;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

/*
В первой строке источника данных даны:
        - целое число 1<=n<=100000 (размер массива)
        - сам массив A[1...n] из n различных натуральных чисел,
          не превышающих 10E9, в порядке возрастания,
Во второй строке
        - целое число 1<=k<=10000 (сколько чисел нужно найти)
        - k натуральных чисел b1,...,bk не превышающих 10E9 (сами числа)
Для каждого i от 1 до kk необходимо вывести индекс 1<=j<=n,
для которого A[j]=bi, или -1, если такого j нет.

        Sample Input:
        5 1 5 8 12 13
        5 8 1 23 1 11

        Sample Output:
        3 1 -1 1 -1

(!) Обратите внимание на смещение начала индекса массивов JAVA относительно условий задачи
*/

public class A_BinaryFind {
    int[] findIndex(InputStream stream) throws FileNotFoundException {
        //подготовка к чтению данных
        Scanner scanner = new Scanner(stream);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!

        //размер отсортированного массива
        int callElementsInMass1 = scanner.nextInt();
        //сам отсортированный массива
        int[] mass1 = new int[callElementsInMass1];
        for (int i = 1; i <= callElementsInMass1; i++) {
            mass1[i - 1] = scanner.nextInt();
        }

        //размер массива индексов
        int callElementsInMass2 = scanner.nextInt();
        int[] resultMass2 = new int[callElementsInMass2];
        for (int i = 0; i < callElementsInMass2; i++) {
            int valueInSecondLine = scanner.nextInt();
            //тут реализуйте бинарный поиск индекса
            int index = -1;
            int lowIndex = 0;
            int highIndex = callElementsInMass1 - 1;
            int midleIndex;
            while (true) {
                midleIndex = (lowIndex + highIndex) / 2;
                if (mass1[midleIndex] == valueInSecondLine) {
                    index = midleIndex;
                    break;
                } else {
                    if (lowIndex >= highIndex) {
                        break;
                    }
                }
                if (valueInSecondLine < mass1[midleIndex]) {
                    highIndex = midleIndex;
                } else {
                    lowIndex = midleIndex + 1;
                }
            }
            if (index >= 0) {
                index++;
            }
            resultMass2[i] = index;
        }
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return resultMass2;
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson04/dataA.txt");
        A_BinaryFind instance = new A_BinaryFind();
        //long startTime = System.currentTimeMillis();
        int[] result = instance.findIndex(stream);
        //long finishTime = System.currentTimeMillis();
        for (int index : result) {
            System.out.print(index + " ");
        }
    }

}
