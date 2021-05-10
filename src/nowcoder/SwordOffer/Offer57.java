package src.nowcoder.SwordOffer;

import java.util.Arrays;

/**
 * @author tsf
 * @date 21-03-25
 * @desp
 * 剑指 Offer 57. 和为s的两个数字
 * 输入一个递增排序的数组和一个数字s，在数组中查找两个数，使得它们的和正好是s。如果有多对数字的和等于s，则输出任意一对即可。
 *
 * 示例 1：
 *
 * 输入：nums = [2,7,11,15], target = 9
 * 输出：[2,7] 或者 [7,2]
 * 示例 2：
 *
 * 输入：nums = [10,26,30,31,47,60], target = 40
 * 输出：[10,30] 或者 [30,10]
 *
 *
 * 限制：
 *
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^6
 */

public class Offer57 {

    public int[] twoSum(int[] nums, int target) { // 对撞双指针法
        // 边界条件
        if (nums.length == 0) {
            return new int[0];
        }

        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            int sum = nums[left] + nums[right];

            if (sum > target) { // 大数，右到左移位
                right--;
            } else if (sum < target) { // 小数，左到右移位
                left++;
            } else {
                return new int [] {nums[left], nums[right]};  // 找到一个即可
            }
        }

        return new int[0];
    }


    public static void main(String[] args) {

        Offer57 offer57 = new Offer57();

        int [] input = {10, 26, 30, 31, 47, 60};
        int target = 80;
        int [] ret = offer57.twoSum(input, target);
        System.out.println(Arrays.toString(ret));
        System.out.println("====================");
    }
}





