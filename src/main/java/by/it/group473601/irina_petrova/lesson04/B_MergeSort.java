package by.it.group473601.irina_petrova.lesson04;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

/*
Реализуйте сортировку слиянием для одномерного массива.
Сложность алгоритма должна быть не хуже, чем O(n log n)

Первая строка содержит число 1<=n<=10000,
вторая - массив A[1…n], содержащий натуральные числа, не превосходящие 10E9.
Необходимо отсортировать полученный массив.

Sample Input:
5
2 3 9 2 9
Sample Output:
2 2 3 9 9
*/
public class B_MergeSort {

    private int[] merge(int[] arrayLeft, int[] arrayRight) {
        int i = 0;
        int j = 0;
        int[] mergerArray = new int[arrayLeft.length + arrayRight.length];
        for (int k = 0; k < mergerArray.length; k++) {
            if (j == arrayRight.length || (i < arrayLeft.length) && arrayLeft[i] <= arrayRight[j]) {
                mergerArray[k] = arrayLeft[i++];
            } else {
                mergerArray[k] = arrayRight[j++];
            }
        }
        return mergerArray;
    }

    private int[] mergeSort(int[] muss) {
        if (muss.length < 2) return muss;
        int[] leftArray = new int[muss.length / 2];
        int[] rightArray = new int[muss.length - leftArray.length];
        System.arraycopy(muss, 0, leftArray, 0, leftArray.length);
        System.arraycopy(muss, leftArray.length, rightArray, 0, rightArray.length);
        leftArray = mergeSort(leftArray);
        rightArray = mergeSort(rightArray);
        return merge(leftArray, rightArray);
    }

    int[] getMergeSort(InputStream stream) throws FileNotFoundException {
        //подготовка к чтению данных
        Scanner scanner = new Scanner(stream);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!

        //размер массива
        int callElementsInMass = scanner.nextInt();
        //сам массива
        int[] massElements = new int[callElementsInMass];
        System.out.println("Исходный массив: ");
        for (int i = 0; i < callElementsInMass; i++) {
            massElements[i] = scanner.nextInt();
            System.out.print(massElements[i] + " ");
        }
        System.out.println();
        // тут ваше решение (реализуйте сортировку слиянием)
        // https://ru.wikipedia.org/wiki/Сортировка_слиянием
        // a = mergeSort(a, 0, a.length - 1);
        massElements = mergeSort(massElements);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return massElements;
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson04/dataB.txt");
        B_MergeSort instance = new B_MergeSort();
        //long startTime = System.currentTimeMillis();
        int[] result = instance.getMergeSort(stream);
        //long finishTime = System.currentTimeMillis();
        System.out.println("Отсортированный массив: ");
        for (int index : result) {
            System.out.print(index + " ");
        }
    }
}
