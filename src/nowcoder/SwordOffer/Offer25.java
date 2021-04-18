package src.nowcoder.SwordOffer;

/**
 * @author tsf
 * @date 21-03-25
 * @desp
 * 剑指 Offer 25. 合并两个排序的链表
 * 输入两个递增排序的链表，合并这两个链表并使新链表中的节点仍然是递增排序的。
 *
 * 示例1：
 *
 * 输入：1->2->4, 1->3->4
 * 输出：1->1->2->3->4->4
 * 限制：
 *
 * 0 <= 链表长度 <= 1000
 */

public class Offer25 {

    public ListNode Merge(ListNode list1,ListNode list2) {

        if (list1 == null) {
            return list2;
        }

        if (list2 == null) {
            return list1;
        }


        ListNode head = new ListNode(0);
        ListNode curNode = head;

        while ((list1 != null) && (list2 != null)) {

            if (list1.val <= list2.val) {
                curNode.next = list1;
                list1 = list1.next;

            } else {
                curNode.next = list2;
                list2 = list2.next;
            }

            curNode = curNode.next;

        }

        curNode.next = list1 == null ? list2 : list1;

        return head.next;

    }

    public static void main(String[] args) {

        Offer25 offer25 = new Offer25();

        ListNode[] listNodes = new ListNode[4];

        listNodes[0] = new ListNode(1);
        listNodes[1] = new ListNode(3);
        listNodes[2] = new ListNode(5);
        listNodes[3] = new ListNode(7);

        listNodes[0].next = listNodes[1];
        listNodes[1].next = listNodes[2];
        listNodes[2].next = listNodes[3];

        ListNode[] listNodes2 = new ListNode[6];

        listNodes2[0] = new ListNode(1);
        listNodes2[1] = new ListNode(4);
        listNodes2[2] = new ListNode(6);
        listNodes2[3] = new ListNode(8);
        listNodes2[4] = new ListNode(10);
        listNodes2[5] = new ListNode(12);

        listNodes2[0].next = listNodes2[1];
        listNodes2[1].next = listNodes2[2];
        listNodes2[2].next = listNodes2[3];
        listNodes2[3].next = listNodes2[4];
        listNodes2[4].next = listNodes2[5];

        ListNode head1 = listNodes[0];
        ListNode head2 = listNodes2[0];
        ListNode ret = offer25.Merge(head1, head2);
        while (ret != null) {
            System.out.println(ret.val);
            ret = ret.next;
        }
        System.out.println("=========================");



    }
}



