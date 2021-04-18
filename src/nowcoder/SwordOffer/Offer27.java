package src.nowcoder.SwordOffer;

/**
 * @author tsf
 * @date 21-03-25
 * @desp
 * 剑指 Offer 27. 二叉树的镜像
 * 请完成一个函数，输入一个二叉树，该函数输出它的镜像。
 *
 * 例如输入：
 *
 *      4
 *    /   \
 *   2     7
 *  / \   / \
 * 1   3 6   9
 * 镜像输出：
 *
 *      4
 *    /   \
 *   7     2
 *  / \   / \
 * 9   6 3   1
 *
 *
 *
 * 示例 1：
 *
 * 输入：root = [4,2,7,1,3,6,9]
 * 输出：[4,7,2,9,6,3,1]
 *
 *
 * 限制：
 *
 * 0 <= 节点个数 <= 1000
 */

public class Offer27 {

    public TreeNode Mirror (TreeNode pRoot) {
        // write code here

        if (pRoot == null) {  // 递归基
            return pRoot;
        }

        swap(pRoot);

        Mirror(pRoot.left);  // 左子树
        Mirror(pRoot.right);  // 右子树

        return pRoot;
    }

    public TreeNode swap (TreeNode pRoot) {  // 交换左右子树的位置
        TreeNode tmp;
        tmp = pRoot.right;
        pRoot.right = pRoot.left;
        pRoot.left = tmp;

        return pRoot;
    }

    public static void main(String[] args) {

        Offer27 offer27 = new Offer27();

        TreeNode[] treeNodes = new TreeNode[7];

        treeNodes[0] = new TreeNode(4);
        treeNodes[1] = new TreeNode(2);
        treeNodes[2] = new TreeNode(7);
        treeNodes[3] = new TreeNode(1);
        treeNodes[4] = new TreeNode(3);
        treeNodes[5] = new TreeNode(6);
        treeNodes[6] = new TreeNode(9);

        treeNodes[0].left = treeNodes[1];
        treeNodes[0].right = treeNodes[2];

        treeNodes[1].left = treeNodes[3];
        treeNodes[1].right = treeNodes[4];

        treeNodes[2].left = treeNodes[5];
        treeNodes[2].right = treeNodes[6];

        TreeNode ret = offer27.Mirror(treeNodes[0]);

        System.out.println(ret);


    }
}



