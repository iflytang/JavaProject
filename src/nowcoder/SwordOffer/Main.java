package src.nowcoder.SwordOffer;

import java.io.*;
import java.util.*;

public class Main {

    public int findKthLargest(int[] nums, int k) {
        return quickSortKthNum(nums, 0, nums.length - 1, k - 1);
    }

    int i = 0;
    public int quickSortKthNum(int [] nums, int low, int high, int k) {
        int pivot = nums[high];

        int left = low;
        int right = high;

        while (left < right) {
            while ((left < right) && (nums[left] >= pivot)) {  // 降序排序
                left++;
            }

            while ((left < right) && (nums[right]) <= pivot) { // 降序排序
                right--;
            }

            swap(nums, left, right);
        }
        swap(nums, left, high);
//        System.out.println("i: " + i + ", " + Arrays.toString(nums));
//        i++;

        if (left < k) {  // 在右子序列
            quickSortKthNum(nums, left + 1, high, k);
        }

        if (left > k) {  // 在左子序列
            quickSortKthNum(nums, low, left - 1, k);
        }

        System.out.println(Arrays.toString(Arrays.copyOf(nums, k)));

        return nums[k];
    }

    public void swap (int [] nums, int low, int high) {
        int tmp = nums[low];
        nums[low] = nums[high];
        nums[high] = tmp;
    }

    // top-k-min可以返回基准值的前k个值
    public int[] getLeastNumbers(int[] arr, int k) {
        if (k >= arr.length) {
            return arr;
        }

        return quickSortK(arr, k, 0, arr.length - 1);
    }

    public int [] quickSortK(int [] arr, int k, int low, int high) {
        // 选择最右值为基准值
        int pivot = arr[high];

        int left = low, right = high;

        while (left < right) {
            // 从左侧开始遍历
            while ((left < right) && (arr[left] <= pivot)) {
                left++;
            }

            while ((left < right) && (arr[right] >= pivot)) {
                right--;
            }

            swap(arr, left, right);
        }
        swap(arr, left, high); // 对换基准值位置

        if (k > left) {  // 说明在右子树中
            quickSortK(arr, k, left + 1, high);
        }

        if (k < left) {  // 说明在左子树
            quickSortK(arr, k, low, left - 1);
        }

        return Arrays.copyOf(arr, k);

    }

    public int[] sort(int[] sourceArray)  {
        int[] arr = Arrays.copyOf(sourceArray, sourceArray.length);

        // 总共要经过 N-1 轮比较
        for (int i = 0; i < arr.length - 1; i++) {
            int min = i;

            // 每轮需要比较的次数 N-i
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[min]) {
                    // 记录目前能找到的最小值元素的下标
                    min = j;
                }
            }

            // 将找到的最小值和i位置所在的值进行交换
            if (i != min) {
                int tmp = arr[i];
                arr[i] = arr[min];
                arr[min] = tmp;
            }

        }
        return arr;
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        int len = candidates.length;
        List<List<Integer>> res = new ArrayList<>();
        if (len == 0) {
            return res;
        }

//        Deque<Integer> path = new ArrayDeque<>();
        List<Integer> path = new ArrayList<>();
        dfs(candidates, 0, len, target, path, res);
        return res;
    }

    /**
     * @param candidates 候选数组
     * @param begin      搜索起点
     * @param len        冗余变量，是 candidates 里的属性，可以不传
     * @param target     每减去一个元素，目标值变小
     * @param path       从根结点到叶子结点的路径，是一个栈
     * @param res        结果集列表
     */
    private void dfs(int[] candidates, int begin, int len, int target, List<Integer> path, List<List<Integer>> res) {
        // target 为负数和 0 的时候不再产生新的孩子结点
        if (target < 0) {
            return;
        }
        if (target == 0) {
            res.add(new ArrayList<>(path));
            return;
        }

        // 重点理解这里从 begin 开始搜索的语意
        for (int i = begin; i < len; i++) {
            path.add(candidates[i]);

            // 注意：由于每一个元素可以重复使用，下一轮搜索的起点依然是 i，这里非常容易弄错
            dfs(candidates, i, len, target - candidates[i], path, res);

            // 状态重置
            path.remove(path.size() - 1);
        }
    }

    // quicksort for string
    public static void quickSort(String [] strs, int low, int high) {
        if (low < high) {
            int idx = partition(strs, low, high);
            quickSort(strs, low, idx - 1);
            quickSort(strs, idx + 1, high);
        }
    }

    public static int partition(String [] strs, int low, int high) {
        String pivot = strs[high];
        int left = low;
        int right = high;

        // 注意low < high严格是小于号，不能是<=
        while (left < right) {
            while ((left < right) && (strs[left].compareTo(pivot) <= 0)) {
                left++;
            }

            while ((left < right) && (strs[right].compareTo(pivot) >= 0)) {
                right--;
            }

            swap(strs, left, right);
        }

        swap(strs, left, high);

        return left;
    }

    public static void swap (String [] strs, int i, int j) {
        String tmp = strs[i];
        strs[i] = strs[j];
        strs[j] = tmp;
    }


    public static void main (String [] args) throws IOException {

       Main test = new Main();
       int [] nums = {2, 3, 6, 7};
       int target = 7;

       List<List<Integer>> ret = test.combinationSum(nums, target);
       for (List<Integer> ele : ret) {
           System.out.println(ele.toString());
       }

       String ip = "19...0";
       System.out.println(Arrays.toString(ip.split("\\.")));

       char [] chars = new char[] {'1', ' ', '2'};
       System.out.println(new String(chars));


    }










}
