package src.interview;

import java.io.IOException;
import java.util.*;

public class DelTree {

    public static void main (String [] args) throws IOException {
        TreeNode [] tree = new TreeNode[9];
        tree[0] = new TreeNode(1);
        tree[1] = new TreeNode(1);
        tree[2] = new TreeNode(1);
        tree[3] = new TreeNode(1);
        tree[4] = new TreeNode(1);
        tree[5] = new TreeNode(1);
        tree[6] = new TreeNode(1);
        tree[7] = new TreeNode(1);
        tree[8] = new TreeNode(1);

        tree[0].left = tree[1];
        tree[0].right = tree[2];

        tree[1].left = tree[3];
        tree[1].right = tree[4];

        tree[2].left = tree[5];
        tree[2].right = tree[6];

        levelOrder(tree[0]);
        TreeNode ret = solution(tree[0]);
        levelOrder(ret);

        System.out.println();
    }

    public static TreeNode solution (TreeNode root) {
        if (root == null) {
            return null;
        }

        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);

        System.out.println(leftDepth + " " + rightDepth);

        // 删除左子树的页结点
        TreeNode left = delTree(root, leftDepth);

        // 删除右子树的页结点
        TreeNode right = delTree(root, rightDepth);

        // 返回值
        TreeNode ret = new TreeNode(root.val);
        ret.left = left;
        ret.right = right;

//        System.out.println(root.left.val + " " + root.right.val);

        return ret;
    }

    public static TreeNode delTree (TreeNode root, int depth) {
        if (root == null) {
            return null;
        }

        Deque<TreeNode> queue = new LinkedList<>();
        queue.add(root);  // 初始化
        int level = 0;
        TreeNode tmp = null;

        // 层序遍历
        while (!queue.isEmpty()) {

            int len = queue.size();
            for (int i = 0; i < len; i++) {  // 注意len的初始化，不能在把len替换成queue.size()
                tmp = queue.removeFirst();  // 弹出当前队首
                // 入队列
                if (tmp.left != null) {
                    queue.addLast(tmp.left);
                }
                if (tmp.right != null) {
                    queue.addLast(tmp.right);
                }
            }

            level++;

            // 删除页结点的根结点后返回
            if (level == depth - 1) {
                tmp.left = null;
                tmp.right = null;
                System.out.println("return level " + level);
                break;
            }

        }

        return root;
    }

    // 二叉树的深度
    public static int maxDepth (TreeNode root) {
        if (root == null) {
            return 0;
        }

        int left = maxDepth(root.left);
        int right = maxDepth(root.right);

        return Math.max(left, right) + 1;
    }

    public static void levelOrder(TreeNode root) {  // 利用queue来实现

        List<List<Integer>> res = new ArrayList<>();

        // 边界检查
        if (root == null) {
            return;
        }

        Deque<TreeNode> queue = new LinkedList<>();
        queue.addLast(root);

        while (!queue.isEmpty()) {
            int size = queue.size();  // 每一层的结点数量
            List<Integer> level = new ArrayList<>();  // 存放每一层数据

            for (int i = 0; i < size; i++) {
                TreeNode tmp = queue.removeFirst();
                level.add(tmp.val);

                if (tmp.left != null) {
                    queue.addLast(tmp.left);
                }

                if (tmp.right != null) {
                    queue.addLast(tmp.right);
                }
            }

            res.add(level);  // 遍历完一层
        }

        System.out.println(Arrays.toString(res.toArray()));
    }

}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
