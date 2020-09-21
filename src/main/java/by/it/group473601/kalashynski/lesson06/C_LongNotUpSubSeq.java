package by.it.group473601.kalashynski.lesson06;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

/*
Задача на программирование: наибольшая невозростающая подпоследовательность

Дано:
    целое число 1<=n<=1E5 ( ОБРАТИТЕ ВНИМАНИЕ НА РАЗМЕРНОСТЬ! )
    массив A[1…n] натуральных чисел, не превосходящих 2E9.

Необходимо:
    Выведите максимальное 1<=k<=n, для которого гарантированно найдётся
    подпоследовательность индексов i[1]<i[2]<…<i[k] <= длины k,
    для которой каждый элемент A[i[k]] не больше любого предыдущего
    т.е. для всех 1<=j<k, A[i[j]]>=A[i[j+1]].

    В первой строке выведите её длину k,
    во второй - её индексы i[1]<i[2]<…<i[k]
    соблюдая A[i[1]]>=A[i[2]]>= ... >=A[i[n]].

    (индекс начинается с 1)

Решить задачу МЕТОДАМИ ДИНАМИЧЕСКОГО ПРОГРАММИРОВАНИЯ

    Sample Input:
    5
    5 3 4 4 2

    Sample Output:
    4
    1 3 4 5
*/


public class C_LongNotUpSubSeq {

    int getNotUpSeqSize(InputStream stream) throws FileNotFoundException {
        //подготовка к чтению данных
        Scanner scanner = new Scanner(stream);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        //общая длина последовательности
        int n = scanner.nextInt();
        int[] fileArray = new int[n];
        //читаем всю последовательность
        for (int i = 0; i < n; i++) {
            fileArray[i] = scanner.nextInt();
        }
        //тут реализуйте логику задачи методами динамического программирования (!!!)
        int result = 0;
        int[] previousIndexOfElement = new int[n];
        int[] countMas = new int[n];

        for (int i = 0; i < n; i++) {
            countMas[i] = 1;
            previousIndexOfElement[i] = -1;
            for (int j = 0; j < i; j++) {
                if (fileArray[j] >= fileArray[i] && countMas[j] + 1 > countMas[i]) {
                    countMas[i] = countMas[j] + 1;
                    previousIndexOfElement[i] = j;
                }
            }
            if (result < countMas[i]) {
                result = countMas[i];
            }
        }
        int[] resultMas = new int[result];
        int k = 1;
        for (int i = 1; i < n; i++) {//Находим первый максимальнвый эл-т - указатель
            // на последний эл-т невозрастающей последовательности
            if (countMas[i] > countMas[k]) {
                k = i;
            }
        }

        //заносим в результатирующий массив данные
        for (int j = result - 1; k >= 0; j--) {
            resultMas[j] = k + 1;
            k = previousIndexOfElement[k];
        }
        //вывод
        for (int i = 0; i < result; i++) {
            System.out.print(resultMas[i] + " ");
        }
        System.out.println();
        return result;
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group473601/kalashynski/lesson06/dataC.txt");
        C_LongNotUpSubSeq instance = new C_LongNotUpSubSeq();
        int result = instance.getNotUpSeqSize(stream);
        System.out.print(result);
    }

}
