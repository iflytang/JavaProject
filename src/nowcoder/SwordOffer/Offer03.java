package src.nowcoder.SwordOffer;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author tsf
 * @date 21-03-25
 * @desp 剑指 Offer 03. 数组中重复的数字。
 *
 * 在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。数组中某些数字是重复的，
 * 但不知道有几个数字重复了，也不知道每个数字重复了几次。请找出数组中任意一个重复的数字。
 *
 * 示例 1：
 *
 * 输入：
 * [2, 3, 1, 0, 2, 5, 3]
 * 输出：2 或 3
 *
 * 限制：
 *
 * 2 <= n <= 100000
 *
 * 链接：https://leetcode-cn.com/problems/shu-zu-zhong-zhong-fu-de-shu-zi-lcof
 */

public class Offer03 {

    /* 第一次刷题：2021/03/25 
     * 思路：升序排序后，比较相邻的数值是否相等。
     */
    public int findRepeatNumber(int[] nums) {
        int[] sortedNums = Arrays.stream(nums).sorted().toArray();

        int repeatedNum = -1;

        for (int i = 0; i < nums.length-1; i++) {
            if (sortedNums[i] == sortedNums[i+1]) {
//                System.out.println(sortedNums[i]);
                repeatedNum = sortedNums[i];
                return repeatedNum;
//                break;
            }
        }

        return 0;
    }

    public static void main(String[] args) {
        int[] arr = {2, 3, 1, 0, 2, 5, 3};

        Offer03 offer03 = new Offer03();
        offer03.findRepeatNumber(arr);
        
    }
}
