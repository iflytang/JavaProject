package src.nowcoder.SwordOffer;

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

public class Offer30_v2 {
    
    private myStackNode head;  // 利用链表实现stack结构

    public void push(int node) {
        if (head == null) {
            head = new myStackNode(node, node, null);
        } else {
            head = new myStackNode(node, Math.min(node, head.xMin), head);  // 每次记录最小的值状态
        }
    }

    public void pop() {
        head = head.xNext;
    }

    public int top() {
        return head.xVal;
    }

    public int min() {
        return head.xMin;
    }

    public static void main(String[] args) {

        Offer30_v2 offer30 = new Offer30_v2();

        offer30.push(-2);
        offer30.push(0);
        offer30.push(-3);

        System.out.println("min: " + offer30.min());

        offer30.pop();
        System.out.println("top: " + offer30.top());
        System.out.println("min: " + offer30.min());

        Offer30_v2 offer30_v2 = new Offer30_v2();
        offer30_v2.push(3);
        System.out.println(offer30_v2.min());  // 3

        offer30_v2.push(4);
        System.out.println(offer30_v2.min());  // 3

        offer30_v2.push(2);
        System.out.println(offer30_v2.min());  // 2

        offer30_v2.push(3);
        System.out.println(offer30_v2.min());  // 3

        offer30_v2.pop();
        System.out.println(offer30_v2.min());  // 3

        offer30_v2.pop();
        System.out.println(offer30_v2.min());  // 3

        offer30_v2.pop();
        System.out.println(offer30_v2.min());  // 3

        offer30_v2.push(0);
        System.out.println(offer30_v2.min());  // 3
    }


}

class myStackNode {
    int xVal;
    int xMin;
    myStackNode xNext;

    myStackNode (int val, int min, myStackNode next) {
        xVal = val;
        xMin = min;
        xNext = next;
    }
}



