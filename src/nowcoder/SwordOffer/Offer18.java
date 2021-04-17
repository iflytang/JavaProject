package src.nowcoder.SwordOffer;

/**
 * @author tsf
 * @date 21-03-25
 * @desp
 * 剑指 Offer 18. 删除链表的节点
 * 给定单向链表的头指针和一个要删除的节点的值，定义一个函数删除该节点。
 *
 * 返回删除后的链表的头节点。
 *
 * 注意：此题对比原题有改动
 *
 * 示例 1:
 *
 * 输入: head = [4,5,1,9], val = 5
 * 输出: [4,1,9]
 * 解释: 给定你链表中值为 5 的第二个节点，那么在调用了你的函数之后，该链表应变为 4 -> 1 -> 9.
 * 示例 2:
 *
 * 输入: head = [4,5,1,9], val = 1
 * 输出: [4,5,9]
 * 解释: 给定你链表中值为 1 的第三个节点，那么在调用了你的函数之后，该链表应变为 4 -> 5 -> 9.
 *
 * 说明：
 *
 * 题目保证链表中节点的值互不相同
 * 若使用 C 或 C++ 语言，你不需要 free 或 delete 被删除的节点
 */


public class Offer18 {


    public ListNode deleteNode(ListNode head, int val) {

        if (head == null) {
            return null;
        }

        ListNode node = head;
        ListNode preNode = null;
        for (int i = 0; node != null; i++) {
            if (node.val == val) {
                if (i == 0) { // first node
                    node = node.next;
                }

                if (i > 0) {
                    preNode.next = node.next;
                    node = head;
                }

                break;
            }

            preNode = node;
            node = node.next;
        }

        return node;
    }

    public ListNode deleteNode2(ListNode head, int val) {
        if(head.val == val)
            return head.next;

        ListNode pre = head, cur = head.next;

        while(cur != null && cur.val != val) {
            pre = cur;
            cur = cur.next;
        }

        if(cur != null)
            pre.next = cur.next;

        return head;
    }

    public ListNode deleteNode3(ListNode head, int val) {  // 单指针
        if (head == null)
            return null;

        if (head.val == val)
            return head.next;

        ListNode cur = head;
        while (cur.next != null && cur.next.val != val)
            cur = cur.next;

        if (cur.next != null)
            cur.next = cur.next.next;
        
        return head;
    }


    public static void main(String[] args) {

        Offer18 offer18 = new Offer18();

       ListNode[] listNodes = new ListNode[4];

        listNodes[0] = new ListNode(4);
        listNodes[1] = new ListNode(5);
        listNodes[2] = new ListNode(1);
        listNodes[3] = new ListNode(9);

        listNodes[0].next = listNodes[1];
        listNodes[1].next = listNodes[2];
        listNodes[2].next = listNodes[3];

        ListNode head = listNodes[0];
        while (head != null) {
            System.out.println(head.val);
            head = head.next;
        }
        System.out.println("=========================");

        head = listNodes[0];
        int val = 1;
        ListNode ret = offer18.deleteNode(head, val);
        while (ret != null) {
            System.out.println(ret.val);
            ret = ret.next;
        }
        System.out.println("=========================");



    }
}



