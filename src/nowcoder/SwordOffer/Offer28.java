package src.nowcoder.SwordOffer;

/**
 * @author tsf
 * @date 21-03-25
 * @desp
 * 剑指 Offer 28. 对称的二叉树
 * 请实现一个函数，用来判断一棵二叉树是不是对称的。如果一棵二叉树和它的镜像一样，那么它是对称的。
 *
 * 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。
 *
 *     1
 *    / \
 *   2   2
 *  / \ / \
 * 3  4 4  3
 * 但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的:
 *
 *     1
 *    / \
 *   2   2
 *    \   \
 *    3    3
 *
 *
 *
 * 示例 1：
 *
 * 输入：root = [1,2,2,3,4,4,3]
 * 输出：true
 * 示例 2：
 *
 * 输入：root = [1,2,2,null,3,null,3]
 * 输出：false
 *
 *
 * 限制：
 *
 * 0 <= 节点个数 <= 1000
 *
 * 注意：本题与主站 101 题相同：https://leetcode-cn.com/problems/symmetric-tree/
 */

public class Offer28 {

    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }

        return isEqual(root.left, root.right);
    }

    public boolean isEqual(TreeNode left, TreeNode right) {
        if (left == null && right == null) {  // 左右子树都到了底端
            return true;
        }

        if (left == null || right == null || left.val != right.val) {  // 只有一个子树为空或值不相等
            return false;
        }

        return isEqual(left.left, right.right) && isEqual(left.right, right.left);
    }


    public static void main(String[] args) {

        Offer28 offer28 = new Offer28();

        TreeNode[] treeNodes = new TreeNode[7];

        treeNodes[0] = new TreeNode(4);
        treeNodes[1] = new TreeNode(2);
        treeNodes[2] = new TreeNode(2);
        treeNodes[3] = new TreeNode(3);
        treeNodes[4] = new TreeNode(4);
        treeNodes[5] = new TreeNode(4);
        treeNodes[6] = new TreeNode(3);

        treeNodes[0].left = treeNodes[1];
        treeNodes[0].right = treeNodes[2];

        treeNodes[1].left = treeNodes[3];
        treeNodes[1].right = treeNodes[4];

        treeNodes[2].left = treeNodes[5];
        treeNodes[2].right = treeNodes[6];

        boolean ret = offer28.isSymmetric(treeNodes[0]);

        System.out.println(ret);
        System.out.println("==================");

        TreeNode[] treeNodes2 = new TreeNode[7];

        treeNodes2[0] = new TreeNode(4);
        treeNodes2[1] = new TreeNode(2);
        treeNodes2[2] = new TreeNode(2);
        treeNodes2[3] = new TreeNode(3);
        treeNodes2[4] = new TreeNode(4);
        treeNodes2[5] = new TreeNode(4);
        treeNodes2[6] = new TreeNode(3);

        treeNodes[0].left = treeNodes[1];
        treeNodes[0].right = treeNodes[2];

        treeNodes[1].left = treeNodes[3];
//        treeNodes[1].right = treeNodes[4];

//        treeNodes[2].left = treeNodes[5];
        treeNodes[2].right = treeNodes[6];

        ret = offer28.isSymmetric(treeNodes2[0]);

        System.out.println(ret);


    }
}



