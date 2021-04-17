package src.nowcoder.SwordOffer;

import java.util.Arrays;

/**
 * @author tsf
 * @date 21-03-25
 * @desp
 * 剑指 Offer 22. 链表中倒数第k个节点
 * 输入一个链表，输出该链表中倒数第k个节点。为了符合大多数人的习惯，本题从1开始计数，即链表的尾节点是倒数第1个节点。
 *
 * 例如，一个链表有 6 个节点，从头节点开始，它们的值依次是 1、2、3、4、5、6。这个链表的倒数第 3 个节点是值为 4 的节点。
 *
 * 示例：
 *
 * 给定一个链表: 1->2->3->4->5, 和 k = 2.
 *
 * 返回链表 4->5.
 */

public class Offer22 {

    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     *
     * @param pHead ListNode类
     * @param k int整型
     * @return ListNode类
     */
    public ListNode FindKthToTail (ListNode pHead, int k) {  // 遍历了2次链表
        // write code here

        if (pHead == null || k == 0) {
            return null;
        }

        ListNode curNode = pHead;
        ListNode kthNode = null;

        int i = 0;
        while (curNode != null) {
            curNode = curNode.next;
            i++;
        }

        if (i < k) {
            return null;
        }

        curNode = pHead;
        int j = 0;
        while ((curNode != null)) {
            if (j == (i - k)) {
                kthNode = curNode;
                break;
            }

            curNode = curNode.next;
            j++;
        }

        return kthNode;
    }

     public ListNode FindKthToTail2 (ListNode pHead, int k) {  // 遍历了1次链表，利用2个指针
        if ((pHead == null) || (k == 0)) {
            return null;
        }

        ListNode pAhead = pHead;

        for (int i = 0; i < k; i++) {
            if (pAhead != null) {
                pAhead = pAhead.next;
            } else {
                return null;
            }
        }

        ListNode pBehind = pHead;
        while (pAhead != null) {
            pAhead = pAhead.next;
            pBehind = pBehind.next;
        }

        return pBehind;
     }


    public static void main(String[] args) {

        Offer22 offer22 = new Offer22();

        ListNode[] listNodes = new ListNode[6];

        listNodes[0] = new ListNode(1);
        listNodes[1] = new ListNode(2);
        listNodes[2] = new ListNode(3);
        listNodes[3] = new ListNode(4);
        listNodes[4] = new ListNode(5);
        listNodes[5] = new ListNode(6);

        listNodes[0].next = listNodes[1];
        listNodes[1].next = listNodes[2];
        listNodes[2].next = listNodes[3];
        listNodes[3].next = listNodes[4];
        listNodes[4].next = listNodes[5];

        ListNode head = listNodes[0];
        while (head != null) {
            System.out.println(head.val);
            head = head.next;
        }
        System.out.println("=========================");

        head = listNodes[0];
        int k = 6;
        ListNode ret = offer22.FindKthToTail(head, k);
        while (ret != null) {
            System.out.println(ret.val);
            ret = ret.next;
        }
        System.out.println("=========================");

        head = listNodes[0];
        k = 6;
        ret = offer22.FindKthToTail2(head, k);
        while (ret != null) {
            System.out.println(ret.val);
            ret = ret.next;
        }
        System.out.println("=========================");

    }
}



