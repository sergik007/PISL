package by.it.group473601.irina_petrova.lesson04;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

/*
Рассчитать число инверсий одномерного массива.
Сложность алгоритма должна быть не хуже, чем O(n log n)

Первая строка содержит число 1<=n<=10000,
вторая - массив A[1…n], содержащий натуральные числа, не превосходящие 10E9.
Необходимо посчитать число пар индексов 1<=i<j<n, для которых A[i]>A[j]A[i]>A[j].

    (Такая пара элементов называется инверсией массива.
    Количество инверсий в массиве является в некотором смысле
    его мерой неупорядоченности: например, в упорядоченном по неубыванию
    массиве инверсий нет вообще, а в массиве, упорядоченном по убыванию,
    инверсию образуют каждые (т.е. любые) два элемента.
    )

Sample Input:
5
2 3 9 2 9
Sample Output:
2

Головоломка (т.е. не обязательно).
Попробуйте обеспечить скорость лучше, чем O(n log n) за счет многопоточности.
Докажите рост производительности замерами времени.
Большой тестовый массив можно прочитать свой или сгенерировать его программно.
*/


public class C_GetInversions {
    public int callInvertion = 0;

    int calc(InputStream stream) throws FileNotFoundException {
        //подготовка к чтению данных
        Scanner scanner = new Scanner(stream);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!
        //размер массива
        int callElementsInMass = scanner.nextInt();
        //сам массив
        int[] massElements = new int[callElementsInMass];
        for (int i = 0; i < callElementsInMass; i++) {
            massElements[i] = scanner.nextInt();
        }
        //!!!!!!!!!!!!!!!!!!!!!!!!     тут ваше решение   !!!!!!!!!!!!!!!!!!!!!!!!
        mergeSort(massElements);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return callInvertion;
    }

    private int[] merge(int[] arrayLeft, int[] arrayRight) {
        int i = 0;
        int j = 0;
        int[] mergerArray = new int[arrayLeft.length + arrayRight.length];
        for (int k = 0; k < mergerArray.length; k++) {
            if (j == arrayRight.length || (i < arrayLeft.length) && arrayLeft[i] <= arrayRight[j]) {
                mergerArray[k] = arrayLeft[i++];
            } else {
                mergerArray[k] = arrayRight[j++];
                callInvertion = callInvertion + (arrayLeft.length - i);
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

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group473601/irina_petrova/lesson04/dataC.txt");
        C_GetInversions instance = new C_GetInversions();
        //long startTime = System.currentTimeMillis();
        int result = instance.calc(stream);
        //long finishTime = System.currentTimeMillis();
        System.out.print("Количество инверсий: ");
        System.out.print(result);
    }
}
