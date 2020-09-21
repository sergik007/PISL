package by.it.group473601.makar.lesson01;

/*
 * Даны целые числа 1<=n<=1E18 и 2<=m<=1E5,
 * необходимо найти остаток от деления n-го числа Фибоначчи на m.
 * время расчета должно быть не более 2 секунд
 */

import java.util.ArrayList;

public class FiboC {

    private long startTime = System.currentTimeMillis();

    private long time() {
        return System.currentTimeMillis() - startTime;
    }

    public static void main(String[] args) {
        FiboC fibo = new FiboC();
        int n = 10;
        int m = 2;
        System.out.printf("fasterC(%d)=%d \n\t time=%d \n\n", n, fibo.fasterC(n, m), fibo.time());
    }

    ArrayList<Long> getPeriodPizano(int m){
        ArrayList<Long> balances = new ArrayList();
        balances.add((long)0);
        balances.add((long)1);
        for(int i = 2; i < m * 6; i++){
            balances.add((balances.get(i - 1) + balances.get(i - 2)) % m);
            if(balances.get(i) == 1 && balances.get(i-1) == 0){
                break;
            }
        }
        return balances;
    }

    long fasterC(long n, int m) {
        //решение практически невозможно найти интуитивно
        //вам потребуется дополнительный поиск информации
        //см. период Пизано
        ArrayList<Long> balances = getPeriodPizano(m);
        long period = balances.size() - 2;
        int val = (int)(n % period);
        return balances.get(val);
    }


}

