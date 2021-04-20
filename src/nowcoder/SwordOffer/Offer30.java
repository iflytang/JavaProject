package src.nowcoder.SwordOffer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

/**
 * @author tsf
 * @date 21-03-25
 * @desp
 *
 *  剑指 Offer 30. 包含min函数的栈
 * 定义栈的数据结构，请在该类型中实现一个能够得到栈的最小元素的 min 函数在该栈中，调用 min、push 及 pop 的时间复杂度都是 O(1)。
 *
 * 示例:
 *
 * MinStack minStack = new MinStack();
 * minStack.push(-2);
 * minStack.push(0);
 * minStack.push(-3);
 * minStack.min();   --> 返回 -3.
 * minStack.pop();
 * minStack.top();      --> 返回 0.
 * minStack.min();   --> 返回 -2.
 *
 *
 * 提示：
 *
 * 各函数的调用总次数不超过 20000 次
 *
 *
 * 注意：本题与主站 155 题相同：https://leetcode-cn.com/problems/min-stack/
 */

public class Offer30 {
    
    Stack<Integer> stackData = new Stack<>();
    Stack<Integer> minData = new Stack<>();  // 辅助栈

    public void push(int node) {

        stackData.push(node);  // 栈压入

        if (minData.empty() || (node < minData.peek())) {
            minData.push(node);
        } else {
            minData.push(minData.peek());
        }
    }

    public void pop() {
        if (!stackData.empty()) {
            stackData.pop();
            minData.pop();
        }
    }

    public int top() {
        return stackData.peek();
    }

    public int min() {
        return minData.peek();
    }

    public static void main(String[] args) {

        Offer30 offer30 = new Offer30();

        offer30.push(-2);
        offer30.push(0);
        offer30.push(-3);

        System.out.println("min: " + offer30.min());

        offer30.pop();
        System.out.println("top: " + offer30.top());
        System.out.println("min: " + offer30.min());
    }
}



