package src.leetcode.BinaryTreeTraversal;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class postorderTraversal {

    public List<Integer> recursiveMethod(TreeNode root) {
        return postorderTraversal(root);
    }

    public List<Integer> postorderTraversal(TreeNode root) {  // 递归

        List<Integer> res = new ArrayList<>();

        postorderTraversal(root, res);

        return res;
    }

    public void postorderTraversal(TreeNode root, List<Integer> res) {
        // 递归出口
        if (root == null) {
            return;
        }

//        res.add(root.val);  // preorder
        postorderTraversal(root.left, res);
//        res.add(root.val);  // inorder
        postorderTraversal(root.right, res);
        res.add(root.val);  // postorder
    }

    public List<Integer> iterativeMethod(TreeNode root) {
        return postorderTraversalIter(root);
    }

    public List<Integer> postorderTraversalIter(TreeNode root) {
        List<Integer> res = new ArrayList<>();

        if (root == null) {
            return res;
        }

        Deque<TreeNode> stack = new LinkedList<>();

        TreeNode pre = null;
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.addFirst(root);
                root = root.left;
            }

            root = stack.removeFirst();
            if (root.right == null || root.right == pre) {
                res.add(root.val);
                pre = root;
                root = null;
            } else {
                stack.addFirst(root);
                root = root.right;
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

        postorderTraversal test = new postorderTraversal();
        List<Integer> ret = test.postorderTraversal(treeNodes[0]);
        System.out.println("recursive: " + ret.toString());

        System.out.println("================");

        ret = test.postorderTraversalIter(treeNodes[0]);
        System.out.println("iterative: " + ret.toString());


    }

}

