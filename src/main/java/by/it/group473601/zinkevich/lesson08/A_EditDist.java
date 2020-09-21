package by.it.group473601.zinkevich.lesson08;

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

        int[][] array = new int [one.length() + 1][two.length() + 1];
        int max = 101;
        for(int i = 0; i < one.length() + 1; i++){
            for (int j = 0; j < two.length() + 1; j++){
                array[i][j] = max;
            }
        }

        int result = -1;
        for(int i = 0; i < one.length() + 1; i++){
            for (int j = 0; j < two.length() + 1; j++){
                result = editDistance(i, j, array, max, one, two);
            }
        }

        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
    }

    int editDistance(int i, int j, int[][] array, int max, String one, String two) {

        if (array[i][j] == max){
            if (i == 0){
                array[i][j] = j;
            }
            else {
                if (j == 0) {
                    array[i][j] = i;
                }
                else {
                    int insert = editDistance(i, j - 1, array, max, one, two) + 1;
                    int delete = editDistance(i - 1 , j, array, max, one, two) + 1;
                    int substitute = editDistance(i - 1 , j - 1, array, max, one, two) + difference(one.charAt(i-1), two.charAt(j-1));

                    array[i][j] = min(insert, delete, substitute);
                }
            }
        }
        return array[i][j];
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

    int difference(char a, char b){
        if (a == b) {
            return 0;
        }
        else {
            return 1;
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group473601/zinkevich/lesson08/dataABC.txt");
        A_EditDist instance = new A_EditDist();
        Scanner scanner = new Scanner(stream);
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
    }
}

