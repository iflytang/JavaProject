package src.interview;

import java.io.*;
import java.util.*;

public class kthReverseNode {

    public static void main (String [] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = null;

        while ((str = br.readLine()) != null) {
            int n = Integer.parseInt(str);

            String [] substr = br.readLine().split(" ");
            ListNode dummy = new ListNode(-1);  // dummy node
            ListNode head = dummy;
            for (int i = 0; i < substr.length; i++) {
                ListNode node = new ListNode(Integer.parseInt(substr[i]));
                dummy.next = node;

                dummy = dummy.next;
            }

            int k = Integer.parseInt(br.readLine());

            // 双指针法
            boolean invalid = false;
            ListNode fast = head.next;
            for (int i = 0; i < k; i++) {
                if (fast != null) {
                    fast = fast.next;
                } else {
                    invalid = true;
                    break;
                }
            }

            if (invalid || k == 0) {
                System.out.println(0);
                continue;
            }

            ListNode slow = head.next;
            while (fast != null) {
                fast = fast.next;
                slow = slow.next;
            }

            System.out.println(slow.val);
        }

    }

}

class ListNode {
    int val;
    ListNode next;

    ListNode (int val) {
        this.val = val;
    }
}
