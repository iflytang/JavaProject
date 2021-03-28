package src.nowcoder.SwordOffer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * @author tsf
 * @date 21-03-25
 * @desp 剑指 Offer 06. 从尾到头打印链表。
 * 题目描述
 * 输入一个链表的头节点，从尾到头反过来返回每个节点的值（用数组返回）。
 *
 * 示例 1：
 *
 * 输入：head = [1,3,2]
 * 输出：[2,3,1]
 *
 * 限制：
 *
 * 0 <= 链表长度 <= 10000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/cong-wei-dao-tou-da-yin-lian-biao-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */



public class Offer06 {


//    public int[] reversePrint(ListNode head) {
//
//    }

    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {

        if (listNode == null) {
            return new ArrayList<Integer>();
        }

        ArrayList<Integer> arrayNode = new ArrayList<>();

        while (listNode.next != null) {
            arrayNode.add(listNode.val);
            listNode = listNode.next;
        }
        arrayNode.add(listNode.val);

        ArrayList<Integer> res = new ArrayList<>();
        for (int i = arrayNode.size() - 1; i >= 0; i--) {
            res.add(arrayNode.get(i));
        }

        return res;

    }

    public static void main(String[] args) {
        /*ListNode[] listNodes = new ListNode[4];

        listNodes[0] = new ListNode(67);
        listNodes[1] = new ListNode(0);
        listNodes[2] = new ListNode(24);
        listNodes[3] = new ListNode(58);

        listNodes[0].next = listNodes[1];
        listNodes[1].next = listNodes[2];
        listNodes[2].next = listNodes[3];*/

        ListNode listNodes = null;

        Offer06 offer06 = new Offer06();
        ArrayList<Integer> res = offer06.printListFromTailToHead(listNodes);
        System.out.println(res);

    }
}

class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }
}