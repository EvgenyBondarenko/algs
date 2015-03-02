import java.util.Arrays;
import java.util.Scanner;

/**
 * Given a list of N integers, your task is to select K integers from the list such that its unfairness is minimized.
 * if (x1,x2,x3,…,xk) are K numbers selected from the list N, the unfairness is defined as max(x1,x2,…,xk)−min(x1,x2,…,xk)
 * where max denotes the largest integer among the elements of K, and min denotes the smallest integer among the elements of K.
 */
public class MinimizeUnfairness {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int K = in.nextInt();
        int[] elements = new int[N];
        for (int i = 0; i < N; i++) {
            elements[i] = in.nextInt();
        }
        System.out.println(minimizeUnfairness(elements, K));
    }

    private static int minimizeUnfairness(int[] elements, int K) {
        Arrays.sort(elements);
        int minUnfairness = Integer.MAX_VALUE;
        for (int i = 0; i <= elements.length - K; i++) {
            minUnfairness = Math.min(elements[i + K - 1] - elements[i], minUnfairness);
        }
        return minUnfairness;
    }
}
