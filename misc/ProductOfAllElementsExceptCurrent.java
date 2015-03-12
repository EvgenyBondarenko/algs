import java.util.Arrays;

/**
 * Given an array replace all its elements with the product of
 * all array's elements except the current one
 */
public class ProductOfAllElementsExceptCurrent {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(replaceWithProductON(new int[]{1, 2, 3, 4, 5})));
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
}
