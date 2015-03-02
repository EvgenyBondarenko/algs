import java.util.Scanner;

/**
 * The Utopian Tree goes through 2 cycles of growth every year. The first growth cycle occurs during the spring, when it doubles in height.
 * The second growth cycle occurs during the summer, when its height increases by 1 meter.
 * Now, a new Utopian Tree sapling is planted at the onset of spring. Its height is 1 meter. Can you find the height of the tree after N growth cycles?
 */
public class UtopianTree {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int numTestCases = in.nextInt();
        for (int i = 1; i <= numTestCases; i++) {
            System.out.println(calcHeightAfterNCycles(in.nextInt()));
        }
    }

    private static int calcHeightAfterNCycles(int numCycles) {
        int height = 1;
        for (int i = 1; i <= numCycles; i++) {
            if ((i % 2) == 1) {
                height *= 2;
            } else {
                height += 1;
            }
        }
        return height;
    }
}
