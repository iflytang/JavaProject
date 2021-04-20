package src.nowcoder.SwordOffer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tsf
 * @date 21-03-25
 * @desp
 *
 * 剑指 Offer 34. 二叉树中和为某一值的路径
 * 输入一棵二叉树和一个整数，打印出二叉树中节点值的和为输入整数的所有路径。
 * 从树的根节点开始往下一直到叶节点所经过的节点形成一条路径。
 *
 * 示例:
 * 给定如下二叉树，以及目标和 target = 22，
 *
 *               5
 *              / \
 *             4   8
 *            /   / \
 *           11  13  4
 *          /  \    / \
 *         7    2  5   1
 * 返回:
 *
 * [
 *    [5,4,11,2],
 *    [5,8,4,5]
 * ]
 *
 *
 * 提示：
 *
 * 节点总数 <= 10000
 * 注意：本题与主站 113 题相同：https://leetcode-cn.com/problems/path-sum-ii/
 */

public class Offer34 {

    ArrayList<ArrayList<Integer>> pathSet = new ArrayList<>();
    ArrayList<Integer> path = new ArrayList<>();

    public ArrayList<ArrayList<Integer>> FindPath(TreeNode root,int target) {

        if (root == null) { // 边界条件，注意target可能为负数，故root.val > target不是边界条件
            return pathSet;
        }

        return dfs(root, target);
    }

    public ArrayList<ArrayList<Integer>> dfs(TreeNode root, int target) {
        path.add(root.val);  // 向下遍历

        target = target - root.val;

        /* 路径记录 */
        if (target == 0 && root.left == null && root.right == null) {
            pathSet.add(new ArrayList<>(path));  // 叶子结点
        }

        /* 先序遍历 */
        if (root.left != null) {
            dfs(root.left, target);  // 递归左子树
        }

        if (root.right != null) {
            dfs(root.right, target);  // 递归右子树
        }

        /* 向上回溯 */
        path.remove(path.size() - 1);  // 回溯到父结点，要删除下一结点

        return pathSet;
    }

    /* 如果Target被设置为全局遍历或C/C++中的指针/引用传参时，
     * 需手动恢复回溯状态，否则出栈时自动恢复. 注意dfs()和dfs2()的区别 */
    int x;
    public ArrayList<ArrayList<Integer>> dfs2(TreeNode root, int target) {
        path.add(root.val);  // 向下遍历

//        target = target - root.val;
        x = x - root.val;

        /* 路径记录 */
        if (x == 0 && root.left == null && root.right == null) {
            pathSet.add(new ArrayList<>(path));  // 叶子结点
        }

        /* 先序遍历 */
        if (root.left != null) {
//            dfs(root.left, target);  // 递归左子树
            dfs(root.left, x);  // 递归左子树
        }

        if (root.right != null) {
//            dfs(root.right, target);  // 递归右子树
            dfs(root.right, x);  // 递归右子树
        }

        /* 向上回溯 */
        path.remove(path.size() - 1);  // 回溯到父结点，要删除下一结点
        x = x + root.val;  // 全局变量，主动恢复回溯结点状态

        return pathSet;
    }

    public static void main(String[] args) {

        Offer34 offer34 = new Offer34();
        TreeNode[] treeNodes = new TreeNode[10];

        treeNodes[0] = new TreeNode(5);
        treeNodes[1] = new TreeNode(4);
        treeNodes[2] = new TreeNode(8);
        treeNodes[3] = new TreeNode(11);
        treeNodes[4] = new TreeNode(13);
        treeNodes[5] = new TreeNode(4);
        treeNodes[6] = new TreeNode(7);
        treeNodes[7] = new TreeNode(2);
        treeNodes[8] = new TreeNode(5);
        treeNodes[9] = new TreeNode(1);

        treeNodes[0].left = treeNodes[1];
        treeNodes[0].right = treeNodes[2];

        treeNodes[1].left = treeNodes[3];

        treeNodes[2].left = treeNodes[4];
        treeNodes[2].right = treeNodes[5];

        treeNodes[3].left = treeNodes[6];
        treeNodes[3].right = treeNodes[7];

        treeNodes[5].left = treeNodes[8];
        treeNodes[5].right = treeNodes[9];

        int target = 22;
        ArrayList<ArrayList<Integer>> ret = offer34.FindPath(treeNodes[0], target);
        for (List<Integer> element : ret) {
            System.out.println(element.toString());
        }

    }
}



