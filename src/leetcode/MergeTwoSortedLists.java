package src.leetcode;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * @author tsf
 * @date 21-03-25
 * @desp https://leetcode-cn.com/problems/merge-two-sorted-lists/
 * 21. 合并两个有序链表
 * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 * 示例 1：
 *
 *
 * 输入：l1 = [1,2,4], l2 = [1,3,4]
 * 输出：[1,1,2,3,4,4]
 * 示例 2：
 *
 * 输入：l1 = [], l2 = []
 * 输出：[]
 * 示例 3：
 *
 * 输入：l1 = [], l2 = [0]
 * 输出：[0]
 *
 *
 * 提示：
 *
 * 两个链表的节点数目范围是 [0, 50]
 * -100 <= Node.val <= 100
 * l1 和 l2 均按 非递减顺序 排列
 */

public class MergeTwoSortedLists {

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {

        ListNode head = new ListNode(-1); // dummy node
        ListNode ret = head;

        ListNode headL1 = l1;
        ListNode headL2 = l2;

        while (headL1 != null && headL2 != null) {  // compare their common len part

            if (headL1.val <= headL2.val) {
                head.next = headL1;
                head = head.next;

                headL1 = headL1.next;
            } else {
                head.next = headL2;
                head = head.next;

                headL2 = headL2.next;
            }
        }

        // only run once while for left remaining parts
        ListNode remain = headL1 != null ? headL1 : headL2;

        while (remain != null) {
            head.next = remain;
            head = head.next;

            remain = remain.next;
        }

        return ret.next;
    }

    public static void main (String [] args) {
        MergeTwoSortedLists solution = new MergeTwoSortedLists();

//        Boolean ret = solution.mergeTwoLists();
//        System.out.println(ret);
    }
}
