package by.it.group473601.kalashynski.lesson07;

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
    int difference(char a, char b) {
        if (a == b) {
            return 0;
        } else {
            return 1;
        }
    }

    int min(int number1, int number2, int number3) {
        int min = -1;
        if (number1 < number2) {
            min = number1;
        } else {
            min = number2;
        }
        if (min > number3) {
            min = number3;
        }
        return min;
    }

    int editDistTD(int[][] mas, int i, int j, String one, String two) {
        if (mas[i][j] == -1) {
            if (i == 0) {
                mas[i][j] = j;
            } else if (j == 0) {
                mas[i][j] = i;
            } else {
                int ins = editDistTD(mas, i, j - 1, one, two) + 1;
                int del = editDistTD(mas, i - 1, j, one, two) + 1;
                int sub = editDistTD(mas, i - 1, j - 1, one, two) +
                        difference(one.charAt(i - 1), two.charAt(j - 1));
                mas[i][j] = min(ins, del, sub);
            }
        }
        return mas[i][j];
    }


    String getDistanceEdinting(String one, String two) {
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        int n = one.length() + 1;
        int m = two.length() + 1;
        int mas[][] = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                mas[i][j] = -1;
            }
        }
        editDistTD(mas, n - 1, m - 1, one, two);

        String revertResult = "";
        int i = one.length();
        int j = two.length();
        while (i >= 1) {
            while (j >= 1) {
                int insert = mas[i][j - 1];
                int delete = mas[i - 1][j];
                int substitute = mas[i - 1][j - 1];
                int min = min(delete, insert, substitute);

                int coef = difference(one.charAt(i - 1), two.charAt(j - 1));
                if (min == substitute) {
                    if (coef != 0) {
                        revertResult += "~" + two.charAt(j - 1) + ",";
                    } else {
                        revertResult += "#,";
                    }
                    i--;
                    j--;
                }
                if (min != delete) {
                    if (min == insert) {
                        revertResult += "+" + two.charAt(j - 1) + ",";
                        j--;
                    }
                } else {
                    revertResult += "-" + one.charAt(i - 1) + ",";
                    i--;
                }

            }
        }

        String[] arrayResult = revertResult.split(",");
        String result = "";
        for (int k = arrayResult.length - 1; k >= 0; k--) {
            result += arrayResult[k] + ",";
        }
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group473601/kalashynski/lesson07/dataABC.txt");
        C_EditDist instance = new C_EditDist();
        Scanner scanner = new Scanner(stream);
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(), scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(), scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(), scanner.nextLine()));
    }

}
