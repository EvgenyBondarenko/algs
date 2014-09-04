import java.util.HashSet;
import java.util.Set;

public class SingleNumber {

    public static void main(String[] args) {
        int[] A = {1, 2, 3, 1, 3};
        System.out.println(new SingleNumber().new Solution().singleNumber(A));
    }

    public class Solution {
        public int singleNumber(int[] A) {
            Set<Integer> uniq = new HashSet<Integer>(A.length / 2 + 1);
            for (int n : A) {
                if (uniq.contains(n)) {
                    uniq.remove(n);
                } else {
                    uniq.add(n);
                }
            }
            return uniq.iterator().next();
        }
    }
}
