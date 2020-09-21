package by.it.group473601.borovsky.lesson01.lesson08;

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


        int oneLength = one.length();
        int twoLength = two.length();

        int[] levensteinDistanceOne;
        int[] levensteinDistanceTwo = new int[twoLength + 1];

        for(int i = 0; i <= twoLength;i ++) {
            levensteinDistanceTwo[i] = i;
        }

        for(int i = 1; i <= oneLength; i++){
            levensteinDistanceOne = levensteinDistanceTwo;
            levensteinDistanceTwo = new int[twoLength + 1];
            for(int j = 0; j <= twoLength; j++){
                if(j == 0) levensteinDistanceTwo[j] = i;
                else {
                    int cost = (one.charAt(i - 1) != two.charAt(j - 1)) ? 1 : 0;
                    if(levensteinDistanceTwo[j - 1] < levensteinDistanceOne[j] && levensteinDistanceTwo[j - 1] < levensteinDistanceOne[j - 1] + cost)
                        levensteinDistanceTwo[j] = levensteinDistanceTwo[j - 1] + 1;
                    else if(levensteinDistanceOne[j] < levensteinDistanceOne[j - 1] + cost)
                        levensteinDistanceTwo[j] = levensteinDistanceOne[j] + 1;
                    else
                        levensteinDistanceTwo[j] = levensteinDistanceOne[j - 1] + cost;
                }
            }
        }


        int result = levensteinDistanceTwo[twoLength];
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group473601/borovsky/lesson01/lesson08/dataABC.txt");
        A_EditDist instance = new A_EditDist();
        Scanner scanner = new Scanner(stream);
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
    }
}

