package by.it.group473601.kalashynski.lesson05;

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
    private class Segment implements Comparable {
        int start;
        int stop;

        Segment(int start, int stop) {
            this.start = start;
            this.stop = stop;
        }

        @Override
        public int compareTo(Object o) {
            Segment segment = (Segment) o;
            if (this.stop < segment.stop) {
                return -1;
            }
            if (this.stop > segment.stop) {
                return 1;
            }
            //подумайте, что должен возвращать компаратор отрезков
            return 0;
        }
    }


    int[] getAccessory2(InputStream stream) throws FileNotFoundException {
        //подготовка к чтению данных
        Scanner scanner = new Scanner(stream);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        //число отрезков отсортированного массива
        int n = scanner.nextInt();
        Segment[] segments = new Segment[n];
        //число точек
        int m = scanner.nextInt();
        int[] points = new int[m];
        int[] result = new int[m];

        //читаем сами отрезки
        for (int i = 0; i < n; i++) {
            //читаем начало и конец каждого отрезка
            segments[i] = new Segment(scanner.nextInt(), scanner.nextInt());
        }
        //читаем точки
        for (int i = 0; i < n; i++) {
            points[i] = scanner.nextInt();
        }
        //тут реализуйте логику задачи с применением быстрой сортировки
        //в классе отрезка Segment реализуйте нужный для этой задачи компаратор
        OptimizedQuickSort(segments, 0, segments.length-1);
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


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group473601/kalashynski/lesson05/dataC.txt");
        C_QSortOptimized instance = new C_QSortOptimized();
        int[] result = instance.getAccessory2(stream);
        for (int index : result) {
            System.out.print(index + " ");
        }
    }

    void OptimizedQuickSort(Segment[] Arr, int Left, int Right) {
        int Pivot;
        Pivot = Q_Sort(Arr, Left, Right);
        if (Left < Pivot - 1) {
            OptimizedQuickSort(Arr, Left, Pivot - 1);
        }
        if (Right > Pivot + 1) {
            OptimizedQuickSort(Arr, Pivot + 1, Right);
        }
    }

    int Q_Sort(Segment[] Arr, int Left, int Right) {
        Segment Pivot;
        Pivot = Arr[Left];
        while (Left < Right) {
            while ((Arr[Right] .compareTo(Pivot)>=0 ) && (Left < Right)) {
                Right--;
            }
            if (Left != Right) {
                Arr[Left] = Arr[Right];
                Left++;
            }
            while ((Arr[Left].compareTo(Pivot) <=0 ) && (Left < Right)) {
                Left++;
            }
            if (Left != Right) {
                Arr[Right] = Arr[Left];
                Right--;
            }
        }
        Arr[Left] = Pivot;
        return Left;
    }
}
