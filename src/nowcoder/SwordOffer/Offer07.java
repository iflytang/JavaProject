package src.nowcoder.SwordOffer;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author tsf
 * @date 21-03-25
 * @desp 剑指 Offer 06. 从尾到头打印链表。
 * 题目描述
 * 输入某二叉树的前序遍历和中序遍历的结果，请重建该二叉树。假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
 *
 * 例如，给出
 *
 * 前序遍历 preorder = [3,9,20,15,7]
 * 中序遍历 inorder = [9,3,15,20,7]
 * 返回如下的二叉树：
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 *
 * 限制：
 *
 * 0 <= 节点个数 <= 5000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/zhong-jian-er-cha-shu-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */



public class Offer07 {

    // 定义全局哈希index，标记出根结点在中序遍历的位置
    HashMap<Integer, Integer> rootMap = new HashMap<>();

    public TreeNode myBinaryTree(int[] pre, int[] in, int leftStart, int leftEnd, int rightStart, int rightEnd) {

        // 如果超过叶子结点
        if (leftStart > leftEnd) {
            return null;
        }
        
        // 根据前序遍历得到根结点
        int curRoot = pre[leftStart];

        // 根据根结点，得到其在中序遍历中的位置
        int inRootIdx = rootMap.get(curRoot);

        // 得到左子树的长度
        int leftTreeLen = inRootIdx - rightStart;

        // 创建根结点
        TreeNode rootTreeNode = new TreeNode(curRoot);

        // 连接左子树
        rootTreeNode.left = myBinaryTree(pre, in,  leftStart + 1, leftStart + leftTreeLen, rightStart, inRootIdx - 1);

        // 连接右子树
        rootTreeNode.right = myBinaryTree(pre, in, leftStart + leftTreeLen + 1, leftEnd, inRootIdx + 1, rightEnd);

        return rootTreeNode;
    }

    public TreeNode reConstructBinaryTree(int [] pre,int [] in) {

        int num = pre.length;

        for (int i = 0; i < num; i++) {
            rootMap.put(in[i], i);
        }

        return myBinaryTree(pre, in, 0, num - 1, 0, num - 1);
    }

    public static void main(String[] args) {

        int [] pre = {3, 9, 20, 15, 7};
        int [] in = {9, 3, 15, 20, 7};

        ListNode listNodes = null;

        Offer07 offer07 = new Offer07();
        TreeNode res = offer07.reConstructBinaryTree(pre, in);
        System.out.println(res);

    }
}

/* Definition for binary tree */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}

