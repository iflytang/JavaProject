package src.nowcoder.SwordOffer;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * @author tsf
 * @date 21-03-25
 * @desp
 * 剑指 Offer 51. 数组中的逆序对
 * 在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。输入一个数组，求出这个数组中的逆序对的总数。
 *
 * 示例 1:
 *
 * 输入: [7,5,6,4]
 * 输出: 5
 *
 *
 * 限制：
 *
 * 0 <= 数组长度 <= 50000
 */

public class Offer51 {

    public int mergeSort(int [] nums, int left, int right, int [] res) {
        if (left >= right) {
            return 0;
        }

        int mid = left + (right - left) / 2;

        int lefrPairs = mergeSort(nums, left, mid, res);  // left: [left, mid]
        int rightPairs = mergeSort(nums, mid + 1, right, res);  // right: [mid + 1, right]

        int crossPairs = merge(nums, left, mid, right, res);

        return lefrPairs + rightPairs + crossPairs;
    }

    public int merge(int [] nums, int left, int mid, int right, int [] res) {
        int i = left;
        int j = mid + 1;
        int k = left;

        int count = 0;

        while (i <= mid && j <= right) {
            if (nums[i] <= nums[j]) { // 比较两个区域的大小
                res[k++] = nums[i++];
            } else {
                res[k++] = nums[j++];
                count += (mid - i + 1);  // 当出现对换时，即有逆序对存在
            }
        }

        // 以下两个while只执行一个
        while (i <= mid) {
            res[k++] = nums[i++];
        }

        while (j <= right) {
            res[k++] = nums[j++];
        }

        for (k = left; k <= right; k++) {  // 重新赋值
            nums[k] = res[k];
        }

        return count;
    }

    public int reversePairs(int[] nums) {

        if (nums.length <= 1) {
            return 0;
        }

        return 2;
    }


    public static void main(String[] args) {

        Offer51 offer51 = new Offer51();

        int [] inputs = {7, 5, 6, 4};
//        int [] inputs = {38, 27, 43, 3, 9, 82, 10};
        int [] res = new int[inputs.length];
        int ret = offer51.reversePairs(inputs);
        System.out.println(ret);
        System.out.println("==========================");

        System.out.println(Arrays.toString(inputs));
        ret = offer51.mergeSort(inputs, 0, inputs.length - 1, res);
        System.out.println(ret + ", " + Arrays.toString(inputs));
        System.out.println("==========================");


        int [] inputs2 = {38, 27, 43, 3, 9, 82, 10};

        int [] res2 = new int[inputs2.length];
        ret = offer51.mergeSort(inputs2, 0, inputs2.length - 1, res2);
        System.out.println(Arrays.toString(inputs2) + ", count: " + ret);
        System.out.println("==========================");



    }
}





