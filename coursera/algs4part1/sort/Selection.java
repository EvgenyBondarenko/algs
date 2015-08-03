import java.util.Arrays;

/**
 * Selection sort takes:
 * random array: N^2/2 comparisons, N/2 exchanges
 * sorted array: N^2/2 comparisons, 0 exchanges
 * reversed array: N^2/2 comparisons, N exchanges
 */
public class Selection {

    public static void main(String[] args) {
        int[] a = {3, 2, 1, 7, 5};
        sort(a);
        print(a);
    }

    public static void sort(int[] a) {
        for (int i = 0; i < a.length - 1; i++) {
            int min = min(a, i + 1);
            if (a[min] < a[i]) {
                exch(a, i, min);
            }
        }
    }

    private static void exch(int[] a, int i, int min) {
        int tmp = a[i];
        a[i] = a[min];
        a[min] = tmp;
    }

    private static int min(int[] a, int from) {
        int min = from;
        for (int i = from + 1; i < a.length; i++) {
            if (a[i] < a[min]) {
                min = i;
            }
        }
        return min;
    }

    private static void print(int[] a) {
        System.out.println(Arrays.toString(a));
    }
}
