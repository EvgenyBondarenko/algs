import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

// https://leetcode.com/problems/permutations/
public class Permutations {

    public static void main(String[] args) {
        System.out.println(new Solution().permute(new int[] {1, 2, 3}));
    }

    // Recursive
    static class Solution {
        public List<List<Integer>> permute(int[] nums) {
            if (nums == null || nums.length == 0) return Collections.emptyList();
            List<List<Integer>> res = new ArrayList<>();
            permute(nums, 0, res);
            return res;
        }

        private void permute(int[] nums, int start, List<List<Integer>> res) {
            if (start == nums.length - 1) {
                List<Integer> list = Arrays.stream(nums).boxed().collect(Collectors.toList());
                res.add(list);
            } else {
                for (int i = start; i < nums.length; i++) {
                    swap(nums, start, i);
                    permute(nums, start + 1, res);
                    swap(nums, start, i);
                }
            }

        }

        private void swap(int[] nums, int a, int b) {
            int temp = nums[a];
            nums[a] = nums[b];
            nums[b] = temp;
        }
    }

    // Loop based
    static class Solution1 {
        public List<List<Integer>> permute(int[] nums) {
            if (nums == null || nums.length == 0) return Collections.emptyList();
            List<List<Integer>> res = new ArrayList<>();
            res.add(new ArrayList<>());
            for (int num : nums) {
                List<List<Integer>> resWithCurrent = new ArrayList<>();
                for (List<Integer> list : res) {
                    for(int i = 0; i < list.size() + 1; i++) {
                        List<Integer> listWithCurrent = new ArrayList<>(list.size() + 1);
                        listWithCurrent.addAll(list.subList(0, i));
                        listWithCurrent.add(num);
                        listWithCurrent.addAll(list.subList(i, list.size()));
                        resWithCurrent.add(listWithCurrent);
                    }
                }
                res = resWithCurrent;
            }
            return res;
        }
    }
}
