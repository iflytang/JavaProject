package src.nowcoder.SwordOffer;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author tsf
 * @date 21-03-25
 * @desp
 * 剑指 Offer 57 - II. 和为s的连续正数序列
 * 输入一个正整数 target ，输出所有和为 target 的连续正整数序列（至少含有两个数）。
 *
 * 序列内的数字由小到大排列，不同序列按照首个数字从小到大排列。
 *
 * 示例 1：
 *
 * 输入：target = 9
 * 输出：[[2,3,4],[4,5]]
 * 示例 2：
 *
 * 输入：target = 15
 * 输出：[[1,2,3,4,5],[4,5,6],[7,8]]
 *
 *
 * 限制：
 *
 * 1 <= target <= 10^5
 */

public class Offer57_2 {

    public int[][] findContinuousSequence(int target) {  // 滑动窗口

        int left = 1;
        int right = 2;
        int sum = left + right;

        ArrayList<int []> res = new ArrayList<>();

        while (left < right) {
            if (sum == target) {  // 可行解
                int [] targetVec = new int [right - left + 1];
                for (int i = left; i <= right; i++) {
                    targetVec[i - left] = i;
                }
                res.add(targetVec);
            }

            if (sum >= target) {  // 偏大，左边界向右滑动
                sum = sum - left;
                left++;
            }

            if (sum < target) {  // 偏小，右边界向右滑动
                right++;
                sum = sum + right;
            }
        }

        // 构造返回二维数组
        /*int [][] ret = new int [res.size()][];  // java二维数组可以变长
        for (int i = 0; i < ret.length; i++) {
            ret[i] = res.get(i);
        }

        return ret;*/

        return res.toArray(new int[res.size()][]);
    }

    public static void main(String[] args) {

        Offer57_2 offer57 = new Offer57_2();
        
        int target = 9;
        int [][] ret = offer57.findContinuousSequence(target);
        System.out.println(Arrays.deepToString(ret));
        System.out.println("====================");

        int target2 = 15;
        int [][] ret2 = offer57.findContinuousSequence(target2);
        System.out.println(Arrays.deepToString(ret2));
        System.out.println("====================");
    }
}





