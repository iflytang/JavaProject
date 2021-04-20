package src.nowcoder.SwordOffer;

import java.util.Arrays;
import java.util.Stack;

/**
 * @author tsf
 * @date 21-03-25
 * @desp
 *
 * 剑指 Offer 33. 二叉搜索树的后序遍历序列
 * 输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历结果。如果是则返回 true，否则返回 false。假设输入的数组的任意两个数字都互不相同。
 *
 * 参考以下这颗二叉搜索树：
 *
 *      5
 *     / \
 *    2   6
 *   / \
 *  1   3
 * 示例 1：
 *
 * 输入: [1,6,3,2,5]
 * 输出: false
 * 示例 2：
 *
 * 输入: [1,3,2,6,5]
 * 输出: true
 *
 *
 * 提示：
 *
 * 数组长度 <= 1000
 */

public class Offer33 {

    public boolean verifyPostorder(int[] postorder) {

        if (postorder.length == 0) {
            return false;
        }

        int rootVal = postorder[postorder.length - 1];

        int i = 0;
        for (; i < postorder.length - 1; i++) {
            if (postorder[i] > rootVal) {
                break;
            }
        }

        int j = i;
        for (; j < postorder.length - 1; j++) {
            if (postorder[j] < rootVal) {
                return false;
            }
        }

        int [] leftTree;
        int [] rightTree;

        boolean left = true;
        boolean right = true;

        if (i > 0) {
            leftTree = Arrays.copyOfRange(postorder, 0, i);
//            System.out.println(Arrays.toString(leftTree));
            left = verifyPostorder(leftTree);
        }

        if (i < postorder.length - 1) {
            rightTree = Arrays.copyOfRange(postorder, i, postorder.length - 1);
//            System.out.println(Arrays.toString(rightTree));
            right = verifyPostorder(rightTree);
        }

        return (left && right);
    }

    public boolean verifyPostorder2(int[] postorder) {
        if (postorder.length == 0) {
            return false;
        }

        return isBST(postorder, 0, postorder.length - 1);
    }

    public boolean isBST(int [] postorder, int start, int end) {
        if (start >= end) {  // 只剩一个结点
            return true;
        }

        int rootVal = postorder[end];
        int i = start;
        for (; i < end; i++) {
            if (postorder[i] > rootVal) {
                break;
            }
        }

        int j = i;
        for (; j < end; j++) {
            if (postorder[j] < rootVal) {
                return false;
            }
        }

        return isBST(postorder, start, i - 1) && isBST(postorder, i, end - 1);
    }

    public static void main(String[] args) {

        Offer33 offer33 = new Offer33();

//        int [] input = {1, 3, 2, 6, 5};
        int [] input = {7, 6, 4, 5};
        System.out.println(Arrays.toString(Arrays.copyOfRange(input, 0, input.length - 1)));

        boolean ret = offer33.verifyPostorder(input);
        System.out.println(ret);
        System.out.println("=========================");

        ret = offer33.verifyPostorder2(input);
        System.out.println(ret);
        System.out.println("=========================");
    }
}



