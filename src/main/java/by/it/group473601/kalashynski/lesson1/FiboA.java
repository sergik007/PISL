package by.it.group473601.kalashynski.lesson1;

import java.math.BigInteger;

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


    private long calc(int n) {
        //здесь простейший вариант, в котором код совпадает с мат.определением чисел Фибоначчи
        //время O(2^n)
        if (n <= 3) {
            return n > 1 ? 1 : 0;
        } else {
            long prev = 0, next = 1, temp = 0;
            for(int i=0;i<=n;i++) {
                temp = next + prev;
                next = prev;
                prev = temp;
            }
            return next;
        }
    }


    BigInteger slowA(Integer n) {
        //рекурсия
        //здесь нужно реализовать вариант без ограничения на размер числа,
        //в котором код совпадает с мат.определением чисел Фибоначчи
        //время O(2^n)
        if (n <= 3) {
            return n > 1 ? BigInteger.ONE : BigInteger.ZERO;
        } else {
            return slowA(n - 1).add(slowA(n - 2));
        }
    }



}

