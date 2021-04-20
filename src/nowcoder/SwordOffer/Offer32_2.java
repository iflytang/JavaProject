package src.nowcoder.SwordOffer;

import java.lang.reflect.AnnotatedElement;
import java.util.*;

/**
 * @author tsf
 * @date 21-03-25
 * @desp
 * 剑指 Offer 32 - II. 从上到下打印二叉树 II
 * 从上到下按层打印二叉树，同一层的节点按从左到右的顺序打印，每一层打印到一行。
 *
 *
 *
 * 例如:
 * 给定二叉树: [3,9,20,null,null,15,7],
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回其层次遍历结果：
 *
 * [
 *   [3],
 *   [9,20],
 *   [15,7]
 * ]
 *
 *
 * 提示：
 *
 * 节点总数 <= 1000
 * 注意：本题与主站 102 题相同：https://leetcode-cn.com/problems/binary-tree-level-order-traversal/
 */

public class Offer32_2 {

    public List<List<Integer>> levelOrder(TreeNode root) {
       if (root == null) {
           return  new ArrayList<>();
       }

       List<List<Integer>> res = new ArrayList<>();
       Queue<TreeNode> queue = new LinkedList<>();


       queue.add(root);
       while (!queue.isEmpty()) {

           List<Integer> levelList = new ArrayList<>();
           for (int i = queue.size(); i > 0; i--) {

               TreeNode poppedNode = queue.poll();
               levelList.add(poppedNode.val);

               if (poppedNode.left != null) {
                   queue.add(poppedNode.left);
               }

               if (poppedNode.right != null) {
                   queue.add(poppedNode.right);
               }
           }
           res.add(levelList);
       }

       return res;
    }

    public static void main(String[] args) {

        Offer32_2 offer32_2 = new Offer32_2();

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

        List<List<Integer>> ret = offer32_2.levelOrder(treeNodes[0]);
        for (List<Integer> element : ret) {
            System.out.println(element.toString());
        }
    }
}



