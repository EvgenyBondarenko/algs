import java.util.ArrayList;
import java.util.List;

public class SpiralMatrix {

    public static void main(String[] args) {
        int[][] m = new int[4][4];
        m[0] = new int[] {1,2,3,4};
        m[1] = new int[] {5,6,7,8};
        m[2] = new int[] {9,10,11,12};
        m[3] = new int[] {13,14,15,16};
        new SpiralMatrix().spiralOrder(m);
    }

    public List<Integer> spiralOrder(int[][] matrix) {
        int iMin = 1, jMin = 0;
        int iMax = matrix.length-1, jMax = matrix[0].length-1;
        int i = 0, j = 0;
        List<Integer> res = new ArrayList<>(matrix.length * matrix.length);
        res.add(matrix[0][0]);
        int size = matrix.length * matrix[0].length;
        while (res.size() < size) {
            // go right
            while (j < jMax && res.size() < size) {
                j++;
                res.add(matrix[i][j]);
            }
            jMax--;
            // go down
            while (i < iMax && res.size() < size) {
                i++;
                res.add(matrix[i][j]);
            }
            iMax--;
            // go left
            while (j > jMin && res.size() < size) {
                j--;
                res.add(matrix[i][j]);
            }
            jMin++;
            // go up
            while (i > iMin && res.size() < size) {
                i--;
                res.add(matrix[i][j]);
            }
            iMin++;
        }
        return res;
    }

}
