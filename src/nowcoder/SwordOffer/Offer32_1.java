package src.nowcoder.SwordOffer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author tsf
 * @date 21-03-25
 * @desp
 *
 * 剑指 Offer 32 - I. 从上到下打印二叉树
 * 从上到下打印出二叉树的每个节点，同一层的节点按照从左到右的顺序打印。
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
 * 返回：
 *
 * [3,9,20,15,7]
 *
 *
 * 提示：
 *
 * 节点总数 <= 1000
 */

public class Offer32_1 {

    public int[] levelOrder(TreeNode root) {
        if (root == null) {
            return new int[0];
        }

        ArrayList<Integer> list = new ArrayList<>();
        // 模拟队列特性，这里队列也可用Queue<TreeNode> queue = new LinkedList<>();
        ArrayList<TreeNode> queue = new ArrayList<>();
//        Queue<TreeNode> queue = new LinkedList<>();

        queue.add(root);
        while (queue.size() != 0) {
            TreeNode poppedNode = queue.remove(0);  // 先进先出
            list.add(poppedNode.val);

            if (poppedNode.left != null) {
                queue.add(poppedNode.left);
            }

            if (poppedNode.right != null) {
                queue.add(poppedNode.right);
            }
        }

        int listLen = list.size();
        int [] ret = new int[listLen];
        for (int i = 0; i < listLen; i++) {
            ret[i] = list.get(i);
        }

        return ret;
    }

    public static void main(String[] args) {

        Offer32_1 offer32_1 = new Offer32_1();

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

        int [] ret = offer32_1.levelOrder(treeNodes[0]);
        System.out.println(Arrays.toString(ret));
    }
}



