package src.nowcoder.SwordOffer;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @author tsf
 * @date 21-03-25
 * @desp
 * 剑指 Offer 40. 最小的k个数
 * 输入整数数组 arr ，找出其中最小的 k 个数。例如，输入4、5、1、6、2、7、3、8这8个数字，则最小的4个数字是1、2、3、4。
 *
 * 示例 1：
 *
 * 输入：arr = [3,2,1], k = 2
 * 输出：[1,2] 或者 [2,1]
 * 示例 2：
 *
 * 输入：arr = [0,1,2,1], k = 1
 * 输出：[0]
 *
 *
 * 限制：
 *
 * 0 <= k <= arr.length <= 10000
 * 0 <= arr[i] <= 10000
 */

public class Offer40 {

    /* 题目只要求返回最小的 k 个数，对这 k 个数的顺序并没有要求。
     * 因此，只需要将数组划分为 最小的 k 个数 和 其他数字 两部分即可，
     * 而快速排序的哨兵划分可完成此目标。
     * 根据快速排序原理，如果某次哨兵划分后 基准数正好是第 k+1小的数字 ，
     * 那么此时基准数左边的所有数字便是题目所求的 最小的 k 个数 。 */
    public int[] getLeastNumbers0(int[] arr, int k) {
        if (arr.length <= k) {
            return arr;
        }

        return quickSortK(arr, k, 0, arr.length - 1);
    }

    public int [] quickSortK(int [] arr, int k, int low, int high) {
        int pivot = arr[high];
        int start = low, end = high;

        while (low < high) {  // 注意基准值位置不同，遍历顺序不同

//            while ((low < high) && (arr[high] >= pivot)) {
//                high--;
//            }

            while ((low < high) && (arr[low] <= pivot)) {
                low++;
            }

            while ((low < high) && (arr[high] >= pivot)) {
                high--;
            }

            swap(arr, low, high);
        }
        swap(arr, low, end);

        if (k > low) {  // 若 k > i，代表第 k + 1小的数字在 右子数组 中，则递归右子数组
            quickSortK(arr, k, low + 1, end);
        }
 
        if (k < low) {  // 若 k < i，代表第 k + 1 小的数字在 左子数组 中，则递归左子数组；
            quickSortK(arr, k, start, low - 1);
        }

        return Arrays.copyOf(arr, k); // 若 k = i，代表此时 arr[k] 即为第 k + 1小的数字，则直接返回数组前 k个数字即可
    }

    /* 冒泡排序，时间复杂度O(n^2) */
    public int[] getLeastNumbers(int[] arr, int k) {

        int [] list = arr.clone();

        for (int i = 0; i < list.length - 1; i++) {

            boolean swapFlag = true;
            for (int j = 0; j < list.length - 1 - i; j++) {  // 每次循环，最大值会在最后处

                if (list[j] > list[j+1]) {
                    int temp = list[j+1];
                    list[j+1] = list[j];
                    list[j] = temp;

                    swapFlag = false;  // 发生了交换。如果没交换，说明前面序列已经有序，直接跳出
                }

            }

            if (swapFlag) {
                break;
            }
        }

        return Arrays.copyOfRange(list, 0, k);
    }

    public int[] getLeastNumbers2(int[] arr, int k) {
        quickSort(arr, 0, arr.length - 1);

        return Arrays.copyOfRange(arr, 0, k);
    }

    // 快速排序
    public void quickSort(int [] arr, int low, int high) {
        if (low < high) {
            int index = partition(arr, low, high);
            quickSort(arr, low, index - 1);
            quickSort(arr, index + 1, high);
        }
    }

    public int partition(int [] arr, int low, int high) {
        int pivot = arr[high];  // 以由节点为基准点

        int left = low - 1;

        for (int i = low; i < high; i++) {  // 比基准点小的值都放到左侧
            if (arr[i] < pivot) {
                // 将小于pivot的值交换到partition左边
                // 将大于等于pivot的值交换到partition右边
                left++;
                swap(arr, left, i);
            }
        }

        // 将分区节点插入到数组左右分区中间
        left++;
        swap(arr, left, high); // 最后需要交换基准点和当前比基准点大的值的位置

        return left;
    }

    public void swap(int [] arr, int i, int j) {
        int temp = arr[j];
        arr[j] = arr[i];
        arr[i] = temp;
    }

    public int[] getLeastNumbers3(int[] arr, int k) {
        quickSort2(arr, 0, arr.length - 1);

        return Arrays.copyOfRange(arr, 0, k);
    }

    public void quickSort2(int [] arr, int low, int high) {
        if (low < high) {
            int partitionIdx = partition2(arr, low, high);
            quickSort2(arr, low, partitionIdx - 1);
            quickSort2(arr, partitionIdx + 1, high);
        }
    }

    public int partition2(int [] arr, int low, int high) {
        int pivot = arr[high];

        while (low < high) {
            while ((low < high) && (arr[low] <= pivot)) {
                low++;  //从左边开始遍历，如果比基准值小，就继续向右走
            }

            if (low < high) { //上面的while循环结束时，就说明当前的a[low]的值比基准值大，应与基准值进行交换
                swap(arr, low, high);
                high--;
            }

            while ((low < high) && (arr[high] >= pivot)) {
                high--;  //从右边开始遍历，如果比基准值大，就继续向左走
            }

            if (low < high) { //上面的while循环结束时，就说明当前的a[high]的值比基准值小，应与基准值进行交换
                swap(arr, low, high);
                low++;
            }
        }

        return low;  //这里返回low或者high皆可，此时都为基准值所在的位置
    }

    public int[] getLeastNumbers4(int[] arr, int k) {
        quickSort3(arr, 0, arr.length - 1);

        return Arrays.copyOfRange(arr, 0, k);
    }

    public void quickSort3(int [] arr, int low, int high) {
        if (low < high) {
            int partitionIdx = partition3(arr, low, high);
            quickSort3(arr, low, partitionIdx - 1);
            quickSort3(arr, partitionIdx + 1, high);
        }
    }

    public int partition3(int [] arr, int low, int high) {
        int pivot = arr[low];
        int pivotIdx = low;

        while (low < high) {

            while ((low < high) && (arr[high] >= pivot)) {
                high--;  //从右边开始遍历，如果比基准值大，就继续向左走
            }

            while ((low < high) && (arr[low] <= pivot)) {
                low++;  //从左边开始遍历，如果比基准值小，就继续向右走
            }

            swap(arr, low, high);
        }
        swap(arr, low, pivotIdx);

        return low;  //这里返回low或者high皆可，此时都为基准值所在的位置
    }


    public static void main(String[] args) {

        Offer40 offer40 = new Offer40();

        int [] inputs = {1, 2, 5, 4, 9, 8, 6};
//        int [] inputs = {3, 2, 1};
        int k = inputs.length;
//        int k = 2;

        System.out.println(Arrays.toString(inputs));
        System.out.println("==========================");

        int[] ret = offer40.getLeastNumbers(inputs, k);
        System.out.println(Arrays.toString(ret));
        System.out.println("==========================");

        ret = offer40.getLeastNumbers2(inputs, k);
        System.out.println(Arrays.toString(ret));
        System.out.println("==========================");

        ret = offer40.getLeastNumbers3(inputs, k);
        System.out.println(Arrays.toString(ret));
        System.out.println("==========================");

        ret = offer40.getLeastNumbers4(inputs, k);
        System.out.println("4: " + Arrays.toString(ret));
        System.out.println("==========================");

        ret = offer40.getLeastNumbers0(inputs, k);
        System.out.println("top-k-min: " + Arrays.toString(ret));
        System.out.println("==========================");

        ret = offer40.getLeastNumbers0(inputs, 2);
        System.out.println("top-k-min: " + Arrays.toString(ret));
        System.out.println("==========================");

    }
}





