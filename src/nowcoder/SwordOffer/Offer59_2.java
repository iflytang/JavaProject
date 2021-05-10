package src.nowcoder.SwordOffer;

import java.util.*;

/**
 * @author tsf
 * @date 21-03-25
 * @desp
 * 剑指 Offer 59 - II. 队列的最大值
 * 请定义一个队列并实现函数 max_value 得到队列里的最大值，要求函数max_value、push_back 和 pop_front 的均摊时间复杂度都是O(1)。
 *
 * 若队列为空，pop_front 和 max_value 需要返回 -1
 *
 * 示例 1：
 *
 * 输入:
 * ["MaxQueue","push_back","push_back","max_value","pop_front","max_value"]
 * [[],[1],[2],[],[],[]]
 * 输出: [null,null,null,2,1,2]
 * 示例 2：
 *
 * 输入:
 * ["MaxQueue","pop_front","max_value"]
 * [[],[],[]]
 * 输出: [null,-1,-1]
 *
 *
 * 限制：
 *
 * 1 <= push_back,pop_front,max_value的总操作数 <= 10000
 * 1 <= value <= 10^5
 */

public class Offer59_2 {
    /**
     * Your MaxQueue object will be instantiated and called as such:
     * MaxQueue obj = new MaxQueue();
     * int param_1 = obj.max_value();
     * obj.push_back(value);
     * int param_3 = obj.pop_front();
     */

    Deque<Integer> queue;
    Deque<Integer> maxVal;

    public Offer59_2() {  // 使用双向递减队列
        queue = new LinkedList<>();
        maxVal = new LinkedList<>();
    }

    public int max_value() {  // 使用双向递减队列
        if (!maxVal.isEmpty()) {
            return maxVal.peekFirst();
        } else {
            return -1;
        }
    }

    public void push_back(int value) { // 使用双向递减队列，保持单调性
        queue.offer(value);

        // maxVal队列检查单调递减特性，记录窗口最大值，注意这里是 <
        while (!maxVal.isEmpty() && (maxVal.peekLast() < value)) {
            maxVal.pollLast();
        }
        maxVal.offer(value);       
    }

    public int pop_front() {
        if (queue.isEmpty()) {
            return -1;
        }

        if (queue.peekFirst().equals(maxVal.peekFirst())) { // 若 maxVal 首元素和 queue 首元素 相等 ，则将 maxVal 首元素出队（以保持两队列 元素一致 ）
            maxVal.pollFirst();
        }

        return queue.pollFirst();
    }

    public static void main(String[] args) {

        Offer59_2 offer59 = new Offer59_2();

        offer59.push_back(1);
        System.out.println(offer59.queue.toString());
        System.out.println(offer59.maxVal.toString());
        System.out.println("====================");

        offer59.push_back(2);
        System.out.println(offer59.queue.toString());
        System.out.println(offer59.maxVal.toString());
        System.out.println("====================");

        int ret = offer59.max_value();
        System.out.println(ret);
        System.out.println(offer59.queue.toString());
        System.out.println(offer59.maxVal.toString());
        System.out.println("====================");

        ret = offer59.pop_front();
        System.out.println(ret);
        System.out.println(offer59.queue.toString());
        System.out.println(offer59.maxVal.toString());
        System.out.println("3====================");

        ret = offer59.max_value();
        System.out.println(ret);
        System.out.println(offer59.queue.toString());
        System.out.println(offer59.maxVal.toString());
        System.out.println("====================");

        offer59.push_back(3);
        System.out.println(offer59.queue.toString());
        System.out.println(offer59.maxVal.toString());
        System.out.println("====================");

        ret = offer59.max_value();
        System.out.println(ret);
        System.out.println(offer59.queue.toString());
        System.out.println(offer59.maxVal.toString());
        System.out.println("====================");


    }
}





