package by.it.group473602.matys.lesson5;

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
        int n = scanner.nextInt();
        int[] points=new int[n];

        //читаем точки
        for (int i = 0; i < n; i++) {
            points[i]=scanner.nextInt();
        }
        scanner.close();
        //тут реализуйте логику задачи с применением сортировки подсчетом
        sort(points);

        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return points;
    }
    private static int[] sort(int[] array) {
	   int min, max = min = array[0];
	   //determine the max and min in the array
	   for (int i = 1; i < array.length; i++) {
	     if (array[i] < min)
	       min = array[i];
	 
	     if (array[i] > max)
	       max = array[i];
	   }
	   return sort(array, min, max);
	 }

    private static int[] sort(int[] array, int min, int max) {
	   //the range is useful to minmize the memory usage
	   //countIntegers holds the number of each integer
	   int[] countIntegers = new int[max - min + 1];
	 
	   for (int i = 0; i < array.length; i++) {
	     countIntegers[array[i] - min]++;
	   }
	 
	   int insertPosition = 0;
	   //fill array in sorted order
	   for (int i = min; i <= max; i++) {
	     for (int j = 0; j < countIntegers[i - min]; j++) {
	       array[insertPosition++] = i;
	     }
	   }
	   return array;
	 }
	 
	
    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group473602/matys/lesson5/dataB.txt");
        B_CountSort instance = new B_CountSort();
        int[] result=instance.countSort(stream);
        for (int index:result){
            System.out.print(index+" ");
        }
    }

}
