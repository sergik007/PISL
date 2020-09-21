package by.it.group473601.borovsky.lesson01.lesson06;

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
        int arrayLength = scanner.nextInt();
        int[] inputArray = new int[arrayLength];
        //читаем всю последовательность
        for (int i = 0; i < arrayLength; i++) {
            inputArray[i] = scanner.nextInt();
        }
        //тут реализуйте логику задачи методами динамического программирования (!!!)
        int result = 0;

        int [] sequenceLength = new int[arrayLength];
        int [] previousIndexesArray = new int[arrayLength];
        for(int i = 0; i < arrayLength; i++){
            sequenceLength[i] = 1;
            previousIndexesArray[i] = -1;
            for(int j = 0; j < i; j++){
                if(inputArray[i] <= inputArray[j] && sequenceLength[j] + 1 > sequenceLength[i]){
                    sequenceLength[i] = sequenceLength[j] + 1;
                    previousIndexesArray[i] = j;
                }
            }
        }

        for(int i = 0; i < arrayLength; i++){
            if(sequenceLength[i] > result){
                result = sequenceLength[i];
            }
        }

        int [] indexesOfLIS = new int[result];
        int k = 1;
        for(int i = 1; i < arrayLength; i++){
            if(sequenceLength[i] > sequenceLength[k]){
                k = i;
            }
        }

        int j = result - 1;
        while(k >= 0){
            indexesOfLIS[j] = k + 1;
            j--;
            k = previousIndexesArray[k];
        }

        for(int i = 0; i < result; i++){
            System.out.print(indexesOfLIS[i] + " ");
        }
        System.out.println();


        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group473601/borovsky/lesson01/lesson06/dataC.txt");
        C_LongNotUpSubSeq instance = new C_LongNotUpSubSeq();
        int result = instance.getNotUpSeqSize(stream);
        System.out.print(result);
    }

}
