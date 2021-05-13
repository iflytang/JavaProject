package src.nowcoder.SwordOffer;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author tsf
 * @date 21-03-25
 * @desp
 * 剑指 Offer 61. 扑克牌中的顺子
 * 从扑克牌中随机抽5张牌，判断是不是一个顺子，即这5张牌是不是连续的。
 * 2～10为数字本身，A为1，J为11，Q为12，K为13，而大、小王为 0 ，可以看成任意数字。A 不能视为 14。
 *
 * 示例 1:
 *
 * 输入: [1,2,3,4,5]
 * 输出: True
 *
 *
 * 示例 2:
 *
 * 输入: [0,0,1,2,5]
 * 输出: True
 *
 *
 * 限制：
 *
 * 数组长度为 5
 *
 * 数组的数取值为 [0, 13] .
 */

public class Offer61 {

    // set + 遍历
    public boolean isStraight(int[] nums) {
        int max = 0, min = 14;  // 最大牌和最小牌
        HashSet<Integer> set = new HashSet<>(); // 重复牌

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {  // 不处理0
                continue;
            }

            max = Math.max(max, nums[i]);
            min = Math.min(min, nums[i]);

            if (set.contains(nums[i])) { // 如果非0重复，则返回false
                return false;
            }

            set.add(nums[i]);
        }

        return (max - min) < 5;
    }

    // 排序 + 遍历
    public boolean isStraight2(int[] nums) {
        int joker = 0;  // 大小王的个数
        
        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] == 0) {
                joker++;
                continue;
            }

            if (nums[i] == nums[i+1]) { // 重复元素
                return false;
            }
        }

        System.out.println(joker);
        
        return nums[nums.length - 1] - nums[joker] < 5;
    }

    public static void main(String[] args) {

        Offer61 offer61 = new Offer61();

        int [] nums = {1, 2, 3, 4, 5};
        boolean ret = offer61.isStraight(nums);
        System.out.println(ret);
        ret = offer61.isStraight2(nums);
        System.out.println(ret);
        System.out.println("==========================");

        int [] nums2 = {0, 0, 2, 2, 5};
        ret = offer61.isStraight(nums2);
        System.out.println(ret);
        ret = offer61.isStraight2(nums2);
        System.out.println(ret);
        System.out.println("==========================");
    }
}





