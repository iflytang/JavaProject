package src.nowcoder.SwordOffer;

import java.util.Stack;

/**
 * @author tsf
 * @date 21-03-25
 * @desp
 *
 * 剑指 Offer 31. 栈的压入、弹出序列
 * 输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否为该栈的弹出顺序。假设压入栈的所有数字均不相等。例如，序列 {1,2,3,4,5} 是某栈的压栈序列，序列 {4,5,3,2,1} 是该压栈序列对应的一个弹出序列，但 {4,3,5,1,2} 就不可能是该压栈序列的弹出序列。
 *
 * 示例 1：
 *
 * 输入：pushed = [1,2,3,4,5], popped = [4,5,3,2,1]
 * 输出：true
 * 解释：我们可以按以下顺序执行：
 * push(1), push(2), push(3), push(4), pop() -> 4,
 * push(5), pop() -> 5, pop() -> 3, pop() -> 2, pop() -> 1
 * 示例 2：
 *
 * 输入：pushed = [1,2,3,4,5], popped = [4,3,5,1,2]
 * 输出：false
 * 解释：1 不能在 2 之前弹出。
 *
 *
 * 提示：
 *
 * 0 <= pushed.length == popped.length <= 1000
 * 0 <= pushed[i], popped[i] < 1000
 * pushed 是 popped 的排列。
 * 注意：本题与主站 946 题相同：https://leetcode-cn.com/problems/validate-stack-sequences/
 */

public class Offer31 {

    public boolean validateStackSequences(int[] pushed, int[] popped) {

        if (pushed.length == 0 || popped.length == 0 || (pushed.length != popped.length)) {
            return false;
        }

        Stack<Integer> pushStack = new Stack<>();  // 利用辅助栈解模拟出栈顺序

        int i = 0, j = 0;
        boolean ret = true;
        for (; j < popped.length; j++) {
            while (true) {
                if (pushStack.empty() || (pushStack.peek() != popped[j])) {  // 如果空或出栈值不等当前栈顶，就入栈
                     if (i < pushed.length)
                        pushStack.push(pushed[i++]);
                }

                if (pushStack.peek() == popped[j]) {  // 如果栈顶等于出栈值，就出栈
                    pushStack.pop();
                    break;
                }

                if (i >= pushed.length)  // 所有数据都入栈了，还没到栈顶，直接返回
                    break;
            }

        }

        return pushStack.isEmpty();   // 查看当前栈的大小。如果是出栈顺序，栈一定是空的。
    }

    public boolean validateStackSequences2(int[] pushed, int[] popped) {

        // 边界条件检查
        if (pushed.length == 0 || popped.length == 0 || (pushed.length != popped.length)) {
            return false;
        }

        Stack<Integer> pushedStack = new Stack<>();

        int j = 0;
        for (int i = 0; i < pushed.length; i++) {
            pushedStack.push(pushed[i]);

            while (!pushedStack.isEmpty() && (pushedStack.peek() == popped[j])) {  // 如果是栈顶，就一定出栈
                pushedStack.pop();
                j++;
            }
        }

        return pushedStack.isEmpty();  // 检测非空
    }


    public static void main(String[] args) {

        Offer31 offer31 = new Offer31();

        int [] pushed = {1, 2, 3, 4, 5};
        int [] poped = {4, 5, 3, 2, 1};
        boolean ret = offer31.validateStackSequences(pushed, poped);
        System.out.println(ret);
        ret = offer31.validateStackSequences2(pushed, poped);
        System.out.println(ret);

        System.out.println("==============");

        int [] pushed2 = {1, 2, 3, 4, 5};
        int [] poped2 = {4, 3, 5, 1, 2};
        ret = offer31.validateStackSequences(pushed2, poped2);
        System.out.println(ret);
        ret = offer31.validateStackSequences2(pushed2, poped2);
        System.out.println(ret);

    }
}



