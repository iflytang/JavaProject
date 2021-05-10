package src.nowcoder.SwordOffer;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author tsf
 * @date 21-03-25
 * @desp
 * 剑指 Offer 56 - I. 数组中数字出现的次数
 * 一个整型数组 nums 里除两个数字之外，其他数字都出现了两次。请写程序找出这两个只出现一次的数字。要求时间复杂度是O(n)，空间复杂度是O(1)。
 *
 * 示例 1：
 *
 * 输入：nums = [4,1,4,6]
 * 输出：[1,6] 或 [6,1]
 * 示例 2：
 *
 * 输入：nums = [1,2,10,4,1,4,3,3]
 * 输出：[2,10] 或 [10,2]
 *
 *
 * 限制：
 *
 * 2 <= nums.length <= 10000
 */

public class Offer56 {

    public int[] singleNumbers(int[] nums) {
        int n = 0, m = 1;

        for (int i = 0; i < nums.length; i++) {  // 得到x xor y
            n ^= nums[i];
        }

        while ((n & m) == 0) {  // x xor y肯定有个二进制位不同，通过这个区分成两个分离数组
            m = (m << 1);
        }

        int x = 0, y = 0;
        for (int i = 0; i < nums.length; i++) { // 分成两个数组，分别异或
            if ((nums[i] & m) == 0) {
                x ^= nums[i];
            } else {
                y ^= nums[i];
            }
        }

        return new int[] {x, y};
    }

    public static void main(String[] args) {

        Offer56 offer56 = new Offer56();

        int [] input = {1, 2, 10, 4, 1, 4, 3, 3};
        int[] ret = offer56.singleNumbers(input);
        System.out.println(Arrays.toString(ret));
        System.out.println("====================");
    }
}





