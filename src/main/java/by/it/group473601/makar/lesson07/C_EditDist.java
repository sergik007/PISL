package by.it.group473601.makar.lesson07;

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

    String getDistanceEdinting(String firstString, String two) {
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        int[][] distances = new int[firstString.length() + 1][two.length() + 1];
        int delete=0;
        int insert=0;
        int replecement=0;
        int min=0;
        int cost=0;
        for (int i = 0; i < firstString.length() + 1; i++) {
            distances[i][0] = i;
        }
        for (int j = 0; j < two.length() + 1; j++) {
            distances[0][j] = j;
        }

        for (int i = 0; i < firstString.length(); i++) {
            for (int j = 0; j < two.length(); j++) {
                cost = getDifference(firstString.charAt(i), two.charAt(j));
                distances[i + 1][j + 1] = getMinimum(distances[i - 1 + 1][j + 1] + 1, distances[i + 1][j - 1 + 1] + 1, distances[i - 1 + 1][j - 1 + 1] + cost);
            }
        }

        String reverse = "";
        int i = firstString.length();
        int j = two.length();
        while (i >= 1) {
            while (j >= 1) {
                delete = distances[i - 1][j];
                insert = distances[i][j - 1];
                replecement = distances[i - 1][j - 1];
                min = getMinimum(delete, insert, replecement);
                cost = getDifference(firstString.charAt(i - 1), two.charAt(j - 1));
                if (min == replecement) {
                    if (cost == 0) {
                        reverse += "#,";
                    } else {
                        reverse += "~" + two.charAt(j - 1) + ",";
                    }
                    i--;
                    j--;
                }
                if (min == delete) {
                    reverse += "-" + firstString.charAt(i - 1) + ",";
                    i--;
                } else {
                    if (min == insert) {
                        reverse += "+" + two.charAt(j - 1) + ",";
                        j--;
                    }

                }

            }
        }
        String[] distancesResult = reverse.split(",");
        String result = "";
        for (int k = distancesResult.length - 1; k >= 0; k--) {
            result += distancesResult[k] + ",";
        }
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
    }

    int getDifference(char first, char second){
        return first == second ? 0: 1;
    }

    int getMinimum(int first, int second, int third) {
        int min = -1;
        if (first < second) {
            min = first;
        } else {
            min = second;
        }
        if (min > third) {
            min = third;
        }
        return min;
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group473601/makar/lesson08/dataABC.txt");
        C_EditDist instance = new C_EditDist();
        Scanner scanner = new Scanner(stream);
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
    }

}
