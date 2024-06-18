public class RotateImage {

    public static void main(String[] args) {
        int[][] m = new int[3][3];
        m[0] = new int[] {1,2,3};
        m[1] = new int[] {4,5,6};
        m[2] = new int[] {7,8,9};
        new RotateImage().rotate(m);
        System.out.println("done");
    }
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n/2; i++) {
            for (int j = i; j < n-1-i; j++) {
                // top to right
                int newI = j;
                int newJ = n - 1 - i;
                int write = matrix[newI][newJ];
                int store = write;
                matrix[newI][newJ] = matrix[i][j];

                // right to bottom
                newI = n - 1 - i;
                newJ = n - 1 - j;
                store = matrix[newI][newJ];
                matrix[newI][newJ] = write;
                write = store;

                // bottom to left
                newI = n - 1 -j;
                newJ = i;
                store = matrix[newI][newJ];
                matrix[newI][newJ] = write;
                write = store;

                // left to top
                matrix[i][j] = write;
            }
        }
    }
}
