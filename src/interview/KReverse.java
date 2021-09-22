package src.interview;

import java.util.ArrayList;

/**
 * 单链表，按照1 2 3 .. N分组逆序
 * 比如 1 [2, 3] [4, 5, 6], 7
 * 结果 7 4 5 6 2 3 1
 */

public class KReverse {

    public static void main (String [] args) {
        ListNode [] list = new ListNode [8];

        list[0] = new ListNode(1);
        list[1] = new ListNode(2);
        list[2] = new ListNode(3);
        list[3] = new ListNode(4);
        list[4] = new ListNode(5);
        list[5] = new ListNode(6);
        list[6] = new ListNode(7);
        list[7] = new ListNode(8);

        list[0].next = list[1];
        list[1].next = list[2];
        list[2].next = list[3];
        list[3].next = list[4];
        list[4].next = list[5];
        list[5].next = list[6];
        list[6].next = list[7];

        // 输入参数测试
        ListNode cur = list[0];
        while (cur != null) {
            System.out.print(cur.val + " ");
            cur = cur.next;
        }
        System.out.println();

        // 递增分段
        ArrayList<Integer> seg = new ArrayList<>();
        cur = list[0];
        int base = 1;
        int cnt = 0;
        while (cur != null) {
            cnt++;

            System.out.print(cur.val + " ");

            if (cnt == base) {
                System.out.print(", ");
                seg.add(base);
                base++;
                cnt = 0;
            }

            cur = cur.next;
        }
        System.out.println();

        if (cnt > 0) {
            seg.add(cnt);
        }

        // 将分段保存为数组
        ListNode head = list[0];
        int size = seg.size();
        int [] k = new int[size];
        for (int i = 0; i < size; i++) {
            k[i] = seg.get(i);
        }

        ListNode tail = reverseKGroup(head, k);  // 按分段数组翻转
        ListNode ret = reverseListNode(tail);    // 再整个翻转一次

        // 输出结果测试
        while (ret != null) {
            System.out.print(ret.val + " ");
            ret = ret.next;
        }
    }

    public static ListNode reverseKGroup(ListNode head, int [] k) {

        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        ListNode pre = dummy;
        ListNode rightNode = dummy;
        int idx = 0;

        while (rightNode != null && idx < k.length) { // 修改1
            // 如果不足k个节点，则退出
            for (int i = 0; i < k[idx++]; i++) {  // 修改2
                if (rightNode != null) {
                    rightNode = rightNode.next;
                } else {
                    break;
                }
            }

            if (rightNode == null) {
                break;
            }

            // 确定反转链表区间[leftNode, rightNode]，以及它的前一个节点pre和后一节点tail
            ListNode leftNode = pre.next;
            ListNode tail = rightNode.next;

            // 截断链表
            pre.next = null;
            rightNode.next = null;

            // 反转链表
            reverseListNode(leftNode);

            // 重新链接链表
            pre.next = rightNode;
            leftNode.next = tail;

            // 进入下一个区间
            pre = leftNode;
            rightNode = pre;
        }

        return dummy.next;
    }

    public static ListNode reverseListNode(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        ListNode next = null;

        while (cur != null) {
            next = cur.next;
            cur.next = pre;

            pre = cur;
            cur = next;
        }

        return pre;
    }

}

