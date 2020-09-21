package by.it.group473602.kloster.lesson5;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

/*
Видеорегистраторы и площадь.
На площади установлена одна или несколько камер.
Известны данные о том, когда каждая из них включалась и выключалась (отрезки работы)
Известен список событий на площади (время начала каждого события).
Вам необходимо определить для каждого события сколько камер его записали.

В первой строке задано два целых числа:
    число включений камер (отрезки) 1<=n<=50000
    число событий (точки) 1<=m<=50000.

Следующие n строк содержат по два целых числа ai и bi (ai<=bi) -
координаты концов отрезков (время работы одной какой-то камеры).
Последняя строка содержит m целых чисел - координаты точек.
Все координаты не превышают 10E8 по модулю (!).

Точка считается принадлежащей отрезку, если она находится внутри него или на границе.

Для каждой точки в порядке их появления во вводе выведите,
скольким отрезкам она принадлежит.
    Sample Input:
    2 3
    0 5
    7 10
    1 6 11
    Sample Output:
    1 0 0

*/

public class A_QSort {

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
            Segment tmp = (Segment)o;
            if(this.stop < tmp.stop)
                return -1;
            else if(this.stop > tmp.stop)
                return 1;
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

        if (j>0)
            quick_sort(segments, j, 0);
        if (size>k)
            quick_sort(segments, size-k, k);
    }

    int[] getAccessory(InputStream stream) throws FileNotFoundException {
        Scanner scanner = new Scanner(stream);
        int n = scanner.nextInt();
        Segment[] segments=new Segment[n];
        int m = scanner.nextInt();
        int[] points=new int[m];
        int[] result=new int[m];
        for (int i = 0; i < n; i++) {
            segments[i]=new Segment(scanner.nextInt(),scanner.nextInt());
        }
        for (int i = 0; i < m; i++) {
            points[i]=scanner.nextInt();
        }
        quick_sort(segments, segments.length-1, 0);
        for (int i = 0; i<m; i++){
            int res = 0;
            for (int j = 0; j < n; j++){
                if (points[i]>=segments[j].start && points[i]<=segments[j].stop){
                    res++;
                }
            }
            result[i]=res;
        }
        return result;
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group473602/kloster/lesson5/dataA.txt");
        A_QSort instance = new A_QSort();
        int[] result=instance.getAccessory(stream);
        for (int index:result){
            System.out.print(index+" ");
        }
    }

}
