public class WeightedIndependentSet {

    public static void main(String[] args) {
        int[] input = {1, 2, 3, 4};
        System.out.println(optimalWIS(input));
    }

    private static int optimalWIS(int[] input) {
        int[] cache = new int[input.length];
        cache[0] = input[0];
        cache[1] = Math.max(input[1], input[0]);
        for (int i = 2; i < input.length; i++) {
            cache[i] = Math.max(cache[i - 1], cache[i - 2] + input[i]);
        }
        return cache[input.length - 1];
    }
}
