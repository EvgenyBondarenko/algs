import java.util.Arrays;

public class Merge {

    public static void main(String[] args) {
        int[] a = {3, 0, 2, 1, 7, 4, 5, 9, 8};
        sort(a);
        print(a);
    }

    public static void sort(int[] a) {
        if (a.length < 2) return;
        int[] aux = new int[a.length];
        sort(a, aux, 0, a.length - 1);
    }

    private static void sort(int[] a, int[] aux, int lo, int hi) {
        if (lo == hi) {
            return;
        }
        int mid = lo + (hi - lo) / 2;
        sort(a, aux, lo, mid);
        sort(a, aux, mid + 1, hi);
        merge(a, aux, lo, mid, hi);
    }

    private static void merge(int[] a, int[] aux, int lo, int mid, int hi) {
        int p = lo;
        int q = mid + 1;
        System.arraycopy(a, lo, aux, lo, hi - lo + 1);
        for (int i = lo; i <= hi; i++) {
            if (p > mid) {
                a[i] = aux[q++];
                continue;
            }
            if (q > hi) {
                a[i] = aux[p++];
                continue;
            }
            if (aux[p] < aux[q])
                a[i] = aux[p++];
            else
                a[i] = aux[q++];
        }
    }

    private static void print(int[] a) {
        System.out.println(Arrays.toString(a));
    }
}
