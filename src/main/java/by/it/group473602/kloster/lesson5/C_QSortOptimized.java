package by.it.group473602.kloster.lesson5;

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
            this.start = start;
            this.stop = stop;
        }

        @Override
        public int compareTo(Object o) {
            //подумайте, что должен возвращать компаратор отрезков
            Segment tmp = (Segment)o;
            if(this.stop < tmp.stop)
            {
      /* текущее меньше полученного */
                return -1;
            }
            else if(this.stop > tmp.stop)
            {
      /* текущее больше полученного */
                return 1;
            }
    /* текущее равно полученному */
            return 0;
        }
    }

    private void quick_sort(Segment[] segments, int j, int i){
        int size = j;
        int k = i;
        Segment c = segments[j/2];
        Segment tmp;
        do{
            while(segments[k].compareTo(c)==-1) k++;
            while(segments[j].compareTo(c)==1) j--;

            if (k<=j){
                tmp = segments[k];
                segments[k]=segments[j];
                segments[j]=tmp;
                k++;
                j--;
            }
        }while(k<=j);

        if (j>0) quick_sort(segments, j, 0);
        if (size>k) quick_sort(segments, size-k, k);
    }

    private int[] binaryFind(Segment [] segments, int[] result, int[] points) {
        //размер отсортированного массива
        int n = segments.length-1;
        //размер массива индексов
        int k = points.length-1;

        for (int i = 0; i < k; i++) {
            int value = points[i];
            //тут реализуйте бинарный поиск индекса
            int l = 0, r = n;
            while (l < r) {
                int m = (l + r) / 2;
                if (value >= segments[m].start && value <= segments[m].stop) {
                    result[i]++;
                    break;
                } else {
                    if (segments[m].start > value) r = m - 1;
                    else {
                        l = m + 1;
                    }
                }
            }
        }
        return result;
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
        for (int i = 0; i < m; i++) {
            points[i]=scanner.nextInt();
        }
        //тут реализуйте логику задачи с применением быстрой сортировки
        //в классе отрезка Segment реализуйте нужный для этой задачи компаратор

        quick_sort(segments, segments.length-1, 0);

        result = binaryFind(segments,result, points);

        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group473602/kloster/lesson5/dataC.txt");
        C_QSortOptimized instance = new C_QSortOptimized();
        int[] result=instance.getAccessory2(stream);
        for (int index:result){
            System.out.print(index+" ");
        }
    }

}
