package by.it.group473601.irina_petrova.lesson06;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Arrays;
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
        int sizeMass = scanner.nextInt();
        int[] massElements = new int[sizeMass];
        //читаем всю последовательность
        for (int i = 0; i < sizeMass; i++) {
            massElements[i] = scanner.nextInt();
        }
        //тут реализуйте логику задачи методами динамического программирования (!!!)
        int result = 0;
        int[] arraySuitableValues = new int[sizeMass];
        int[] massIndexes = new int[sizeMass];
        for (int countI = 0; countI < sizeMass; countI++) {
            arraySuitableValues[countI] = 1;
            massIndexes[countI] = -1;
            for (int countJ = 0; countJ < countI; countJ++) {
                if (massElements[countI] <= massElements[countJ] && (arraySuitableValues[countJ] + 1) > arraySuitableValues[countI]) {
                    arraySuitableValues[countI] = arraySuitableValues[countJ] + 1;
                    massIndexes[countI] = countJ;
                }
            }
        }
        Arrays.sort(arraySuitableValues);
        result = arraySuitableValues[sizeMass - 1];
        int[] arrayIndexes = new int[result];
        int k = 0;
        for (int i = 1; i < sizeMass; i++)
            if (arraySuitableValues[i] > arraySuitableValues[k]) {
                k = i;
            }
        int j = k - 1;
        while (k >= 0) {
            arrayIndexes[j] = k + 1;
            j--;
            k = massIndexes[k];
        }
        System.out.println(Arrays.toString(arrayIndexes));
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group473601/irina_petrova/lesson06/dataC.txt");
        C_LongNotUpSubSeq instance = new C_LongNotUpSubSeq();
        int result = instance.getNotUpSeqSize(stream);
        System.out.print(result);
    }
}
