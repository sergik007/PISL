package by.it.group473602.prilepin.lesson07;

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
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!


        String result = "";
        int n = one.length()+1;
        int m = two.length()+1;
        int [][] d = new int[n][m];
        for (int i = 0; i<n; i++){
            d[i][0]=i;
        }
        for (int j = 0; j<m; j++){
            d[0][j]=j;
        }
        for (int i = 0; i<n-1; i++){
            for (int j=0; j<m-1; j++){
                int c = getDiff(one.charAt(i), two.charAt(j));
                d[i+1][j+1] = getMin(d[i][j+1]+1, d[i+1][j]+1, d[i][j]+c);
            }
        }
        int a = one.length();
        int b = two.length();
        while (a>=1){
            while(b>=1){
                int ins = d[a][b-1];
                int del = d[a-1][b];
                int rep = d[a-1][b-1];
                int min = getMin(ins, del, rep);

                if (min == rep){
                    int c = getDiff(one.charAt(a-1), two.charAt(b-1));
                    switch (c){
                        case 0:{
                            result += "#,";
                        }break;
                        case 1:{
                            result+="~" + two.charAt(b-1) + ",";
                        }break;
                    }
                    a--;
                    b--;
                }
                if (min == del){
                    result+="-" + one.charAt(a-1) + ",";
                    a--;
                }
                else{
                    if(min == ins){
                        result+="+" + two.charAt(b-1)+",";
                        b--;
                    }
                }
            }
        }
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
    }



    private static int getDiff(char one, char two){
        return one != two ? 1: 0;
    }


    private static int getMin(int one, int two, int three){
        int min = -1;
        if (two > one){
            min = one;
        }else{
            min = two;
        }
        if (three<min){
            min = three;
        }
        return min;
    }



    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson08/dataABC.txt");
        C_EditDist instance = new C_EditDist();
        Scanner scanner = new Scanner(stream);
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
    }

}
