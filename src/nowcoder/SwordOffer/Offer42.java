package src.nowcoder.SwordOffer;

import java.util.Arrays;

/**
 * @author tsf
 * @date 21-03-25
 * @desp
 * 剑指 Offer 42. 连续子数组的最大和
 * 输入一个整型数组，数组中的一个或连续多个整数组成一个子数组。求所有子数组的和的最大值。
 *
 * 要求时间复杂度为O(n)。
 *
 *
 *
 * 示例1:
 *
 * 输入: nums = [-2,1,-3,4,-1,2,1,-5,4]
 * 输出: 6
 * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
 *
 *
 * 提示：
 *
 * 1 <= arr.length <= 10^5
 * -100 <= arr[i] <= 100
 * 注意：本题与主站 53 题相同：https://leetcode-cn.com/problems/maximum-subarray/
 */

public class Offer42 {

    /* 状态定义： 设动态规划列表 dpdp ，dp[i]dp[i] 代表以元素 nums[i]nums[i] 为结尾的连续子数组最大和。
    * 转移方程： 若 dp[i−1]≤0 ，说明 dp[i−1] 对 dp[i] 产生负贡献，即 dp[i-1] + nums[i]还不如 nums[i]本身大。
    * 1. dp[i−1]>0 时：执行 dp[i] = dp[i-1] + nums[i];
    * 2. dp[i−1]≤0 时：执行 dp[i] = nums[i]
     * */
    public int maxSubArray(int[] nums) {  // 会修改原数组
        if (nums.length == 0) {
            return 0;
        }

        int res = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i - 1] > 0) {
                nums[i] += nums[i - 1];
            } else {
                nums[i] = nums[i];
            }

            res = Math.max(nums[i], res);
        }

        return res;
    }

    public int maxSubArray2(int[] nums) {  // 不会修改原数组
        if (nums.length == 0) {
            return 0;
        }

        int pre = 0;  // dp[i-1]
        int cur = 0;  // dp[i]
        int res = nums[0];

        for (int i = 0; i < nums.length; i++) {
            if (pre > 0) {  // 状态转移方程
                cur += nums[i];
            } else {
                cur = nums[i];
            }

            pre = cur; // dp[i-1]移到下一状态

            res = Math.max(res, cur);
        }

        return res;
    }

    public static void main(String[] args) {

        Offer42 offer42 = new Offer42();

//        int [] inputs = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        int [] inputs = {-2};

        System.out.println(Arrays.toString(inputs));
        System.out.println("==========================");

        int ret = offer42.maxSubArray(inputs);
        System.out.println(ret);
        System.out.println("==========================");

        int [] inputs2 = {-2};
        ret = offer42.maxSubArray2(inputs2);
        System.out.println(ret);
        System.out.println("==========================");

    }
}





