import java.util.*;

/**
 * Buggy one
 */
public class LargestRectangleInHistogramNLogN {
    public static void main(String[] args) {
        int[] height = {2, 1, 5, 6, 2, 3};
        System.out.println(new LargestRectangleInHistogramNLogN().new Solution().largestRectangleArea(height));
    }

    public class Solution {
        public int largestRectangleArea(int[] height) {
            if (height == null || height.length == 0) return 0;
            PriorityQueue<Integer> pq = new PriorityQueue<>();
            Map<Integer, Queue<Integer>> heightToIdxs = new HashMap<>();
            for (int i = 0; i < height.length; i++) {
                pq.add(height[i]);
                appendMap(heightToIdxs, height[i], i);
            }
            boolean[] processed = new boolean[height.length];
            return maxArea(height, 0, height.length - 1, pq, heightToIdxs, processed);
        }

        private void appendMap(Map<Integer, Queue<Integer>> heightToIdxs, int height, int idx) {
            Queue<Integer> idxs = heightToIdxs.get(height);
            if (idxs == null) {
                idxs = new LinkedList<>();
                heightToIdxs.put(height, idxs);
            }
            idxs.add(idx);
        }

        private int maxArea(int[] h, int p, int q, PriorityQueue<Integer> pq, Map<Integer, Queue<Integer>> heightToIdxs, boolean[] processed) {
            if (p > q) return 0;
            if (p == q) {
                processed[p] = true;
                return h[p];
            }
            int lowestIdx = getLowestIdx(pq, heightToIdxs, processed);
            return max(maxArea(h, p, lowestIdx - 1, pq, heightToIdxs, processed),
                    h[lowestIdx] * (q - p + 1),
                    maxArea(h, lowestIdx + 1, q, pq, heightToIdxs, processed));
        }

        private int getLowestIdx(PriorityQueue<Integer> pq, Map<Integer, Queue<Integer>> heightToIdxs, boolean[] processed) {
            int lowest;
            do {
                lowest = heightToIdxs.get(pq.poll()).poll();
            } while (processed[lowest]);
            return lowest;
        }

        private int max(int x, int y, int z) {
            return Math.max(Math.max(x, y), z);
        }
    }
}
