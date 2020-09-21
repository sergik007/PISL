package by.it.group473601.dontsova.lesson8;

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


        String s1=one;
        String s2=two;
        int n=one.length( );
        int m=two.length();
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return Edit_Distance(s1,s2,n,m);
    }
    int Edit_Distance( String s1, String s2, int n, int m )
    {
/* Here n = len(s1)
       m = len(s2) */
        if(n == 0 && m == 0)   //Base case
            return 0;
        if(n == 0)            //Base case
            return m;
        if( m == 0 )         //Base Case
            return n;

/* Recursive Part */
        int   a;
        if (s1.charAt(n-1) == s2.charAt(m-1)) {
            a=Edit_Distance(s1, s2, n-1, m-1)+0;
        }
        else{
            a=Edit_Distance(s1, s2, n-1, m-1)+1;
        }
        int   b  = Edit_Distance(s1, s2, n-1, m) + 1;                      //Deletion
        int   c  = Edit_Distance(s1, s2, n, m-1) + 1;                      //Insertion

        return  min(a, b, c);
    }
    public static int min(int a, int b, int c)
    {
        int min = -1;
        if (a < b) {
            min = a;
        } else {
            min = b;
        }
        if (min > c) {
            min = c;
        }
        return min;
    }



    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group473601/dontsova/lesson8/dataABC.txt");
        A_EditDist instance = new A_EditDist();
        Scanner scanner = new Scanner(stream);
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
    }
}

