package src.nowcoder.SwordOffer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author tsf
 * @date 21-03-25
 * @desp
 * 剑指 Offer 32 - III. 从上到下打印二叉树 III
 * 请实现一个函数按照之字形顺序打印二叉树，即第一行按照从左到右的顺序打印，
 * 第二层按照从右到左的顺序打印，第三行再按照从左到右的顺序打印，其他行以此类推。
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
 *   [20,9],
 *   [15,7]
 * ]
 *
 *
 * 提示：
 *
 * 节点总数 <= 1000
 */

public class Offer32_3 {

    public List<List<Integer>> levelOrder(TreeNode root) {
       if (root == null) {  // 边界条件
           return  new ArrayList<>();
       }

       List<List<Integer>> res = new ArrayList<>();
       Queue<TreeNode> queue = new LinkedList<>();


       queue.add(root);
       boolean turn_flag = true;
       while (!queue.isEmpty()) {

           List<Integer> levelList = new ArrayList<>();
           for (int i = queue.size(); i > 0; i--) {
               TreeNode poppedNode = queue.poll();

               if (turn_flag) {  // 根据奇偶决定插入数据顺序（奇：顺序；偶：逆序）
                   levelList.add(poppedNode.val);
               } else {
                   levelList.add(0, poppedNode.val);
               }

               if (poppedNode.left != null) {
                   queue.add(poppedNode.left);
               }

               if (poppedNode.right != null) {
                   queue.add(poppedNode.right);
               }

           }
           res.add(levelList);
           turn_flag = !turn_flag;
       }

       return res;
    }

    public static void main(String[] args) {

        Offer32_3 offer32_3 = new Offer32_3();

        TreeNode[] treeNodes = new TreeNode[7];

        treeNodes[0] = new TreeNode(8);
        treeNodes[1] = new TreeNode(6);
        treeNodes[2] = new TreeNode(10);
        treeNodes[3] = new TreeNode(5);
        treeNodes[4] = new TreeNode(7);
        treeNodes[5] = new TreeNode(9);
        treeNodes[6] = new TreeNode(11);

        treeNodes[0].left = treeNodes[1];
        treeNodes[0].right = treeNodes[2];

        treeNodes[1].left = treeNodes[3];
        treeNodes[1].right = treeNodes[4];

        treeNodes[2].left = treeNodes[5];
        treeNodes[2].right = treeNodes[6];

        List<List<Integer>> ret = offer32_3.levelOrder(treeNodes[0]);
        for (List<Integer> element : ret) {
            System.out.println(element.toString());
        }
    }
}



