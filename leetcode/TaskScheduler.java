import java.util.*;

// https://leetcode.com/problems/task-scheduler/
public class TaskScheduler {

    public static void main(String[] args) {
        System.out.println(new Solution().leastInterval(
//                new char[] {'A', 'A', 'A', 'A', 'A', 'A', 'B', 'C', 'D', 'E', 'F', 'G'}, 2));
                new char[] {'A', 'B', 'A', 'B', 'A', 'B'}, 2));
    }


    static class Solution {
        public int leastInterval(char[] tasks, int n) {
            int[] map = new int[26];
            for (char task : tasks)
                map[task - 'A']++;
            Arrays.sort(map);
            int size = 0;
            for (int i = 25; map[i] > 0; i--) size++;
            int res = 0;
            while (size > 0) {
                int i = 0;
                while (i <= n && size > 0) {
                    if (i < 26 && map[25 - i] > 0) {
                        map[25 - i]--;
                        if (map[25 - i] == 0) size--;
                    }
                    res++;
                    i++;
                }
                Arrays.sort(map);
            }
            return res;
        }
    }
}
