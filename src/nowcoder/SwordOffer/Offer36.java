package src.nowcoder.SwordOffer;

import java.util.List;

/**
 * @author tsf
 * @date 21-03-25
 * @desp
 * 剑指 Offer 36. 二叉搜索树与双向链表
 * 输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的循环双向链表。要求不能创建任何新的节点，只能调整树中节点指针的指向。
 *
 * 为了让您更好地理解问题，以下面的二叉搜索树为例：
 *
 * 我们希望将这个二叉搜索树转化为双向循环链表。链表中的每个节点都有一个前驱和后继指针。
 * 对于双向循环链表，第一个节点的前驱是最后一个节点，最后一个节点的后继是第一个节点。
 *
 * 下图展示了上面的二叉搜索树转化成的链表。“head” 表示指向链表中有最小元素的节点。
 *
 * 特别地，我们希望可以就地完成转换操作。当转化完成以后，树中节点的左指针需要指向前驱，
 * 树中节点的右指针需要指向后继。还需要返回链表中的第一个节点的指针。
 *
 * 注意：本题与主站 426 题相同：
 * https://leetcode-cn.com/problems/convert-binary-search-tree-to-sorted-doubly-linked-list/
 *
 * 注意：此题对比原题有改动。
 */

public class Offer36 {

    TreeNode pHead = null, preNode = null;
    public TreeNode treeToDoublyList(TreeNode root) {
       if (root == null) {
           return root;
       }

       recur(root);

       // 当前preNode位于尾结点
       // 进行头节点和尾节点的相互指向，这两句的顺序也是可以颠倒的
       pHead.left = preNode; 
       preNode.right = pHead;

       return pHead;
    }

    public void recur(TreeNode curNode) {
        if (curNode == null) {
            return;
        }

        // 左子树遍历
        recur(curNode.left);

        // 根结点
        // pre用于记录双向链表中位于cur左侧的节点，即上一次迭代中的cur,当pre==null时，
        // cur左侧没有节点,即此时cur为双向链表中的头节点
        if (preNode == null) {
            pHead = curNode;
        } else {
            preNode.right = curNode;
        }
        curNode.left = preNode;

        // 右子树遍历
        preNode = curNode;
        recur(curNode.right);
    }

    public static void main(String[] args) {

        Offer36 offer36 = new Offer36();

        TreeNode[] treeNodes = new TreeNode[7];

        treeNodes[0] = new TreeNode(10);
        treeNodes[1] = new TreeNode(6);
        treeNodes[2] = new TreeNode(14);
        treeNodes[3] = new TreeNode(4);
        treeNodes[4] = new TreeNode(8);
        treeNodes[5] = new TreeNode(12);
        treeNodes[6] = new TreeNode(16);

        treeNodes[0].left = treeNodes[1];
        treeNodes[0].right = treeNodes[2];

        treeNodes[1].left = treeNodes[3];
        treeNodes[1].right = treeNodes[4];

        treeNodes[2].left = treeNodes[5];
        treeNodes[2].right = treeNodes[6];

        TreeNode ret = offer36.treeToDoublyList(treeNodes[0]);
        for (int i = 0; i < 6; i++) {
            System.out.println(ret.val);
            ret = ret.right;
        }

    }
}





