package src.nowcoder.SwordOffer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * @author tsf
 * @date 21-03-25
 * @desp
 * 剑指 Offer 39. 数组中出现次数超过一半的数字
 * 数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。
 *
 * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
 *
 * 示例 1:
 *
 * 输入: [1, 2, 3, 2, 2, 2, 5, 4, 2]
 * 输出: 2
 *
 *
 * 限制：
 *
 * 1 <= 数组长度 <= 50000
 *
 * 注意：本题与主站 169 题相同：https://leetcode-cn.com/problems/majority-element/
 */

public class Offer39 {

    /* 哈希表统计法：遍历数组 nums ，用 HashMap 统计各数字的数量，即可找出众数。
    * 此方法时间和空间复杂度均为 O(N)。 */
    public int majorityElement(int[] nums) {

        HashMap<Integer, Integer> hashMap = new HashMap<>();
        int ret = 0;

        for (int i = 0; i < nums.length; i++) {
            if (hashMap.containsKey(nums[i])) {
                hashMap.put(nums[i], hashMap.get(nums[i]) + 1);
            } else {
                hashMap.put(nums[i], 1);
            }
        }

        int halfLen = nums.length >>> 1;
        for (Integer key: hashMap.keySet()) {
            if (hashMap.get(key) > halfLen) {
                ret = key;
                break;
            }
        }

        return ret;
    }

    /* 摩尔投票法：核心理念为票数正负抵消。此方法时间和空间复杂度分别为
    * O(N)O(N) 和 O(1)O(1) ，为本题的最佳解法。 */
    public int majorityElement2(int[] nums) {
        int votes = 0;
        int candidate = 0;

        for (int i = 0; i < nums.length; i++) {
            if (votes == 0) {  // 如果票数相消为0，选择下一数值
                candidate = nums[i];
                votes++;
            } else {
                if (candidate == nums[i]) {  // 如果是众数，就加1，否则减1
                    votes++;
                } else {
                    votes--;
                }
            }
        }

        /* 是否存在众数的检查 */
        int votesCheck = 0;
        for (int i = 0; i < nums.length; i++) {
            if (candidate == nums[i]) {
                votesCheck++;
            }
        }

        return votesCheck > nums.length/2? candidate : 0;
    }


    public static void main(String[] args) {

        Offer39 offer39 = new Offer39();

        int [] inputs = {1, 2, 3, 2, 2, 2, 5, 4, 2};
        int ret = offer39.majorityElement(inputs);
        System.out.println(ret);
        System.out.println("==========================");
        ret = offer39.majorityElement2(inputs);
        System.out.println(ret);
        System.out.println("==========================");

        int [] inputs2 = {2, 3, 3};
        int ret2 = offer39.majorityElement(inputs2);
        System.out.println(ret2);
        System.out.println("==========================");
        ret2 = offer39.majorityElement2(inputs2);
        System.out.println(ret2);
        System.out.println("==========================");
    }
}





