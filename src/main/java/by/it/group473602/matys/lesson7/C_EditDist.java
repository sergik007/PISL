package by.it.group473602.matys.lesson7;

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
    Итерационно вычислить алгоритм преобразования двух данных непустых строк
    Вывести через запятую редакционное предписание в формате:
     операция("+" вставка, "-" удаление, "~" замена, "#" копирование)
     символ замены или вставки

    Sample Input 1:
    ab
    ab
    Sample Output 1:
    #,#,

    Sample Input 2:
    short
    ports
    Sample Output 2:
    -s,~p,#,#,#,+s,

    Sample Input 3:
    distance
    editing
    Sample Output 2:
    +e,#,#,-s,#,~i,#,-c,~g,


    P.S. В литературе обычно действия редакционных предписаний обозначаются так:
    - D (англ. delete) — удалить,
    + I (англ. insert) — вставить,
    ~ R (replace) — заменить,
    # M (match) — совпадение.
*/

public class C_EditDist {

    String getDistanceEdinting(String one, String two) {
		
	int[][] levensteignDistances = new int[one.length() + 1][two.length() + 1];

	for (int i = 0; i < one.length() + 1; i++) {
	    levensteignDistances[i][0] = i;
	}

	for (int j = 0; j < two.length() + 1; j++) {
	    levensteignDistances[0][j] = j;
	}

	for (int i = 0; i < one.length(); i++) {
	    for (int j = 0; j < two.length(); j++) {
		int cost = getDiff(one.charAt(i), two.charAt(j));
		levensteignDistances[i + 1][j + 1] = Math.min(Math.min(levensteignDistances[i][j + 1] + 1,
			levensteignDistances[i + 1][j] + 1), levensteignDistances[i][j] + cost);
	    }
	}
	
	return formSequenceChanges(levensteignDistances, one, two);
    }

    private static String formSequenceChanges(int[][] levensteignDistances, String one, String two) {

	StringBuilder result = new StringBuilder();

	int a = one.length();
	int b = two.length();

	while (a > 0) {
	    while (b > 0) {

		int needToBeInsert = levensteignDistances[a][b - 1];
		int needToBeDelete = levensteignDistances[a - 1][b];
		int needToBeReplace = levensteignDistances[a - 1][b - 1];
		int minimum = Math.min(Math.min(needToBeDelete, needToBeInsert), needToBeReplace);

		if (minimum == needToBeReplace) {
		    int cost = getDiff(one.charAt(a-- - 1), two.charAt(b-- - 1));
		    if (cost == 0) {
			result.append("#,");
		    }
		    if (cost == 1) {
			result.append("~" + two.charAt(b) + ",");
		    }
		}
		if (minimum == needToBeDelete) {
		    result.append("-" + one.charAt(--a) + ",");
		}
		if (minimum == needToBeInsert) {
		    result.append("+" + two.charAt(--b) + ",");
		}
	    }
	}
	return result.toString();

    }

    private static int getDiff(char one, char two) {
	return one != two ? 1 : 0;
    }

    public static void main(String[] args) throws FileNotFoundException {
	String root = System.getProperty("user.dir") + "/src/";
	InputStream stream = new FileInputStream(root + "by/it/group473602/matys/lesson7/dataABC.txt");
	C_EditDist instance = new C_EditDist();
	Scanner scanner = new Scanner(stream);
	System.out.println(instance.getDistanceEdinting(scanner.nextLine(), scanner.nextLine()));
	System.out.println(instance.getDistanceEdinting(scanner.nextLine(), scanner.nextLine()));
	System.out.println(instance.getDistanceEdinting(scanner.nextLine(), scanner.nextLine()));
	scanner.close();
    }

}
