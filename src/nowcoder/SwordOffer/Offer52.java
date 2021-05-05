package src.nowcoder.SwordOffer;

import java.util.Arrays;

/**
 * @author tsf
 * @date 21-03-25
 * @desp
 * 剑指 Offer 52. 两个链表的第一个公共节点
 * 输入两个链表，找出它们的第一个公共节点。
 *
 * 注意：
 *
 * 如果两个链表没有交点，返回 null.
 * 在返回结果后，两个链表仍须保持原有的结构。
 * 可假定整个链表结构中没有循环。
 * 程序尽量满足 O(n) 时间复杂度，且仅用 O(1) 内存。
 * 本题与主站 160 题相同：https://leetcode-cn.com/problems/intersection-of-two-linked-lists/
 */

public class Offer52 {

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {  // 公共尾节点
        if (headA == null || headB == null) {
            return null;
        }

        // 得到两个链表的长度
        int aLen = 0, bLen = 0;
        ListNode aList = headA, bList = headB;

        while (aList != null) {
            aLen++;
            aList = aList.next;
        }

        while (bList != null) {
            bLen++;
            bList = bList.next;
        }

        // 确定长短链表
        ListNode longHead = headA, shortHead = headB;
        int diffLen = aLen - bLen;
        if (diffLen < 0) {
            longHead = headB;
            shortHead = headA;
            diffLen = bLen - aLen;
        }

       // 先在长链表上走几步，再同时在两个链表上遍历
        for (int i = 0; i < diffLen; i++) {
            longHead = longHead.next;
        }

        while (longHead != null && shortHead != null) {
            if (longHead == shortHead) {
                break;
            }

            longHead = longHead.next;
            shortHead = shortHead.next;
        }

        return longHead;  // longHead或shortHead，相等
    }

    public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {  // 公共尾节点，双指针
        ListNode A = headA, B = headB;

        while (A != B) {
            A = (A != null) ? A.next : headB;  // 走到末位时，换到另外一个链路遍历
            B = (B != null) ? B.next : headA;
        }

        return A;
    }

    public static void main(String[] args) {

        Offer52 offer52 = new Offer52();

        ListNode headA0 = new ListNode(0);
        ListNode headA1 = new ListNode(9);
        ListNode headA2 = new ListNode(1);
        ListNode headA3 = new ListNode(2);
        ListNode headA4 = new ListNode(4);

        headA0.next = headA1;
        headA1.next = headA2;
        headA2.next = headA3;
        headA3.next = headA4;

        ListNode headB0 = new ListNode(3);
//        ListNode headB1 = new ListNode(2);
//        ListNode headB2 = new ListNode(4);

        headB0.next = headA3;
//        headB1.next = headB2;

        ListNode ret = offer52.getIntersectionNode(headA0, headB0);
        System.out.println(ret.val);

        System.out.println("====================");
        ret = offer52.getIntersectionNode2(headA0, headB0);
        System.out.println(ret.val);
    }
}





