package src.nowcoder.SwordOffer;

import java.util.Arrays;

/**
 * @author tsf
 * @date 21-03-25
 * @desp
 * 剑指 Offer 53 - I. 在排序数组中查找数字 I
 * 统计一个数字在排序数组中出现的次数。
 *
 * 示例 1:
 *
 * 输入: nums = [5,7,7,8,8,10], target = 8
 * 输出: 2
 * 示例 2:
 *
 * 输入: nums = [5,7,7,8,8,10], target = 6
 * 输出: 0
 *
 *
 * 限制：
 *
 * 0 <= 数组长度 <= 50000
 *
 * 注意：本题与主站 34 题相同（仅返回值不同）：
 * https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array/
 */

public class Offer53_1 {

    public int search(int[] nums, int target) {
//        Arrays.binarySearch(nums, target);

        int left = 0;
        int right = nums.length - 1;

        int mid = -1;

        int targetLoc = -1;

        while (left <= right) {
            mid = left + (right - left) / 2;

            if (nums[mid] < target) {
                left = mid + 1;
                System.out.println("left: " + left + ", right: " + right);
            } else if (nums[mid] > target) {
                right = mid - 1;
                System.out.println("right: " + right + ", left: " + left);
            } else {
                targetLoc = mid;
                System.out.println("mid: " + mid);
                break;
            }
        }

        if (targetLoc < 0) {
            return 0;
        }
        System.out.println(targetLoc);

        // counts
        int count = 1;  // nums[mid]
        int i = targetLoc - 1;
        while (i >= 0 && nums[i] == nums[targetLoc]) {
            count++;
            i--;
        }

        int j = targetLoc + 1;
        while (j <= right && nums[j] == nums[targetLoc]) {
            count++;
            j++;
        }

        return count;
    }

    public int search2(int[] nums, int target) {
        if (nums.length == 0) {
            return 0;
        }

        int left = 0;
        int right = nums.length - 1;

        // find right bound
        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] <= target) {
                left = mid + 1;
                System.out.println("left: " + left + ", right: " + right);
            } else {
                right = mid - 1;
                System.out.println("right: " + right + ", left: " + left);
            }
        }

        // 若数组中无 target ，则提前返回
        if (right >= 0 && nums[right] != target) {
            return 0;
        }
        System.out.println(right);

        int rightBound = left;

        left = 0;
        right = nums.length - 1;

        // find left bound
        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] < target) {
                left = mid + 1;
                System.out.println("left: " + left + ", right: " + right);
            } else {
                right = mid - 1;
                System.out.println("right: " + right + ", left: " + left);
            }
        }

        int leftBound = right;
        System.out.println(left);

        return rightBound - leftBound - 1;
    }


    public static void main(String[] args) {

        Offer53_1 offer53_1 = new Offer53_1();

        int [] nums = {5, 7, 7, 8, 8, 8, 8, 10};
//        int [] nums = {2, 2, 2, 2};
        int target = 8;
        int ret = offer53_1.search(nums, target);
        System.out.println(ret);
        System.out.println("====================");
//        System.out.println(Arrays.binarySearch(nums, target));

        ret = offer53_1.search2(nums, target);
        System.out.println(ret);
        System.out.println("====================");
//        System.out.println(Arrays.binarySearch(nums, target));

    }
}





