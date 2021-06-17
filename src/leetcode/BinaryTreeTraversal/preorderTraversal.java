package src.leetcode.BinaryTreeTraversal;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class preorderTraversal {

    public List<Integer> recursiveMethod(TreeNode root) {
        return preorderTraversal(root);
    }

    public List<Integer> preorderTraversal(TreeNode root) {  // 递归

        List<Integer> res = new ArrayList<>();

        preorderTraversal(root, res);

        return res;
    }

    public void preorderTraversal(TreeNode root, List<Integer> res) {
        // 递归出口
        if (root == null) {
            return;
        }

        // 递归过程：根 -> 左 -> 右
        res.add(root.val);

        preorderTraversal(root.left, res);
        preorderTraversal(root.right, res);
    }

    public List<Integer> iterativeMethod(TreeNode root) {
        return preorderTraversalIter(root);
    }

    public List<Integer> preorderTraversalIter(TreeNode root) {
        List<Integer> res = new ArrayList<>();

        if (root == null) {
            return res;
        }

        Deque<TreeNode> stack = new LinkedList<>();

        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.addFirst(root);  // push
                res.add(root.val);  // preorder
                root = root.left;
            }

            root = stack.removeFirst();  // pop
//            res.add(root.val);  // inorder
            root = root.right;
        }

        return res;
    }

    public List<Integer> iterativeMethod2(TreeNode root) {
        return preorderTraversalIter2(root);
    }

    public List<Integer> preorderTraversalIter2(TreeNode root) {
        List<Integer> res = new ArrayList<>();

        if (root == null) {
            return res;
        }

        Deque<TreeNode> stack = new LinkedList<>();
        stack.addFirst(root);
        while (!stack.isEmpty()) {

            root = stack.removeFirst();
            res.add(root.val);

            if (root.right != null) { // first in last out
                stack.addFirst(root.right);
            }

            if (root.left != null) {
                stack.addFirst(root.left);
            }

        }

        return res;
    }

    public static void main (String [] args) {
        TreeNode[] treeNodes = new TreeNode[7];

        treeNodes[0] = new TreeNode(10);
        treeNodes[1] = new TreeNode(6);
        treeNodes[2] = new TreeNode(14);
        treeNodes[3] = new TreeNode(4);
        treeNodes[4] = new TreeNode(8);
        treeNodes[5] = new TreeNode(12);
        treeNodes[6] = new TreeNode(16);

        treeNodes[0].left = treeNodes[1];
        treeNodes[0].right = treeNodes[2];

        treeNodes[1].left = treeNodes[3];
        treeNodes[1].right = treeNodes[4];

        treeNodes[2].left = treeNodes[5];
        treeNodes[2].right = treeNodes[6];

        preorderTraversal test = new preorderTraversal();
        List<Integer> ret = test.preorderTraversal(treeNodes[0]);
        System.out.println("recursive: " + ret.toString());

        System.out.println("================");

        ret = test.preorderTraversalIter(treeNodes[0]);
        System.out.println("iterative: " + ret.toString());

        System.out.println("================");

        ret = test.preorderTraversalIter2(treeNodes[0]);
        System.out.println("iterative: " + ret.toString());

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
