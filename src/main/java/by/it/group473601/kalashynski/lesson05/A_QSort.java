package by.it.group473601.kalashynski.lesson05;

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
    private class Segment implements Comparable {
        int start;
        int stop;

        Segment(int start, int stop) {
            if (start <= stop) {
                this.start = start;
                this.stop = stop;
            } else {
                this.start = stop;
                this.stop = start;
            }
            //тут вообще-то лучше доделать конструктор на случай если
            //концы отрезков придут в обратном порядке
        }

        @Override
        public int compareTo(Object o) {
            if (this.stop < ((Segment) o).stop) {
                return -1;
            } else if (this.stop > ((Segment) o).stop) {
                return 1;
            } else if (this.stop == ((Segment) o).stop) {
                return 0;
            }
            return 0;
        }
    }


    int[] getAccessory(InputStream stream) throws FileNotFoundException {

        Scanner scanner = new Scanner(stream);
        int n = scanner.nextInt();
        Segment[] segments = new Segment[n];
        int m = scanner.nextInt();
        int[] points = new int[m];
        int[] result = new int[m];
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        //число отрезков отсортированного массива

        //число точек


        //читаем сами отрезки
        for (int i = 0; i < n; i++) {
            //читаем начало и конец каждого отрезка
            segments[i] = new Segment(scanner.nextInt(), scanner.nextInt());
        }
        quickSort(segments,0,segments.length-1);


        for (int i = 0; i < m; i++) {
            points[i]=scanner.nextInt();
            for(int k = 0; k < segments.length; k++){
                if(points[i] >= segments[k].start && points[i] <= segments[k].stop){
                    result[i]++;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group473601/kalashynski/lesson05/dataA.txt");
        A_QSort instance = new A_QSort();
        int[] result = instance.getAccessory(stream);
        for (int index : result) {
            System.out.print(index + " ");
        }
    }

    private void quickSort(Segment[] segments, int left, int right) {
        int i = left, j = right;
        Segment temp, p;
        p = segments[(left + right) / 2];
        do {
            while (segments[i].compareTo(p) == -1) {
                i++;
            }
            while (segments[j].compareTo(p) == 1) {
                j--;
            }
            if (i <= j) {
                temp = segments[i];
                segments[i] = segments[j];
                segments[j] = temp;
                i++;
                j--;
            }
        } while (i <= j);
        if (j > left) {
            quickSort(segments, left, j);
        }
        if (i < right) {
            quickSort(segments, i, right);
        }
    }

}
