package by.it.group473601.makar.lesson08;

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
        int distances [][]= new int[firstString.length() + 1][secondString.length() + 1];
        for (int i = 0; i < firstString.length() + 1; i++) {
            distances[i][0] = i;
        }
        for (int j = 0; j < secondString.length() + 1; j++) {
            distances[0][j] = j;
        }
        for (int i = 0; i < firstString.length(); i++) {
            for (int j = 0; j < secondString.length(); j++) {
                int coef = getDifference(firstString.charAt(i), secondString.charAt(j));
                distances[i + 1][j + 1] = getMinimum(distances[i - 1 + 1][j + 1] + 1, distances[i + 1][j - 1 + 1] + 1, distances[i - 1 + 1][j - 1 + 1] + coef);
            }
        }
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        int result=distances[firstString.length()][secondString.length()];
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
        B_EditDist instance = new B_EditDist();
        Scanner scanner = new Scanner(stream);
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
    }

}
