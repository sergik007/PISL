package by.it.group473601.zinkevich.lesson05;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

/*
Видеорегистраторы и площадь 2.
Условие то же что и в задаче А.

        По сравнению с задачей A доработайте алгоритм так, чтобы
        1) он оптимально использовал время и память:
            - за стек отвечает элиминация хвостовой рекурсии,
            - за сам массив отрезков - сортировка на месте
            - рекурсионные вызовы должны проводится на основе 3-разбиения

        2) при поиске подходящих отрезков для точки реализуйте метод бинарного поиска,
        помните при реализации, что поиск множественный
        (т.е. отрезков, подходящих для точки, может быть много)

    Sample Input:
    2 3
    0 5
    7 10
    1 6 11
    Sample Output:
    1 0 0

*/


public class C_QSortOptimized {

    //отрезок
    private class Segment  implements Comparable{
        int start;
        int stop;

        Segment(int start, int stop){
            if (start < stop) {
                this.start = start;
                this.stop = stop;
            }
            else {
                this.start = stop;
                this.stop = start;
            }
        }

        @Override
        public int compareTo(Object object) {
            //подумайте, что должен возвращать компаратор отрезков
            Segment segment = (Segment) object;
            if(this.stop < segment.stop) {  /* текущее меньше полученного */
                return -1;
            }
            else {
                if (this.stop > segment.stop) {  /* текущее больше полученного */
                    return 1;
                }
            }
            return 0;
        }
    }


    int[] getAccessory2(InputStream stream) throws FileNotFoundException {
        //подготовка к чтению данных
        Scanner scanner = new Scanner(stream);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        //число отрезков отсортированного массива
        int n = scanner.nextInt();
        Segment[] segments=new Segment[n];
        //число точек
        int m = scanner.nextInt();
        int[] points=new int[m];
        int[] result=new int[m];

        //читаем сами отрезки
        for (int i = 0; i < n; i++) {
            //читаем начало и конец каждого отрезка
            segments[i]=new Segment(scanner.nextInt(),scanner.nextInt());
        }
        //читаем точки
        for (int i = 0; i < n; i++) {
            points[i]=scanner.nextInt();
        }
        //тут реализуйте логику задачи с применением быстрой сортировки
        //в классе отрезка Segment реализуйте нужный для этой задачи компаратор

        quickSort(segments, 0, segments.length - 1);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++){
                if (points[i] >= segments[j].start && points[i] <= segments[j].stop){
                    result[i]++;
                }
            }
        }
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
    }


    public static void quickSort (Segment[] arr, int b, int e)
    {
        int l = b, r = e;
        Segment basic = arr[(l + r) / 2]; // Опорным элементом для примера возьмём средний
        while (l <= r)
        {
            while (arr[l].compareTo(basic) == -1)
                l++;
            while (arr[l].compareTo(basic) == 1)
                r--;
            if (l <= r)
                swap (arr[l++], arr[r--]);
        }
        if (b < r)
            quickSort (arr, b, r);
        if (e > l)
            quickSort (arr, l, e);
    }
    public static void swap(Segment number1, Segment number2){
        Segment tmp = number1;
        number1 = number2;
        number2 = tmp;
    }



    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group473601/zinkevich/lesson05/dataC.txt");
        C_QSortOptimized instance = new C_QSortOptimized();
        int[] result=instance.getAccessory2(stream);
        for (int index:result){
            System.out.print(index+" ");
        }
    }

}
