package src.nowcoder.SwordOffer;

import java.util.Arrays;

/**
 * @author tsf
 * @date 21-03-25
 * @desp
 * 剑指 Offer 21. 调整数组顺序使奇数位于偶数前面
 * 输入一个整数数组，实现一个函数来调整该数组中数字的顺序，
 * 使得所有奇数位于数组的前半部分，所有偶数位于数组的后半部分。
 * 并保证奇数和奇数，偶数和偶数之间的相对位置不变。
 * 示例：
 *
 * 输入：nums = [1,2,3,4]
 * 输出：[1,3,2,4]
 * 注：[3,1,2,4] 也是正确的答案之一。
 *
 *
 * 提示：
 *
 * 0 <= nums.length <= 50000
 * 1 <= nums[i] <= 10000
 */

public class Offer21 {

    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     * @param array int整型一维数组
     * @return int整型一维数组
     */
    public int[] reOrderArray (int[] array) {
        // write code here

        if (array.length == 0) {
            return array;
        }

        int[] ret = new int[array.length];

        int k = array.length - 1;
        for (int i = 0, j = 0; i < array.length; i++) {

            if ((array[i] & 0x01) == 0x01) {   // 二进制识别奇数，奇数顺序放
                ret[j] = array[i];
                j++;
                continue;
            }

            ret[k--] = array[i];  // 偶数逆序放
        }

        int evenLen = array.length - k;
        k++;  // 偶数区间[k++, array.length)
        int tmp = 0;
        for (int i = 0; i < evenLen/2; i++) {  // 互换偶数区间的前后元素位置
            tmp = ret[k + i];
            ret[k + i] = ret[ret.length - 1 - i];
            ret[ret.length - 1 - i] = tmp;
        }


        return ret;
    }


    public static void main(String[] args) {

        Offer21 offer21 = new Offer21();

        int [] input = {1, 3, 4, 5, 6, 9, 12};
        System.out.println(Arrays.toString(input));
        int [] ret = offer21.reOrderArray(input);
        System.out.println(Arrays.toString(ret));
        System.out.println("====================");

    }
}



