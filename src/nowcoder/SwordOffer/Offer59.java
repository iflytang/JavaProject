package src.nowcoder.SwordOffer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * @author tsf
 * @date 21-03-25
 * @desp
 * 剑指 Offer 59 - I. 滑动窗口的最大值
 * 给定一个数组 nums 和滑动窗口的大小 k，请找出所有滑动窗口里的最大值。
 *
 * 示例:
 *
 * 输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
 * 输出: [3,3,5,5,6,7]
 * 解释:
 *
 *   滑动窗口的位置                最大值
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 *  1 [3  -1  -3] 5  3  6  7       3
 *  1  3 [-1  -3  5] 3  6  7       5
 *  1  3  -1 [-3  5  3] 6  7       5
 *  1  3  -1  -3 [5  3  6] 7       6
 *  1  3  -1  -3  5 [3  6  7]      7
 *
 *
 * 提示：
 *
 * 你可以假设 k 总是有效的，在输入数组不为空的情况下，1 ≤ k ≤ 输入数组的大小。
 *
 * 注意：本题与主站 239 题相同：https://leetcode-cn.com/problems/sliding-window-maximum/
 */

public class Offer59 {

    // https://leetcode-cn.com/problems/hua-dong-chuang-kou-de-zui-da-zhi-lcof/solution/dong-hua-yan-shi-dan-diao-dui-lie-jian-z-unpy/
    public int[] maxSlidingWindow(int[] nums, int k) {  // 滑动窗口，hard
        if (nums.length == 0 || k == 0) {
            return new int[0];
        }

        int [] res = new int[nums.length - k + 1]; // 窗口个数

        Deque<Integer> queue = new LinkedList<>();  // 保存单调减队列的下标，最大元素位于队列首

        // 遍历数组中元素，right表示滑动窗口右边界
        for (int right = 0; right < nums.length; right++) {

            // 如果队列不为空且当前考察元素大于等于队尾元素，则将队尾元素移除。
            // 直到，队列为空或当前考察元素小于新的队尾元素
            while (!queue.isEmpty() && (nums[queue.peekLast()] < nums[right])) {  // 1. 非空，且队列尾元素小于右侧元素时违反单调减
                queue.removeLast();
            }

            // 存储元素下标
            queue.add(right);  // 2. 滑动窗口将元素加入队列

            // 计算窗口左侧边界
            int left = right - k + 1;

            // 当队首元素的下标小于滑动窗口左侧边界left时
            // 表示队首元素已经不再滑动窗口内，因此将其从队首移除
            if (queue.peekFirst() < left) {  // 3. 如果队首已不在滑动窗口内，移除
                queue.removeFirst();
            }

            // 由于数组下标从0开始，因此当窗口右边界right+1大于等于窗口大小k时
            // 意味着窗口形成。此时，队首元素就是该窗口内的最大值
            if ((right + 1) >= k) {  // 4. 已形成窗口，可记录数据
                res[left] = nums[queue.peekFirst()];
            }
        }

        return res;
    }

    public int[] maxSlidingWindow2(int[] nums, int k) { // 超出时间限制
        int left = 0;
        int right = k;

        ArrayList<Integer> res = new ArrayList<>();

        while (right <= nums.length) {
            int [] tmp = Arrays.copyOfRange(nums, left, right + 1);
            int maxVal = nums[left];

            for (int i = 0; i < k; i++) {
                maxVal = Math.max(maxVal, tmp[i]);
            }

            res.add(maxVal);

            left++;
            right++;
        }

        int [] ret = new int[res.size()];
        for (int i = 0; i < res.size(); i++) {
            ret[i] =  res.get(i);
        }

        return ret;
    }

    public static void main(String[] args) {

        Offer59 offer59 = new Offer59();

        int [] nums = {1, 3, -1, -3, 5, 3, 6, 7};
        int k = 3;

        int [] ret = offer59.maxSlidingWindow(nums, k);
        System.out.println(Arrays.toString(ret));
        System.out.println("====================");

        ret = offer59.maxSlidingWindow2(nums, k);
        System.out.println(Arrays.toString(ret));
        System.out.println("====================");
    }
}





