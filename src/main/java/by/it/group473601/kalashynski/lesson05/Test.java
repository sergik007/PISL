package by.it.group473601.kalashynski.lesson05;

public class Test {
    static void OptimizedQuickSort(int[] Arr, int Left, int Right) {
        int Pivot;
        Pivot = Q_Sort(Arr, Left, Right);
        if (Left < Pivot - 1) {
            OptimizedQuickSort(Arr, Left, Pivot - 1);
        }
        if (Right > Pivot + 1) {
            OptimizedQuickSort(Arr, Pivot + 1, Right);
        }
    }

    static int Q_Sort(int[] Arr, int Left, int Right) {
        int Pivot;
        Pivot = Arr[Left];
        while (Left < Right) {
            while ((Arr[Right] >= Pivot) && (Left < Right)) {
                Right--;
            }
            if (Left != Right) {
                Arr[Left] = Arr[Right];
                Left++;
            }
            while ((Arr[Left] <= Pivot) && (Left < Right)) {
                Left++;
            }
            if (Left != Right) {
                Arr[Right] = Arr[Left];
                Right--;
            }
        }
        Arr[Left] = Pivot;
        return Left;
    }

    public static void main(String[] args) {
        int a[] = {8, 5, 9, 4, 7, 0, 2, 1};
        OptimizedQuickSort(a, 0, a.length - 1);
    }
}
