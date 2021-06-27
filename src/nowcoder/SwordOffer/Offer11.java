package src.nowcoder.SwordOffer;

import java.util.Arrays;

/**
 * @author tsf
 * @date 21-03-25
 * @desp
 * 剑指 Offer 11. 旋转数组的最小数字
 * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。输入一个递增排序的数组的一个旋转，
 * 输出旋转数组的最小元素。例如，数组 [3,4,5,1,2] 为 [1,2,3,4,5] 的一个旋转，该数组的最小值为1。
 *
 * 示例 1：
 *
 * 输入：[3,4,5,1,2]
 * 输出：1
 * 示例 2：
 *
 * 输入：[2,2,2,0,1]
 * 输出：0
 */


public class Offer11 {

    public int minNumberInRotateArray2(int [] array) {
        /* 二分法变形：不知道查找的target */
        int left = 0;
        int right = array.length - 1;
        int mid = 0;

        while (left <= right) {  // 递增序列
            mid = left + (right - left) / 2;  //防止溢出

            if (array[mid] < array[right]) {  // 情况1：最小值在左侧
                right = mid;
            } else if (array[mid] > array[right]) {  // 情况2：最小值在右侧。注意左侧是严格递增的。
                left = mid + 1;
            } else {  // 情况3：两者相等
                right = right - 1;
            }
        }

        return array[mid];
    }

    public int minNumberInRotateArray(int [] array) {

        int ret = 0;

        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] > array[i + 1]) {  // 由于是递增序列，若相邻违反，必为最小
                ret = array[i + 1];
            }

            if (((i+1) == (array.length -1)) && (array[i+1] > array[0])) {  // 如果尾处仍未违反，这说明旋转数组的最小值在idx=0处
                ret = array[0];
            }
        }

        return ret;
    }


    public static void main(String[] args) {

        Offer11 offer11 = new Offer11();

        int [] input1 = {1, 3, 5};
        int ret = offer11.minNumberInRotateArray(input1);
        System.out.println(ret);

        int [] input2 = {2, 2, 2, 0, 1};
        ret = offer11.minNumberInRotateArray(input2);
        System.out.println(ret);

    }
}
