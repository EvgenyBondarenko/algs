import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NumberOfWaysToGiveChange {
    private static int recursiveCallCount;
    // Recursive solution
    public static int numberOfWaysToGiveChangeRecursive(int sum, List<Integer> coins) {
        recursiveCallCount++;
        if (sum < 0 || coins.isEmpty()) return 0;
        if (sum == 0) return 1;
        return numberOfWaysToGiveChangeRecursive(sum - coins.get(0), coins)
             + numberOfWaysToGiveChangeRecursive(sum, coins.subList(1, coins.size()));
    }

    private static Map<Pair, Integer> cache = new HashMap<>();

    // Dynamic programming solution
    public static int numberOfWaysToGiveChangeCached(int sum, List<Integer> coins) {
        recursiveCallCount++;
        Integer cached = cache.get(new Pair(sum, coins));
        if (cached != null) return cached;

        if (sum < 0 || coins.isEmpty()) return 0;
        if (sum == 0) return 1;
        int result = numberOfWaysToGiveChangeCached(sum - coins.get(0), coins)
                   + numberOfWaysToGiveChangeCached(sum, coins.subList(1, coins.size()));
        cache.put(new Pair(sum, coins), result);
        return result;
    }

    private static class Pair {
        private final int sum;
        private final List<Integer> coins;

        private Pair(int sum, List<Integer> coins) {
            this.sum = sum;
            this.coins = coins;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Pair pair = (Pair) o;

            return sum == pair.sum && coins.equals(pair.coins);
        }

        @Override
        public int hashCode() {
            int result = sum;
            result = 31 * result + coins.hashCode();
            return result;
        }
    }

    public static void main(String[] args) {
        int sum = 127;
        List<Integer> coins = Arrays.asList(1, 2, 3);
        recursiveCallCount = 0;
        System.out.println(numberOfWaysToGiveChangeRecursive(sum, coins));
        System.out.println("recursive call count: " + recursiveCallCount);
        recursiveCallCount = 0;
        System.out.println(numberOfWaysToGiveChangeCached(sum, coins));
        System.out.println("recursive call count: " + recursiveCallCount);
    }
}
