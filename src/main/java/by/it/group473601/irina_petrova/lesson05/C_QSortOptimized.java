package by.it.group473601.irina_petrova.lesson05;

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
    private class Segment implements Comparable<Segment> {
        int start;
        int stop;

        Segment(int start, int stop) {
            this.start = start;
            this.stop = stop;
        }

        @Override
        public int compareTo(Segment segment) {
            Segment obj = (Segment) segment;
            if (obj.start >= this.start) {
                return 1;
            }
            if (obj.start < this.start) {
                return -1;
            }
            return 0;
        }
    }

    int[] getAccessory2(InputStream stream) throws FileNotFoundException {
        //подготовка к чтению данных
        Scanner scanner = new Scanner(stream);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        int callSegments = scanner.nextInt();
        Segment[] segments = new Segment[callSegments];
        //число точек
        int callPoints = scanner.nextInt();
        int[] massPoints = new int[callSegments];
        int[] massResults = new int[callPoints];
        for (int i = 0; i < callPoints; i++) {
            massResults[i] = 0;
        }
        //читаем сами отрезки
        for (int i = 0; i < callSegments; i++) {
            //читаем начало и конец каждого отрезка
            segments[i] = new Segment(scanner.nextInt(), scanner.nextInt());
        }
        //читаем точки
        for (int i = 0; i < callSegments; i++) {
            massPoints[i] = scanner.nextInt();
        }
        //тут реализуйте логику задачи с применением быстрой сортировки
        //в классе отрезка Segment реализуйте нужный для этой задачи компаратор
        quickSort(segments, 0, segments.length - 1);
        dopSort(massResults, segments, massPoints);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return massResults;
    }

    public void quickSort(Segment[] segments, int low, int high) {
        Segment newSegments = segments[(low + high) / 2];
        int left = low;
        int right = high;
        while (low <= high) {
            while (segments[low].compareTo(newSegments) == 1) {
                low++;
            }
            while (segments[high].compareTo(newSegments) == -1) {
                high--;
            }
            if (low <= high) {
                Segment tmpObject = segments[low + 1];
                segments[low + 1] = segments[high - 1];
                segments[high - 1] = tmpObject;
                low++;
                high--;
            }
        }
        if (right > low) {
            quickSort(segments, low, right);
        }
        if (left < high) {
            quickSort(segments, left, high);
        }
    }

    public void dopSort(int[] resultMass, Segment[] segments, int[] pointsMass) {
        for (int i = 0; i < pointsMass.length; i++) {
            int indexright = segments.length - 1;
            int indexLeft = 0;
            while (indexLeft <= indexright) {
                int middleIndex = (indexLeft + indexright) / 2;
                if (segments[middleIndex].start <= pointsMass[i] && segments[middleIndex].stop >= pointsMass[i]) {
                    resultMass[i]++;
                    break;
                } else {
                    if (segments[middleIndex].start < pointsMass[i]) {
                        indexright = middleIndex - 1;
                    } else {
                        indexLeft = middleIndex + 1;
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group473601/irina_petrova/lesson05/dataC.txt");
        C_QSortOptimized instance = new C_QSortOptimized();
        int[] result = instance.getAccessory2(stream);
        for (int index : result) {
            System.out.print(index + " ");
        }
    }
}
