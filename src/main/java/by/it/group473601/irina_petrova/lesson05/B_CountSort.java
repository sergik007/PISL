package by.it.group473601.irina_petrova.lesson05;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

/*
Первая строка содержит число 1<=n<=10000, вторая - n натуральных чисел, не превышающих 10.
Выведите упорядоченную по неубыванию последовательность этих чисел.

При сортировке реализуйте метод со сложностью O(n)

Пример: https://karussell.wordpress.com/2010/03/01/fast-integer-sorting-algorithm-on/
Вольный перевод: http://programador.ru/sorting-positive-int-linear-time/
*/

public class B_CountSort {


    int[] countSort(InputStream stream) throws FileNotFoundException {
        //подготовка к чтению данных
        Scanner scanner = new Scanner(stream);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        //размер массива
        int sizeOfMass = scanner.nextInt();
        int[] pointMass = new int[sizeOfMass];
        //читаем точки
        int minValue = Integer.MAX_VALUE;
        int maxValue = Integer.MIN_VALUE;
        for (int i = 0; i < sizeOfMass; i++) {
            pointMass[i] = scanner.nextInt();
            if (minValue > pointMass[i]) {
                minValue = pointMass[i];
            }
            if (maxValue < pointMass[i]) {
                maxValue = pointMass[i];
            }
        }
        //тут реализуйте логику задачи с применением сортировки подсчетом
        int[] counter = new int[maxValue - minValue + 1];
        for (int i = 0; i < sizeOfMass; i++) {
            counter[pointMass[i] - minValue]++;
        }
        int i = 0;
        for (int j = 0; j < counter.length; j++) {
            for (int k = 0; k < counter[j]; k++) {
                pointMass[i++] = j + minValue;
            }
        }
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return pointMass;
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson05/dataB.txt");
        B_CountSort instance = new B_CountSort();
        int[] result = instance.countSort(stream);
        for (int index : result) {
            System.out.print(index + " ");
        }
    }

}
