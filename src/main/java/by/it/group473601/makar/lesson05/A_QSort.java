package by.it.group473601.makar.lesson05;

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
            if (stop<start){
                this.start = stop;
                this.stop = start;
            } else {
                this.start = start;
                this.stop = stop;
            }
            //тут вообще-то лучше доделать конструктор на случай если
            //концы отрезков придут в обратном порядке
        }

        @Override
        public int compareTo(Object o) {
            //подумайте, что должен возвращать компаратор отрезков
            int stopToCompare=((Segment)o).stop;
            if (this.stop>stopToCompare) return 1;
            if (this.stop<stopToCompare) return -1;
            else return 0;
        }

    }


    int[] getAccessory(InputStream stream) throws FileNotFoundException {
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

        doQuickSort(0,segments.length - 1,segments);

        //читаем точки
        for (int i = 0; i < n; i++) {
            points[i]=scanner.nextInt();
            for(int j = 0; j < segments.length; j++){
                if(points[j] <= segments[j].stop && points[i] >= segments[j].start){
                    result[i]++;
                }
            }
        }
        //тут реализуйте логику задачи с применением быстрой сортировки
        //в классе отрезка Segment реализуйте нужный для этой задачи компаратор
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
    }

    private void doQuickSort(int start, int end, Segment[] array) {
        if (start >= end)
            return;
        int i = start;
        int j = end;
        Segment current = array[i - (i - j) / 2];
        while (i < j) {
            while (array[i].compareTo(current)==-1) { //пока текущ знач из левого листа меньше ключевого берем следующий эл из левого листа
                i++;
            }
            while (current.compareTo(array[j])==1) {  //пока текущ знач из правого листа больше ключевого берем следующий эл из правого листа
                j--;
            }
            if (i < j) {
                Segment temp = array[i];
                array[i] = array[j];
                array[j] = temp;
               /* if (i == current)
                    current = j;
                else if (j == current)
                    current = i;*/
               j--;
               i++;
            }
        }
        doQuickSort(start, j, array);
        doQuickSort(i, end, array);
    }



    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group473601/makar/lesson05/dataA.txt");
        A_QSort instance = new A_QSort();
        int[] result=instance.getAccessory(stream);
        for (int index:result){
            System.out.print(index+" ");
        }
    }

}
