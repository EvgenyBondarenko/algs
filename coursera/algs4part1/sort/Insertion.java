import java.util.Arrays;

/**
 * Selection sort takes:
 * random array: N^2/4 comparisons, N^2/4 exchanges
 * sorted array: N comparisons, 0 exchanges
 * reversed array: N^2/2 comparisons, N^2/2 exchanges
 */
public class Insertion {

    public static void main(String[] args) {
        int[] a = {3, 2, 1, 7, 5};
        sort(a);
        print(a);
    }

    public static void sort(int[] a) {
        for (int i = 1; i < a.length; i++) {
            for (int j = i; j > 0 && a[j] < a[j - 1]; j--) {
                exch(a, j, j - 1);
            }
        }
    }

    private static void exch(int[] a, int i, int min) {
        int tmp = a[i];
        a[i] = a[min];
        a[min] = tmp;
    }

    private static void print(int[] a) {
        System.out.println(Arrays.toString(a));
    }
}