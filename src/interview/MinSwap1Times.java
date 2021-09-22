package src.interview;

import java.io.IOException;

/**
 * 输入：[1,0,1,0,1]
 * 输出：1
 * 解释：
 * 有三种可能的方法可以把所有的 1 组合在一起：
 * [1,1,1,0,0]，交换 1 次；
 * [0,1,1,1,0]，交换 2 次；
 * [0,0,1,1,1]，交换 1 次。
 * 所以最少的交换次数为 1。
 *
 * 示例 2：
 * 输入：[0,0,0,1,0]
 * 输出：0
 * 解释：
 * 由于数组中只有一个 1，所以不需要交换。
 *
 * 示例 3：
 * 输入：[1,0,1,0,1,0,0,1,1,0,1]
 * 输出：3
 * 解释：
 * 交换 3 次，一种可行的只用 3 次交换的解决方案是 [0,0,0,0,0,1,1,1,1,1,1]。
 */
public class MinSwap1Times {
    public static int solution (int [] arr) {
        int n = 0;

        // 先统计1的个数
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 1) {
                n++;
            }
        }

        // 根据滑动窗口，来计算滑动窗口内的1，n - maxOnes则是需要交换的最小次数
        int ones = 0;
        int maxOnes = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 1) { // 窗口末端
                ones++;
            }

            if ((i >= n) && (arr[i - n] == 1)) {
                ones--;
            }

            maxOnes = Math.max(ones, maxOnes);
        }

        return n - maxOnes;
    }

    public static void main (String [] args) throws IOException {
        int [] mat = {1,0,1,0,1,0,0,1,1,0,1};
        int ret = solution(mat);
        System.out.println(ret);
    }
}
