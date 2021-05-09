package src.nowcoder.SwordOffer;

import java.util.ArrayList;

/**
 * @author tsf
 * @date 21-03-25
 * @desp
 * 剑指 Offer 54. 二叉搜索树的第k大节点
 * 给定一棵二叉搜索树，请找出其中第k大的节点。
 *
 * 示例 1:
 *
 * 输入: root = [3,1,4,null,2], k = 1
 *    3
 *   / \
 *  1   4
 *   \
 *    2
 * 输出: 4
 * 示例 2:
 *
 * 输入: root = [5,3,6,2,4,null,null,1], k = 3
 *        5
 *       / \
 *      3   6
 *     / \
 *    2   4
 *   /
 *  1
 * 输出: 4
 *
 *
 * 限制：
 *
 * 1 ≤ k ≤ 二叉搜索树元素个数
 */

public class Offer54 {

    ArrayList<Integer> list = new ArrayList<>();
    public int kthLargest(TreeNode root, int k) {
        midOrder(root);

        System.out.println(list.toString());
        return list.get(list.size() - k);  // 第k大
    }

    public void midOrder(TreeNode root) {  // 中序遍历，二叉搜索树是有序的
        if (root == null)
            return;
        
//        list.add(root.val);  // 此处是先序遍历
        midOrder(root.left);
        list.add(root.val);  // 此处是中序遍历
        midOrder(root.right);
    }

    int cnt;  // 需要设置为全局变量
    int res;
    public int kthLargest2(TreeNode root, int k) {
        cnt = k;
        res = -1;
        dfs(root);

        return res;
    }

    public void dfs(TreeNode root) {  // 逆序中序遍历，即<右，根，左>
        if (root == null/* || cnt <= 0*/) {  // cnt<=0 可以考虑
            return;
        }

        dfs(root.right);

        cnt--;
        System.out.println("cnt: " + cnt + ", res: " + root.val);
        if (cnt == 0) {  // 剪枝，提前返回
            res = root.val;
            return;
        }

        dfs(root.left);
    }

    public static void main(String[] args) {

        Offer54 offer54 = new Offer54();

        TreeNode[] treeNodes = new TreeNode[7];

        treeNodes[0] = new TreeNode(5);
        treeNodes[1] = new TreeNode(3);
        treeNodes[2] = new TreeNode(6);
        treeNodes[3] = new TreeNode(2);
        treeNodes[4] = new TreeNode(4);
        treeNodes[5] = new TreeNode(1);

        treeNodes[0].left = treeNodes[1];
        treeNodes[0].right = treeNodes[2];

        treeNodes[1].left = treeNodes[3];
        treeNodes[1].right = treeNodes[4];

        treeNodes[3].left = treeNodes[5];

        int k = 3;
        int ret = offer54.kthLargest(treeNodes[0], k);
        System.out.println(ret);
        System.out.println("====================");

        ret = offer54.kthLargest2(treeNodes[0], k);
        System.out.println(ret);
        System.out.println("====================");
    }
}





