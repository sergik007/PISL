package by.it.group473601.irina_petrova.lesson09;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

/*
Даны число 1<=n<=100 ступенек лестницы и
целые числа −10000<=a[1],…,a[n]<=10000, которыми помечены ступеньки.
Найдите максимальную сумму, которую можно получить, идя по лестнице
снизу вверх (от нулевой до n-й ступеньки), каждый раз поднимаясь на
одну или на две ступеньки.

Sample Input 1:
2
1 2
Sample Output 1:
3

Sample Input 2:
2
2 -1
Sample Output 2:
1

Sample Input 3:
3
-1 2 1
Sample Output 3:
3

*/

public class C_Stairs {

    int getMaxSum(InputStream stream) {
        Scanner scanner = new Scanner(stream);
        int countOfStep = scanner.nextInt();
        int arraySteps[] = new int[countOfStep];
        int arrayResult[] = new int[countOfStep];
        for (int i = 0; i < countOfStep; i++) {
            arraySteps[i] = scanner.nextInt();
            arrayResult[i] = Integer.MIN_VALUE;
        }
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        arrayResult[0] = arraySteps[0];
        arrayResult[1] = arraySteps[1];

        if (arrayResult[0] + arraySteps[1] > arrayResult[1]) {
            arrayResult[1] = arrayResult[0] + arraySteps[1];
        }

        for (int i = 2; i < countOfStep; i++) {
            for (int j = i - 2; j < i; j++) {
                if (arrayResult[j] + arraySteps[i] > arrayResult[i]) {
                    arrayResult[i] = arrayResult[j] + arraySteps[i];
                }
            }
        }

        int result = arrayResult[countOfStep - 1];

        /*for (int k = 0; k < arrayResult.length; k++) {
            System.out.printf(arrayResult[k] + " ");
        }
        System.out.println();*/
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group473601/irina_petrova/lesson09/dataC.txt");
        C_Stairs instance = new C_Stairs();
        int res = instance.getMaxSum(stream);
        System.out.println(res);
    }
}

