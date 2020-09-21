package by.it.group473601.dontsova.lesson4;

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
/* Сначала делим список на кусочки (по 1 элементу), затем сравниваем каждый элемент с соседним, сортируем
и объединяем. В итоге, все элементы отсортированы и объединены вместе.*/
public class B_MergeSort {

    int[] merge(int[] array1, int[] array2){
        int size1=array1.length;
        int size2=array2.length;
        int[] result=new int[size1+size2];
        int m1 = 0, n2 = 0;
        for (int i = 0; i < size1+size2; i++){
            if(n2<size2 && m1<size1)
            {
                if(array1[m1]>array2[n2])
                    result[i]=array2[n2++];
                else result[i]=array1[m1++];
            }
            else if(n2<size2)
            {
                result[i]=array2[n2++];
            }

            else result[i]=array1[m1++];

        }
        return result;
    }

    int[] mergeSort(int[] arr, int l, int r){
        int[] result = new int[1];
        int index = (int)(l + r) / 2;
        if (l < r){
           return merge(mergeSort(arr, l, index), mergeSort(arr, index + 1, r));
        }else {
            result[0] = arr[l];
            return result;
        }
    }

    int[] getMergeSort(InputStream stream) throws FileNotFoundException {
        //подготовка к чтению данных
        Scanner scanner = new Scanner(stream);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!

        //размер массива
        int n = scanner.nextInt();
        //сам массива
        int[] a=new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
            System.out.println(a[i]);

        }

        // тут ваше решение (реализуйте сортировку слиянием)
        // https://ru.wikipedia.org/wiki/Сортировка_слиянием

        a = mergeSort(a, 0, a.length - 1);




        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return a;
    }
    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group473601/dontsova/lesson4/dataB.txt");
        B_MergeSort instance = new B_MergeSort();
        //long startTime = System.currentTimeMillis();
        int[] result=instance.getMergeSort(stream);
        //long finishTime = System.currentTimeMillis();
        for (int index:result){
            System.out.print(index+" ");
        }
    }


}
