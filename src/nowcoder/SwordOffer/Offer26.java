package src.nowcoder.SwordOffer;

/**
 * @author tsf
 * @date 21-03-25
 * @desp
 * 剑指 Offer 26. 树的子结构
 * 输入两棵二叉树A和B，判断B是不是A的子结构。(约定空树不是任意一个树的子结构)
 *
 * B是A的子结构， 即 A中有出现和B相同的结构和节点值。
 *
 * 例如:
 * 给定的树 A:
 *
 *      3
 *     / \
 *    4   5
 *   / \
 *  1   2
 * 给定的树 B：
 *
 *    4
 *   /
 *  1
 * 返回 true，因为 B 与 A 的一个子树拥有相同的结构和节点值。
 *
 * 示例 1：
 *
 * 输入：A = [1,2,3], B = [3,1]
 * 输出：false
 * 示例 2：
 *
 * 输入：A = [3,4,5,1,2], B = [4,1]
 * 输出：true
 * 限制：
 *
 * 0 <= 节点个数 <= 10000
 */

public class Offer26 {

    public boolean HasSubtree(TreeNode root1, TreeNode root2) {
        if ((root1 == null) || (root2 == null)) {  // 边界条件
            return false;
        }

        /*if ((root1.val == root2.val) && isSubTree(root1.left, root2.left) && isSubTree(root2.right, root2.right)) {
            return true;
        } else {
            return HasSubtree(root1.left, root2) || HasSubtree(root1.right, root2);
        }*/

        return isSubTree(root1, root2) || HasSubtree(root1.left, root2) || HasSubtree(root1.right, root2);

    }

    public boolean isSubTree(TreeNode node1, TreeNode node2) {
        /* 注意node2先判断 */
        if (node2 == null) {  // 如果node2到了叶结点
            return true;
        }

        if ((node1 == null) || (node1.val != node2.val)) {  // 如果node1到了叶结点
            return false;
        }

        return isSubTree(node1.left, node2.left) && isSubTree(node1.right, node2.right);  // 值相等且左右结点相同

    }

    public static void main(String[] args) {

        Offer26 offer26 = new Offer26();

        TreeNode[] treeNodes = new TreeNode[5];

        treeNodes[0] = new TreeNode(3);
        treeNodes[1] = new TreeNode(4);
        treeNodes[2] = new TreeNode(5);
        treeNodes[3] = new TreeNode(1);
        treeNodes[4] = new TreeNode(2);

        treeNodes[0].left = treeNodes[1];
        treeNodes[0].right = treeNodes[2];

        treeNodes[1].left = treeNodes[3];
        treeNodes[1].right = treeNodes[4];


        TreeNode[] subTreeNodes = new TreeNode[2];

        subTreeNodes[0] = new TreeNode(4);
        subTreeNodes[1] = new TreeNode(1);

        subTreeNodes[0].left = treeNodes[1];

        boolean ret = offer26.HasSubtree(treeNodes[0], subTreeNodes[0]);

        System.out.println(ret);


    }
}



