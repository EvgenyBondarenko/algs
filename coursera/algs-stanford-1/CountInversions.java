public class CountInversions {

    public static int countInversions(int[] a) {
        return countInversionsAndSort(a, 0, a.length - 1, new int[a.length]);
    }

    private static int countSplitInversionsAndMerge(int[] a, int p, int mid, int q, int[] aux) {
        int i = p;
        int j = mid + 1;
        int count = 0;
        for (int k = p; k <= q; k++) {
            if (j > q)
                aux[k] = a[i++];
            else if (i > mid || a[j] < a[i]) {
                count += mid - i + 1;
                aux[k] = a[j++];
            } else
                aux[k] = a[i++];
        }
        System.arraycopy(aux, p, a, p, q + 1 - p);
        return count;
    }

    private static int countInversionsAndSort(int[] a, int p, int q, int[] aux) {
        if (p == q)
            return 0;
        int mid = p + (q - p) / 2;
        return countInversionsAndSort(a, p, mid, aux)
                + countInversionsAndSort(a, mid + 1, q, aux)
                + countSplitInversionsAndMerge(a, p, mid, q, aux);
    }

    public static void main(String[] args) {
        int[] a = {1, 3, 5, 2, 4, 6};
//        int[] a = {6,5,4,3,2,1};
//        int[] a = {1,2,3,4,5,6};
        System.out.println(CountInversions.countInversions(a));
    }
}
