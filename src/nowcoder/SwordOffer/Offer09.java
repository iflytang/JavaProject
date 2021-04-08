package src.nowcoder.SwordOffer;

import java.util.Stack;

/**
 * @author tsf
 * @date 21-03-25
 * @desp 剑指 Offer 09. 剑指 Offer 09. 用两个栈实现队列
 * 用两个栈实现一个队列。队列的声明如下，请实现它的两个函数 appendTail 和 deleteHead ，
 * 分别完成在队列尾部插入整数和在队列头部删除整数的功能。(若队列中没有元素，deleteHead 操作返回 -1 )
 *
 * 示例 1：
 *
 * 输入：
 * ["CQueue","appendTail","deleteHead","deleteHead"]
 * [[],[3],[],[]]
 * 输出：[null,null,3,-1]
 *
 * 示例 2：
 *
 * 输入：
 * ["CQueue","deleteHead","appendTail","appendTail","deleteHead","deleteHead"]
 * [[],[],[5],[2],[],[]]
 * 输出：[null,-1,null,null,5,2]
 * 提示：
 *
 * 1 <= values <= 10000
 * 最多会对 appendTail、deleteHead 进行 10000 次调用
 */


public class Offer09 {
    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();

    public void push(int node) {
        stack1.push(node);
    }

    public int pop() {

        // 如果stack2非空，直接弹出
        if (!stack2.empty()) {
            return stack2.pop();
        }
        
        // 如果stack2空，检查stack1是否空。若stack1非空，则把stack1数据插入到stack2，再pop stack2
        if (stack1.empty()) {
            return -1;
        }

        while (!stack1.empty()) {
            int stackData = stack1.pop();
            stack2.push(stackData);
        }

        return stack2.pop();
    }


    public static void main(String[] args) {

        Offer09 offer09 = new Offer09();

        offer09.push(1);
        offer09.push(2);
        offer09.push(3);
        int ret = offer09.pop();
        System.out.println(ret);
        ret = offer09.pop();
        System.out.println(ret);

        offer09.push(4);
        ret = offer09.pop();
        System.out.println(ret);

        offer09.push(5);
        ret = offer09.pop();
        System.out.println(ret);
        ret = offer09.pop();
        System.out.println(ret);


    }
}
