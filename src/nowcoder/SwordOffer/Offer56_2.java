package src.nowcoder.SwordOffer;

import java.util.Arrays;

/**
 * @author tsf
 * @date 21-03-25
 * @desp
 * 剑指 Offer 56 - II. 数组中数字出现的次数 II
 * 在一个数组 nums 中除一个数字只出现一次之外，其他数字都出现了三次。请找出那个只出现一次的数字。
 *
 * 示例 1：
 *
 * 输入：nums = [3,4,3,3]
 * 输出：4
 * 示例 2：
 *
 * 输入：nums = [9,1,7,9,7,9,7]
 * 输出：1
 *
 *
 * 限制：
 *
 * 1 <= nums.length <= 10000
 * 1 <= nums[i] < 2^31
 *
 */

public class Offer56_2 {

    public int singleNumber(int[] nums) {  // 剑指offer答案，效率不高
        int [] bits = new int[32];

        for (int i = 0; i < nums.length; i++) {  // 统计二进制比特位的个数
            int bitmask = 1;

            for (int j = 0; j < 32; j++) {
                if ((nums[i] & bitmask) != 0) {
                    bits[j]++;
                }
                bitmask <<= 1;
            }
        }

        System.out.println(Arrays.toString(bits));

        int res = 0;
        int bitmask = 1;
        for (int i = 0; i < bits.length; i++) {  // 除余则表示该位有值

            if (bits[i] % 3 != 0) {
                res += bitmask;
            }

            bitmask <<= 1;
        }

        return res;
    }

    public int singleNumber2(int[] nums) { // 有限状态自动机
        int ones = 0, twos = 0;
        for(int num : nums){
            ones = ones ^ num & ~twos;
            twos = twos ^ num & ~ones;
        }
        return ones;
    }

    public static void main(String[] args) {

        Offer56_2 offer56 = new Offer56_2();

//        int [] input = {9, 5, 7, 9, 7, 9, 7};
        int [] input = {3, 5, 3, 3};
        int ret = offer56.singleNumber(input);
        System.out.println(ret);
        System.out.println("====================");
    }
}





