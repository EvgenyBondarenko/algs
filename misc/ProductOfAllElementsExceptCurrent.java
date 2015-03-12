import java.util.Arrays;

/**
 * Given an array replace all its elements with the product of
 * all array's elements except the current one
 */
public class ProductOfAllElementsExceptCurrent {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(replaceWithProductON(new int[]{1, 2, 3, 4, 5})));
        System.out.println(Arrays.toString(replaceWithProductONLogN(new int[]{1, 2, 3, 4, 5})));
    }

    private static int[] replaceWithProductON(int[] a) {
        int[] temp1 = new int[a.length];
        temp1[0] = 1;
        for (int i = 0; i < a.length - 1; i++) {
            temp1[i + 1] = temp1[i] * a[i];
        }
        int[] temp2 = new int[a.length];
        temp2[a.length - 1] = 1;
        for (int i = a.length - 1; i > 0; i--) {
            temp2[i - 1] = temp2[i] * a[i];
        }
        for (int i = 0; i < a.length; i++) {
            a[i] = temp1[i] * temp2[i];
        }
        return a;
    }

    private static int[] replaceWithProductONLogN(int[] a) {
        solve(a, 0, a.length - 1, 1);
        return a;
    }

    private static void solve(int[] a, int p, int q, int otherHalfProduct) {
        if (p == q) {
            a[p] = otherHalfProduct;
            return;
        }
        int k = (p + q) / 2;
        int leftProduct = 1;
        for (int i = p; i <= k; i++) {
            leftProduct *= a[i];
        }
        int rightProduct = 1;
        for (int i = k + 1; i <= q; i++) {
            rightProduct *= a[i];
        }
        solve(a, p, k, rightProduct * otherHalfProduct);
        solve(a, k + 1, q, leftProduct * otherHalfProduct);
    }
}
