package src.nowcoder.SwordOffer;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author tsf
 * @date 21-03-25
 * @desp
 * 剑指 Offer 55 - II. 平衡二叉树
 * 输入一棵二叉树的根节点，判断该树是不是平衡二叉树。
 * 如果某二叉树中任意节点的左右子树的深度相差不超过1，那么它就是一棵平衡二叉树。
 *
 * 示例 1:
 *
 * 给定二叉树 [3,9,20,null,null,15,7]
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回 true 。
 *
 * 示例 2:
 *
 * 给定二叉树 [1,2,2,3,3,null,null,4,4]
 *
 *        1
 *       / \
 *      2   2
 *     / \
 *    3   3
 *   / \
 *  4   4
 * 返回 false 。
 *
 * 限制：
 *
 * 0 <= 树的结点个数 <= 10000
 * 注意：本题与主站 110 题相同：https://leetcode-cn.com/problems/balanced-binary-tree/
 */

public class Offer55_2 {

    public boolean isBalanced(TreeNode root) {
        return recur(root) != -1;  // 如果等于-1，说明不平衡了
    }

    public int recur(TreeNode root) {  // 后序遍历 + 剪枝
        if (root == null) {
            return 0;
        }

        int left = recur(root.left);
        
        if (left == -1) {  // 如果左子树已经不平衡，停止遍历
            return -1;
        }

        int right = recur(root.right);

        if (right == -1) {  // 如果左子树已经不平衡，停止遍历
            return -1;
        }

        // 检查当前树是否平衡。如果平衡，向上返回当前最大深度
        int absDepth = Math.abs(left - right);        
        return absDepth <= 1 ? Math.max(left, right) + 1 : -1;
    }

    public boolean isBalanced2(TreeNode root) {  // 按深度比较每个左右子树是否符合要求
        if (root == null) {
            return true;
        }

        // 检查当前根结点是否满足
        int left = depth(root.left);
        int right = depth(root.right);
        int absDepth = Math.abs(left - right);
        boolean rootTree = (absDepth <= 1);
        
        // 检查左结点
        boolean leftTree = isBalanced2(root.left);

        // 检查右结点
        boolean rightTree = isBalanced2(root.right);

        return rootTree && leftTree && rightTree;
    }

    public int depth(TreeNode root) {  // 后序遍历
        if (root == null) {
            return 0;
        }

        int left = depth(root.left);
        int right = depth(root.right);

        return Math.max(left, right) + 1;
    }

    public static void main(String[] args) {

        Offer55_2 offer55 = new Offer55_2();

        TreeNode[] treeNodes = new TreeNode[5];

        treeNodes[0] = new TreeNode(3);
        treeNodes[1] = new TreeNode(9);
        treeNodes[2] = new TreeNode(2);
        treeNodes[3] = new TreeNode(1);
        treeNodes[4] = new TreeNode(7);

        treeNodes[0].left = treeNodes[1];
        treeNodes[0].right = treeNodes[2];

        treeNodes[2].left = treeNodes[3];
        treeNodes[2].right = treeNodes[4];

        Boolean ret = offer55.isBalanced(treeNodes[0]);
        System.out.println(ret);
        System.out.println("====================");

        ret = offer55.isBalanced2(treeNodes[0]);
        System.out.println(ret);
        System.out.println("====================");
    }
}





