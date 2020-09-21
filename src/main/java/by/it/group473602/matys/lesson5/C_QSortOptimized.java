package by.it.group473602.matys.lesson5;

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

    // отрезок
    private class Segment implements Comparable<Segment> {
	int start;
	int stop;

	Segment(int start, int stop) {
	    this.start = start;
	    this.stop = stop;
	}

	@Override
	public int compareTo(Segment o) {
	    if (this.start > o.start) {
		return 1;
	    } else if (this.start < this.start) {
		return -1;
	    } else if (this.stop > o.stop) {
		return 1;
	    } else if (this.stop < o.stop) {
		return -1;
	    } else {
		return 0;
	    }
	    // return (this.start>o.start)?1:(this.start<o.start)?-1:0;
	}
    }

    
    public void binarySearch(int[] result, Segment[] segments, int[] points) {
	for (int i = 0; i < points.length; i++) {
	    int leftIndex = 0;
	    int rightIndex = segments.length - 1;
	    while (leftIndex <= rightIndex) {
		int middleIndex = leftIndex + (rightIndex - leftIndex) / 2;
		if (segments[middleIndex].start <= points[i] && segments[middleIndex].stop >= points[i]) {
		    result[i]++;
		    for (int j = middleIndex - 1; j >= leftIndex; j--) {
			if (segments[j].start <= points[i] && segments[j].stop >= points[i]) {
			    result[i]++;
			}
		    }
		    for (int j = middleIndex + 1; j <= rightIndex; j++) {
			if (segments[j].start <= points[i] && segments[j].stop >= points[i]) {
			    result[i]++;
			}
		    }
		    break;
		} else {
		    if (segments[middleIndex].start < points[i]) {
			rightIndex = middleIndex - 1;
		    } else {
			leftIndex = middleIndex + 1;
		    }
		}
	    }
	}
    }

    int[] getAccessory2(InputStream stream) throws FileNotFoundException {
	// подготовка к чтению данных
	Scanner scanner = new Scanner(stream);
	// !!!!!!!!!!!!!!!!!!!!!!!!! НАЧАЛО ЗАДАЧИ !!!!!!!!!!!!!!!!!!!!!!!!!
	// число отрезков отсортированного массива
	int n = scanner.nextInt();
	Segment[] segments = new Segment[n];
	// число точек
	int m = scanner.nextInt();
	int[] points = new int[m];
	int[] result = new int[m];

	// читаем сами отрезки
	for (int i = 0; i < n; i++) {
	    // читаем начало и конец каждого отрезка
	    segments[i] = new Segment(scanner.nextInt(), scanner.nextInt());
	}
	
	for (int i = 0; i < segments.length; i++) {
	    System.out.println(segments[i].start + "," + segments[i].stop);
	}

	// читаем точки
	for (int i = 0; i < m; i++) {
	    points[i] = scanner.nextInt();
	}
	scanner.close();
	// тут реализуйте логику задачи с применением быстрой сортировки
	// в классе отрезка Segment реализуйте нужный для этой задачи компаратор

	Quicksort3.sort(segments);
	binarySearch(result, segments, points);
	

	// !!!!!!!!!!!!!!!!!!!!!!!!! КОНЕЦ ЗАДАЧИ !!!!!!!!!!!!!!!!!!!!!!!!!
	return result;
    }

    public static void main(String[] args) throws FileNotFoundException {
	String root = System.getProperty("user.dir") + "/src/";
	InputStream stream = new FileInputStream(root + "by/it/group473602/matys/lesson5/dataC.txt");
	C_QSortOptimized instance = new C_QSortOptimized();
	int[] result = instance.getAccessory2(stream);
	for (int index : result) {
	    System.out.print(index + " ");
	}
    }

}
