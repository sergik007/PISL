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


    int getDistanceEdinting(String one, String two) {
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        int result = 0;
        int[][] levensteignDistances = new int [one.length() + 1][two.length() + 1];
        int roundInfinity = 32765;
        for(int i = 0; i < one.length() + 1; i++) {
            for (int j = 0; j < two.length() + 1; j++) {
                levensteignDistances[i][j] = roundInfinity;
            }
        }

        for(int i = 0; i < one.length() + 1; i++) {
            for (int j = 0; j < two.length() + 1; j++) {
                result = editDistance(i, j, levensteignDistances, roundInfinity, one, two);
            }

        }

        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
    }


    int editDistance(int i, int j, int[][] array, int max, String firstString, String secondString) {
        int insert=0;
        int delete=0;
        int replecemtnt=0;
        if (array[i][j] == max){
            if (i == 0){
                array[i][j] = j;
            }
            else {
                if (j == 0) {
                    array[i][j] = i;
                }
                else {
                    insert = editDistance(i, j - 1, array, max, firstString, secondString) + 1;
                    delete = editDistance(i - 1 , j, array, max, firstString, secondString) + 1;
                    replecemtnt = editDistance(i - 1 , j - 1, array, max, firstString, secondString) + getDifference(firstString.charAt(i-1), secondString.charAt(j-1));

                    array[i][j] = getMinimum(insert, delete, replecemtnt);
                }
            }
        }
        return array[i][j];
    }

    private static int getDifference(char one, char two){
        return one != two ? 1: 0;
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
        A_EditDist instance = new A_EditDist();
        Scanner scanner = new Scanner(stream);
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
    }
}

