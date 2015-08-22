import java.util.Arrays;
import java.util.Random;

public class QuickSort {
    private static final Random random = new Random();

    public static void main(String[] args) {
        int[] a = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
//        int[] a = {0, 1};
        print(a);
        sort(a);
        print(a);
    }

    public static void sort(int[] a) {
        if (a.length < 2) return;
        shuffle(a);
        sort(a, 0, a.length - 1);
    }

    private static void shuffle(int[] a) {
        for (int i = 1; i < a.length; i++) {
            int newIndex = random.nextInt(i + 1);
            int tmp = a[newIndex];
            a[newIndex] = a[i];
            a[i] = tmp;
        }
        print(a);
    }

    private static void sort(int[] a, int lo, int hi) {
        if (lo == hi) {
            return;
        }
        int pivot = partition(a, lo, hi);
        sort(a, lo, pivot);
        sort(a, pivot + 1, hi);
    }

    private static int partition(int[] a, int lo, int hi) {
        int pivot = a[lo];
        int p = lo + 1;
        int q = hi;
        while (p <= q) {
            while (p <= hi && a[p] < pivot)
                p++;
            while (q >= lo + 1 && a[q] > pivot)
                q--;
            if (p < q)
                exch(a, p, q);
        }
        if (q != lo) {
            exch(a, lo, q);
            return q - 1;
        }
        return q;
    }

    private static void exch(int[] a, int p, int q) {
        int tmp = a[p];
        a[p] = a[q];
        a[q] = tmp;
    }

    private static void print(int[] a) {
        System.out.println(Arrays.toString(a));
    }
}
