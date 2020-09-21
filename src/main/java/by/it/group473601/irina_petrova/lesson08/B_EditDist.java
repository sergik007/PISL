package by.it.group473601.irina_petrova.lesson08;

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
    Итерационно вычислить расстояние редактирования двух данных непустых строк

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

public class B_EditDist {

    int getDistanceEdinting(String firstString, String secondString) {
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        int result = 0;
        int[][] MatrixD = new int[firstString.length() + 1][secondString.length() + 1];
        for (int i = 0; i < firstString.length(); i++) {
            MatrixD[i][0] = i;
        }
        for (int j = 0; j < secondString.length(); j++) {
            MatrixD[0][j] = j;
        }
        for (int i = 1; i <= firstString.length(); i++)
            for (int j = 1; j <= secondString.length(); j++) {
                int cost = (firstString.charAt(i - 1) != secondString.charAt(j - 1)) ? 1 : 0;
                if (MatrixD[i][j - 1] < MatrixD[i - 1][j] && MatrixD[i][j - 1] < MatrixD[i - 1][j - 1] + cost) {
                    //Вставка
                    MatrixD[i][j] = MatrixD[i][j - 1] + 1;
                } else if (MatrixD[i - 1][j] < MatrixD[i - 1][j - 1] + cost) {
                    //Удаление
                    MatrixD[i][j] = MatrixD[i - 1][j] + 1;
                } else {
                    //Замена или отсутствие операции
                    MatrixD[i][j] = MatrixD[i - 1][j - 1] + cost;
                }
            }
        result = MatrixD[firstString.length()][secondString.length()];
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group473601/irina_petrova/lesson08/dataABC.txt");
        B_EditDist instance = new B_EditDist();
        Scanner scanner = new Scanner(stream);
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
    }

}
