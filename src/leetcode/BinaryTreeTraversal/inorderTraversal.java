package src.leetcode.BinaryTreeTraversal;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class inorderTraversal {

    public List<Integer> recursiveMethod(TreeNode root) {
        return inorderTraversal(root);
    }

    public List<Integer> inorderTraversal(TreeNode root) {  // 递归

        List<Integer> res = new ArrayList<>();

        inorderTraversal(root, res);

        return res;
    }

    public void inorderTraversal(TreeNode root, List<Integer> res) {
        // 递归出口
        if (root == null) {
            return;
        }

//        res.add(root.val);  // preorder
        inorderTraversal(root.left, res);
        res.add(root.val);  // inorder
        inorderTraversal(root.right, res);
//        res.add(root.val);  // postorder
    }

    public List<Integer> iterativeMethod(TreeNode root) {
        return inorderTraversalIter(root);
    }

    public List<Integer> inorderTraversalIter(TreeNode root) {
        List<Integer> res = new ArrayList<>();

        if (root == null) {
            return res;
        }

        Deque<TreeNode> stack = new LinkedList<>();

        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.addFirst(root);  // push
//                res.add(root.val);  // preorder
                root = root.left;
            }

            root = stack.removeFirst();  // pop
            res.add(root.val);  // inorder
            root = root.right;
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

        inorderTraversal test = new inorderTraversal();
        List<Integer> ret = test.inorderTraversal(treeNodes[0]);
        System.out.println("recursive: " + ret.toString());

        System.out.println("================");

        ret = test.inorderTraversalIter(treeNodes[0]);
        System.out.println("iterative: " + ret.toString());

    }

}

