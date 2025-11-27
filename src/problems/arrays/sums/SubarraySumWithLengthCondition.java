package problems.arrays.sums;

import java.util.Arrays;

public class SubarraySumWithLengthCondition {

    public static long maxSubarraySum(int[] nums, int k) {
        Arrays.sort(nums);

        var total = 0;
        var kthTotal = 0;
        var index = nums.length - 1;
        var endIndex = nums.length % k;

        while (total + kthTotal >= total) {
            kthTotal += nums[index];
            if ((index - endIndex) % k == 0) {
                total += kthTotal;
                if (index == endIndex) {
                    break;
                }
            }
            index--;
        }
        return total;
    }
}
