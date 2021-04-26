package src.nowcoder.SwordOffer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tsf
 * @date 21-03-25
 * @desp
 * 剑指 Offer 35. 复杂链表的复制
 * 请实现 copyRandomList 函数，复制一个复杂链表。在复杂链表中，每个节点除了有一个 next 指针指向下一个节点，
 * 还有一个 random 指针指向链表中的任意节点或者 null。
 *
 * 示例 1：
 *
 * 输入：head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
 * 输出：[[7,null],[13,0],[11,4],[10,2],[1,0]]
 * 示例 2：
 *
 * 输入：head = [[1,1],[2,1]]
 * 输出：[[1,1],[2,1]]
 * 示例 3：
 *
 * 输入：head = [[3,null],[3,0],[3,null]]
 * 输出：[[3,null],[3,0],[3,null]]
 * 示例 4：
 *
 * 输入：head = []
 * 输出：[]
 * 解释：给定的链表为空（空指针），因此返回 null。
 *
 *
 * 提示：
 *
 * -10000 <= Node.val <= 10000
 * Node.random 为空（null）或指向链表中的节点。
 * 节点数目不超过 1000 。
 *
 *
 * 注意：本题与主站 138 题相同：https://leetcode-cn.com/problems/copy-list-with-random-pointer/
 */

public class Offer35 {

    public RandomListNode Clone(RandomListNode pHead) {

        if (pHead == null) {
            return pHead;
        }

        /* 1. copy list nodes in even locations. 
        * 将复制的新节点放在原节点后，形成偶数位的复制链表
        */
        RandomListNode curNode = pHead;
        RandomListNode clonedNode = null;
        while (curNode != null) {
            clonedNode = new RandomListNode(curNode.label);

            clonedNode.next = curNode.next;
            curNode.next = clonedNode;

            curNode = clonedNode.next;
        }

        /* 2. connect random nodes according to odd locations. 
        *  根据奇偶的相对位置，来选择随机位置
        */
        curNode = pHead;
        while (curNode != null) {
            if (curNode.random != null) {
                curNode.next.random = curNode.random.next;
            }
            curNode = curNode.next.next;
        }

        /* 3. pick even nodes 
        * 选出偶数位的复制节点，形成复制链表
        */
        curNode = pHead;
        RandomListNode ret = pHead.next, cloneList = null;
        while (curNode != null) {
            cloneList = curNode.next;

            curNode.next = cloneList.next;
            if (curNode.next != null) {
                cloneList.next = curNode.next.next;
            }

            curNode = curNode.next;
        }

        return ret;
    }

    public static void main(String[] args) {

        Offer35 offer35 = new Offer35();
        RandomListNode [] randomListNodes = new RandomListNode[2];

        randomListNodes[0] = new RandomListNode(1);
        randomListNodes[1] = new RandomListNode(2);

        randomListNodes[0].next = randomListNodes[1];
        randomListNodes[0].random = randomListNodes[1];

        // next = null
        randomListNodes[1].random = randomListNodes[1];

        RandomListNode pHead = randomListNodes[0];
        while (pHead != null) {
            System.out.println(pHead + ", " + pHead.label + ", " + pHead.next + ", " + pHead.random);
            pHead = pHead.next;
        }
        System.out.println("===================");


        pHead = offer35.Clone(randomListNodes[0]);
        while (pHead != null) {
            System.out.println(pHead + ", " + pHead.label + ", " + pHead.next + ", " + pHead.random);
            pHead = pHead.next;
        }
    }
}

class RandomListNode {
    int label;
    RandomListNode next = null;
    RandomListNode random = null;

    RandomListNode(int label) {
        this.label = label;
    }
}



