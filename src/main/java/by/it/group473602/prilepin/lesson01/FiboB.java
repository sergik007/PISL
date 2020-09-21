package by.it.group473602.prilepin.lesson01;

import java.math.BigInteger;
import java.util.Vector;

/*
 * Вам необходимо выполнить способ вычисления чисел Фибоначчи с вспомогательным массивом
 * без ограничений на размер результата (BigInteger)
 */

public class FiboB {

    private long startTime = System.currentTimeMillis();

    private long time() {
        return System.currentTimeMillis() - startTime;
    }

    public static void main(String[] args) {

        //вычисление чисел простым быстрым методом
        FiboB fibo = new FiboB();
        int n = 55555;
        System.out.printf("fastB(%d)=%d \n\t time=%d \n\n", n, fibo.fastB(n), fibo.time());
    }

    BigInteger fastB(Integer n) {
        //здесь нужно реализовать вариант с временем O(n) и памятью O(n)

        if (n <= 2) return BigInteger.ONE;
        BigInteger[] dp = new BigInteger[n+1];
        dp[0]=BigInteger.ZERO;
        dp[1]=BigInteger.ONE;
        dp[2]=BigInteger.ONE;
        for (int i = 3; i <= n; i++)
        {
            dp[i] = dp[i-1].add(dp[i-2]);
        }
        return dp[n];
    }

}

