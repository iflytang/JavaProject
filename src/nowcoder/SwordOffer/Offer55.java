package src.nowcoder.SwordOffer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author tsf
 * @date 21-03-25
 * @desp
 * 剑指 Offer 55 - I. 二叉树的深度
 * 输入一棵二叉树的根节点，求该树的深度。从根节点到叶节点依次经过的节点（含根、叶节点）形成树的一条路径，最长路径的长度为树的深度。
 *
 * 例如：
 *
 * 给定二叉树 [3,9,20,null,null,15,7]，
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回它的最大深度 3 。
 *
 *
 *
 * 提示：
 *
 * 节点总数 <= 10000
 * 注意：本题与主站 104 题相同：https://leetcode-cn.com/problems/maximum-depth-of-binary-tree/
 */

public class Offer55 {

    public int maxDepth(TreeNode root) {  // 层次输出
        if (root == null) {
            return 0;
        }

        Queue<TreeNode> queue = new LinkedList<>();

        int depth = 0;
        queue.add(root);

        while (!queue.isEmpty()) {


            for (int i = queue.size(); i > 0; i--) {  // pop出当前层的所有结点

                TreeNode tmp = queue.poll();

                if (tmp.left != null) {
                    queue.add(tmp.left);
                }

                if (tmp.right != null) {
                    queue.add(tmp.right);
                }
            }

            depth++;
        }

        return depth;
    }

    int maxDep = 0;
    public int maxDepth2(TreeNode root) {

        int curDep = 0;

        dfs(root, curDep);

        return maxDep;
    }

    public void dfs(TreeNode root, int curDep) {
        if (root == null) {
            return;
        }

        curDep++;
        dfs(root.left, curDep);
        // res = root.val;
//        curDep++;
        dfs(root.right, curDep);

        maxDep = Math.max(maxDep, curDep);
    }

    public int maxDepth3(TreeNode root) { // 后序遍历
        if(root == null){
            return 0;
        }

        int left = maxDepth(root.left);
        int right = maxDepth(root.right);

        return Math.max(left, right) + 1;
    }

        public static void main(String[] args) {

        Offer55 offer55 = new Offer55();

        TreeNode[] treeNodes = new TreeNode[5];

        treeNodes[0] = new TreeNode(3);
        treeNodes[1] = new TreeNode(9);
        treeNodes[2] = new TreeNode(20);
        treeNodes[3] = new TreeNode(15);
        treeNodes[4] = new TreeNode(7);

        treeNodes[0].left = treeNodes[1];
        treeNodes[0].right = treeNodes[2];

        treeNodes[2].left = treeNodes[3];
        treeNodes[2].right = treeNodes[4];

        int ret = offer55.maxDepth(treeNodes[0]);
        System.out.println(ret);
        System.out.println("====================");

        ret = offer55.maxDepth2(treeNodes[0]);
        System.out.println(ret);
        System.out.println("====================");
    }
}





