package problems.arrays.sums;

public class SubarraySumWithLengthCondition {

    private static long maxSubarraySum(int[] nums, int k) {

        var incrementalTotals = new int[(nums.length % k) + 1][nums.length / k];
        var kthTotal = 0;
        for (int offset = 0; offset < (nums.length % k) + 1; offset++) {
            kthTotal = 0;
            for (int i = offset; i < nums.length; i++) {
                kthTotal = kthTotal + nums[i];
                if ((i - offset +1) % k == 0) {
                    incrementalTotals[offset][(i - offset + 1)/k - 1] = kthTotal;
                }
            }
        }
        // max: total, start index, end index
        var max = -999999999;
        for (int offset = 0; offset < (nums.length % k) + 1; offset++) {
            for (int x = 0; x < nums.length/k; x++) {
                if (x != 0) {
                    
                }
                if (incrementalTotals[offset][x] > max) {
                    max = incrementalTotals[offset][x];
                }
                if (x != 0 && incrementalTotals[offset][x] - incrementalTotals[offset][x-1] > max) {
                    max = incrementalTotals[offset][x] - incrementalTotals[offset][x-1];
                }
            }
        }
        return max;
    }

    public static void testMaxSubarraySum(int[] nums, int k) {
        System.out.println(maxSubarraySum(nums, k));
    }
}