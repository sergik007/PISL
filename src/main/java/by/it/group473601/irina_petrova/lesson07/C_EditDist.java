package by.it.group473601.irina_petrova.lesson07;

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

    String getDistanceEdinting(String firstString, String secondString) {
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        String result = "";
        int[][] MatrixD = new int[firstString.length() + 1][secondString.length() + 1];
        char[][] MatrixP = new char[firstString.length() + 1][secondString.length() + 1];

        // Базовые значения
        for (int i = 0; i < firstString.length(); i++) {
            MatrixD[i][0] = i;
            MatrixP[i][0] = '-';
        }
        for (int j = 0; j < secondString.length(); j++) {
            MatrixD[0][j] = j;
            MatrixP[0][j] = '+';
        }

        for (int i = 1; i <= firstString.length(); i++)
            for (int j = 1; j <= secondString.length(); j++) {
                int cost = (firstString.charAt(i - 1) != secondString.charAt(j - 1)) ? 1 : 0;
                if (MatrixD[i][j - 1] < MatrixD[i - 1][j] && MatrixD[i][j - 1] < MatrixD[i - 1][j - 1] + cost) {
                    //Вставка
                    MatrixD[i][j] = MatrixD[i][j - 1] + 1;
                    MatrixP[i][j] = '+';
                } else if (MatrixD[i - 1][j] < MatrixD[i - 1][j - 1] + cost) {
                    //Удаление
                    MatrixD[i][j] = MatrixD[i - 1][j] + 1;
                    MatrixP[i][j] = '-';
                } else {
                    //Замена или отсутствие операции
                    MatrixD[i][j] = MatrixD[i - 1][j - 1] + cost;
                    MatrixP[i][j] = (cost == 1) ? '~' : '#';
                }
            }

        //Восстановление предписания
        StringBuilder route = new StringBuilder("");
        int i = firstString.length(), j = secondString.length();
        do {
            char c = MatrixP[i][j];
            route.append(',');
            if (c == '-') {
                route.append(firstString.charAt(j - 1));
            } else if (c == '+') {
                route.append(secondString.charAt(j - 1));
            } else if (c == '~') {
                route.append(secondString.charAt(j - 1));
            }
            route.append(c);
            if (c == '~' || c == '#') {
                i--;
                j--;
            } else if (c == '-') {
                i--;
            } else {
                j--;
            }
        } while ((i != 0) || (j != 0));
        result = route.reverse().toString();
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group473601/irina_petrova/lesson07/dataABC.txt");
        C_EditDist instance = new C_EditDist();
        Scanner scanner = new Scanner(stream);
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(), scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(), scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(), scanner.nextLine()));
    }

}
