package src.leetcode.SortAlg;

import java.io.IOException;
import java.util.Arrays;

public class InsertionSort {

    public int[] sort(int[] sourceArray) {
        // 对 arr 进行拷贝，不改变参数内容
        int[] arr = Arrays.copyOf(sourceArray, sourceArray.length);

        // 从下标为1的元素开始选择合适的位置插入，因为下标为0的只有一个元素，默认是有序的
        for (int i = 1; i < arr.length; i++) {

            // 记录要插入的数据
            int tmp = arr[i];

            // 从已经排序的序列最右边的开始比较，找到比其小的数
            int j = i;
            while (j > 0 && tmp < arr[j - 1]) {
                arr[j] = arr[j - 1];
                j--;
            }

            // 存在比其小的数，插入
            if (j != i) {
                arr[j] = tmp;
            }

        }
        return arr;
    }

    public static void main (String [] args) throws IOException {

        InsertionSort test = new InsertionSort();

        int [] nums3 = {3,2,3,1,2,4,5,5,6};
        int [] ret3 = test.sort(nums3);
        System.out.println(Arrays.toString(ret3));
    }

}
