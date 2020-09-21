package by.it.group473602.prilepin.lesson01;

import java.math.BigInteger;
import java.util.Vector;

/*
 * Вам необходимо выполнить рекурсивный способ вычисления чисел Фибоначчи
 */

public class FiboA {

    private long startTime = System.currentTimeMillis();

    private long time() {
        return System.currentTimeMillis() - startTime;
    }

    public static void main(String[] args) {
        FiboA fibo = new FiboA();
        int n = 33;
        System.out.printf("calc(%d)=%d \n\t time=%d \n\n", n, fibo.calc(n), fibo.time());

        //вычисление чисел фибоначчи медленным методом (рекурсией)
        fibo = new FiboA();
        n = 33;
        System.out.printf("slowA(%d)=%d \n\t time=%d \n\n", n, fibo.slowA(n), fibo.time());
    }


    private int calc(int n) {
        //здесь простейший вариант, в котором код совпадает с мат.определением чисел Фибоначчи
        //время O(2^n)
     int x = 1;
        int y=0;
        int ans=0;
        for (int i = 1; i<=n; i++){//refactor
            ans = x+y;
            x = y;
            y = ans;
        }
        return ans;
    }


    BigInteger slowA(Integer n) {
        //рекурсия
        //здесь нужно реализовать вариант без ограничения на размер числа,
        //в котором код совпадает с мат.определением чисел Фибоначчи
        //время O(2^n)

        if (n==0) return BigInteger.ZERO;
        else{
            if ((n == -1) || (n == 1)) { return BigInteger.ONE;}
            else{
                if (n>0){
                    return slowA(n-1).add(slowA(n-2));
                }
                else {
                    return slowA(n+2).add(slowA(n+1));
                }
            }
        }
    }
}

