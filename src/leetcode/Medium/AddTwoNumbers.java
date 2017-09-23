package leetcode.Medium;

/**
 * Created by tsf on 17-9-18.
 */


class  ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x;}
}


public class AddTwoNumbers {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        ListNode node = new ListNode(0);
        ListNode sumNode;
        int sum = 0;
        int carry = 0;
        int val1 = l1.val;
        int val2 = l2.val;

        sumNode = node;

        while(l1 != null || l2 != null) {

            sum = val1 + val2 + carry;
            carry = sum / 10;
            sumNode.next = new ListNode(sum % 10);
            sumNode = sumNode.next;

            if(l1.next == null && l2.next == null) {
                break;
            }

            if(l1.next != null)
            {
                l1 = l1.next;
                val1 = l1.val;
            } else {
                l1.next = null;
                val1 = 0;
            }

            if(l2.next != null)
            {
                l2 = l2.next;
                val2 = l2.val;
            } else {
                l2.next = null;
                val2 = 0;
            }


//            System.out.println("sumNode.val:" + sumNode.val);

        }

        if(carry > 0) {
            sumNode.next = new ListNode(carry);
        }

        return node.next;
    }


    public static void main(String[] args) {

        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(8);


        ListNode l2 = new ListNode(0);

        System.out.println("l1:" + l1.val + " " + l1.next.val);
        System.out.println("l2:" + l2.val);

        AddTwoNumbers add = new AddTwoNumbers();
        ListNode sum = add.addTwoNumbers(l1, l2);
        //System.out.println("sum:" + sum.val + " " + sum.next.val  + " "+ sum.next.next.val);

        while (sum != null) {
            System.out.println(sum.val);
            sum = sum.next;
        }
    }
}
