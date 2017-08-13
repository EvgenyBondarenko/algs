public class LongestCommonSequence {

    static int commonChild(String s1, String s2){
        if (s1.isEmpty() || s2.isEmpty()) return 0;
        int[][] matrix = buildLcsMatrix(s1, s2);
//        printLcsMatrix(matrix);
        return matrix[s2.length()][s1.length()];
    }

    private static void printLcsMatrix(int[][] matrix) {
        for (int i = 0; i<matrix.length; i++) {
            for (int j = 0; j< matrix[0].length; j++) {
                System.out.print(matrix[i][j]);
            }
            System.out.println();
        }
    }

    private static int[][] buildLcsMatrix(String s1, String s2) {
        int[][] matrix = new int[s2.length() + 1][s1.length() + 1];
        // fill first row and first column with zeros
        for (int j = 0; j <= s1.length(); j++) {
            matrix[0][j] = 0;
        }
        for (int i = 1; i <= s2.length(); i++) {
            matrix[i][0] = 0;
        }
        for (int i = 1; i<=s2.length(); i++) {
            for (int j = 1; j<= s1.length(); j++) {
                if (s1.charAt(i-1) == s2.charAt(j-1)) matrix[i][j] = matrix[i-1][j-1] + 1;
                else matrix[i][j] = Integer.max(matrix[i-1][j], matrix[i][j-1]);
            }
        }
        return matrix;
    }

    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        String s1 = in.next();
//        String s2 = in.next();
        int result = commonChild("SHINCHAN", "NOHARAAA");
        System.out.println(result);
    }
}
