package src.nowcoder.SwordOffer;

import java.util.Arrays;

/**
 * @author tsf
 * @date 21-03-25
 * @desp 剑指 Offer 04. 数组中重复的数字。
 * 题目描述
 * 在一个二维数组中（每个一维数组的长度相同），每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
 * 示例1
 * 输入
 * 复制
 * 7,[[1,2,8,9],[2,4,9,12],[4,7,10,13],[6,8,11,15]]
 * 返回值
 * 复制
 * true
 */

public class Offer04 {
    public boolean findNumberIn2DArray(int[][] matrix, int target) {

        boolean res = false;

        int loop = matrix.length;
        for (int i = 0; i < loop; i++) {
            int[] intArr = matrix[i];
//            int ret = Arrays.binarySearch(intArr, target);
            int ret = binarySearch(intArr, target);

            if (ret >= 0) {
                res = true;
                break;
            }
        }

        return res;
    }

    public int binarySearch(int[] arr, int key) {

        int leftIdx = 0;
        int rightIdx = arr.length - 1;

        while (leftIdx <= rightIdx) {
            int midIdx = (leftIdx + rightIdx) / 2;
            if (arr[midIdx] < key) {
                leftIdx = midIdx + 1;
            } else if (arr[midIdx] > key) {
                rightIdx = midIdx - 1;
            } else {
                return midIdx;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 4, 7, 11, 15},
                {2, 5, 8, 12, 19},
                {3, 6, 9, 16, 22},
                {10, 13, 14, 17, 24},
                {18, 21, 23, 26, 30}

        };

        int target = 55;

        Offer04 offer04 = new Offer04();
        boolean res = offer04.findNumberIn2DArray(matrix, target);
        System.out.println(res);

    }
}
