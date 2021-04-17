package src.nowcoder.SwordOffer;

/**
 * @author tsf
 * @date 21-03-25
 * @desp
 * 剑指 Offer 24. 反转链表
 * 定义一个函数，输入一个链表的头节点，反转该链表并输出反转后链表的头节点。
 *
 *
 *
 * 示例:
 *
 * 输入: 1->2->3->4->5->NULL
 * 输出: 5->4->3->2->1->NULL
 *
 *
 * 限制：
 *
 * 0 <= 节点个数 <= 5000
 */

public class Offer24 {

    public ListNode ReverseList(ListNode head) {

        if (head == null) {
            return null;
        }

        ListNode revNode = null, tmp = null;
        ListNode curNode = head;

        while (curNode != null) {

            revNode = new ListNode(curNode.val);
            revNode.next = tmp;
            tmp = revNode;

            curNode = curNode.next;
        }

        return revNode;
    }

    public ListNode ReverseList2(ListNode head) {

        if (head == null) {
            return null;
        }

        ListNode curNode = head, nextNode = null;
        ListNode revNode = null;

        while (curNode != null) {
            nextNode = curNode.next;
            curNode.next = revNode;
            revNode = curNode;
            curNode = nextNode;
        }

        return  revNode;
    }

    public static void main(String[] args) {

        Offer24 offer22 = new Offer24();

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
        listNodes[5].next = null;

        ListNode head = listNodes[0];
        while (head != null) {
            System.out.println(head.val);
            head = head.next;
        }
        System.out.println("=========================");

        head = listNodes[0];
        ListNode ret = offer22.ReverseList(head);
        while (ret != null) {
            System.out.println(ret.val);
            ret = ret.next;
        }
        System.out.println("=========================");


        head = listNodes[0];
        ret = offer22.ReverseList2(head);
        while (ret != null) {
            System.out.println(ret.val);
            ret = ret.next;
        }
        System.out.println("=========================");



    }
}



