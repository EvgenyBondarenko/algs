import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class ThreeSum {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++){
            if (i > 0 && nums[i] == nums[i-1]) {
                continue;
            }
            int L = i+1;
            int R = nums.length -1;
            while (L < R) {
                int sum = nums[i] + nums[L] + nums[R];
                if (sum > 0) {
                    R--;
                } else if (sum < 0) {
                    L++;
                } else {
                    // we found it!
                    res.add(List.of(nums[i], nums[L], nums[R]));
                    L++;
                    while(nums[L] == nums[L-1] && L < R) {
                        L++;
                    }
                }
            }
        }
        return res;
    }
}