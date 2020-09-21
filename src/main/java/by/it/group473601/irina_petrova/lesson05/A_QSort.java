package by.it.group473601.irina_petrova.lesson05;

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
    0 5 ----- 0 - start - 5- stop --- камера 1
    7 10 ----- 7- start - 10- stop --- камера 2
    1 6 11 ----- точти событий
    Sample Output:
    1 0 0

*/

public class A_QSort {

    //отрезок
    private class Segment implements Comparable<Segment> {
        int start;
        int stop;

        Segment(int start, int stop) {
            this.start = start;
            this.stop = stop;
        }

        @Override
        public int compareTo(Segment segment) {
            //подумайте, что должен возвращать компаратор отрезков
            if (start - segment.start != 0) {
                return start - segment.start;
            } else {
                return stop - segment.stop;
            }
        }
    }

    int[] getAccessory(InputStream stream) throws FileNotFoundException {
        //подготовка к чтению данных
        Scanner scanner = new Scanner(stream);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        //число отрезков отсортированного массива
        int callSegments = scanner.nextInt();
        //число точек
        int callPoints = scanner.nextInt();

        Segment[] segments = new Segment[2 * callSegments + callPoints];
        int[] result = new int[callPoints];
        int count = 0;
        for (int i = 0; i < callSegments; i++) {
            int start = scanner.nextInt();
            int stop = scanner.nextInt();
            if (start > stop) { //проверка правильности сегмента
                int t = start;
                start = stop;
                stop = t;
            }
            segments[count++] = new Segment(start, -1);
            segments[count++] = new Segment(stop, segments.length);
        }
        for (int i = 0; i < callPoints; i++) {
            int x = scanner.nextInt();
            segments[count++] = new Segment(x, i);
        }
        //тут реализуйте логику задачи с применением быстрой сортировки
        //в классе отрезка Segment реализуйте нужный для этой задачи компаратор
        sort(segments);
        int callCamera = 0;
        for (int i = 0; i < segments.length; i++) {
            if (segments[i].stop < 0) {
                callCamera++;
            } else if (segments[i].stop == segments.length) {
                callCamera--;
            } else {
                result[segments[i].stop] = callCamera;
            }
        }
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
    }

    private int myPartition(Segment segments[], int low, int high) {
        int m = low;
        Segment x = segments[low];
        for (int i = low + 1; i < high; i++) {
            if (segments[i].compareTo(x) <= 0) {
                m++;
                Segment tmp = segments[i];
                segments[i] = segments[m];
                segments[m] = tmp;
            }
            Segment tmp = segments[low];
            segments[low] = segments[m];
            segments[m] = tmp;
        }
        return m;
    }

    private void sort(Segment[] segments) {
        quickSort(segments, 0, segments.length);
    }

    private void quickSort(Segment[] segments, int low, int high) {
        if (low < high) {
            int m = myPartition(segments, low, high);
            quickSort(segments, low, m - 1);
            quickSort(segments, m + 1, high);
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group473601/irina_petrova/lesson05/dataA.txt");
        A_QSort instance = new A_QSort();
        int[] result = instance.getAccessory(stream);
        for (int index : result) {
            System.out.print(index + " ");
        }
    }

}
