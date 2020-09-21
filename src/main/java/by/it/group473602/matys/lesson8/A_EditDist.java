package by.it.group473602.matys.lesson8;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

/*
Задача на программирование: расстояние Левенштейна
    https://ru.wikipedia.org/wiki/Расстояние_Левенштейна
    http://planetcalc.ru/1721/

Дано:
    Две данных непустые строки длины не более 100, содержащие строчные буквы латинского алфавита.

Необходимо:
    Решить задачу МЕТОДАМИ ДИНАМИЧЕСКОГО ПРОГРАММИРОВАНИЯ
    Рекурсивно вычислить расстояние редактирования двух данных непустых строк

    Sample Input 1:
    ab
    ab
    Sample Output 1:
    0

    Sample Input 2:
    short
    ports
    Sample Output 2:
    3

    Sample Input 3:
    distance
    editing
    Sample Output 3:
    5

*/

public class A_EditDist {

   
    protected int distance(String one, String two, final int i, final int j) {

	if (i == 0) {
	    return j;
	}
	if (j == 0) {
	    return i;
	}

	if (one.charAt(i - 1) == two.charAt(j - 1)) {
	    return distance(one, two, i - 1, j - 1);
	}

	int delete = distance(one, two, i - 1, j) + 1;
	int insert = distance(one, two, i, j - 1) + 1;
	int replace = distance(one, two, i - 1, j - 1) + 1;
	
	return Math.min(Math.min(insert, replace),delete);
    }

    int getDistanceEdinting(String one, String two) {
	return distance(one, two, one.length(), two.length());
    }

    public static void main(String[] args) throws FileNotFoundException {
	String root = System.getProperty("user.dir") + "/src/";
	InputStream stream = new FileInputStream(root + "by/it/group473602/matys/lesson7/dataABC.txt");
	A_EditDist instance = new A_EditDist();
	Scanner scanner = new Scanner(stream);
	System.out.println(instance.getDistanceEdinting(scanner.nextLine(), scanner.nextLine()));
	System.out.println(instance.getDistanceEdinting(scanner.nextLine(), scanner.nextLine()));
	System.out.println(instance.getDistanceEdinting(scanner.nextLine(), scanner.nextLine()));
	scanner.close();
    }
}
