import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class LargestRectangleInHistogramNoArray {
    public static void main(String[] args) {
        int[] height = {2, 1, 5, 6, 2, 3};
        System.out.println(new LargestRectangleInHistogramNoArray().new Solution().largestRectangleArea(height));
    }

    public class Solution {
        public int largestRectangleArea(int[] height) {
            int maxArea = 0;
            Map<Integer, Integer> cur = new HashMap<>();
            for (int bar : height) {
                Iterator<Map.Entry<Integer, Integer>> iterator = cur.entrySet().iterator();
                int removedCount = 0;
                while (iterator.hasNext()) {
                    Map.Entry<Integer, Integer> entry = iterator.next();
                    if (bar < entry.getKey()) {
                        iterator.remove();
                        removedCount++;
                        maxArea = Math.max(maxArea, entry.getValue());
                    } else {
                        cur.put(entry.getKey(), entry.getValue() + entry.getKey());
                    }
                }
                if (cur.get(bar) == null) {
                    cur.put(bar, bar * (removedCount + 1));
                }
            }
            for (Integer value : cur.values()) {
                maxArea = Math.max(maxArea, value);
            }
            return maxArea;
        }
    }
}
