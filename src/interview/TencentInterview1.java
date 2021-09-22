package src.interview;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * 生成n个不同的随机数，随机数的大小范围为1~10倍，然后排序（不能用sort），
 * 最后输入一个target，经过二分查找返回idx，不存在则返回-1。
 * 考虑时间复杂度和空间复杂度。写输入输出。
 */

public class TencentInterview1 {
    public static int [] genRandom (int n) {
        int [] rnd = new int [n];

        Random base = new Random();
        int base_min = 0;
        int base_max = n;

        Random time = new Random();
        int time_min = 1;
        int time_max = 10;

        Set<Integer> set = new HashSet<>();
        int num = 0;

        for (int i = 0; set.size() < n;) {
            num = ((base.nextInt(n)) % (base_max - base_min + 1) + base_min) *
                    (time.nextInt(time_max) % (time_max - time_min + 1) + time_min);

            if (!set.contains(num)) {
                rnd[i++] = num;
                set.add(num);
            }

        }

        return rnd;
    }

    public static void quickSort(int [] arr, int left, int right) {
        if (left < right) {
            int idx = partition(arr, left, right);
            quickSort(arr, left, idx - 1);
            quickSort(arr, idx + 1, right);
        }
    }

    public static int partition(int [] arr, int left, int right) {
        int pivot = arr[right];

        int low = left;
        int high = right;

        while ((low < high)) {
            while ((low < high) && (arr[low] <= pivot)) {
                low++;
            }

            while ((low < high) && (arr[high] >= pivot)) {
                high--;
            }

            swap(arr, low, high);
        }

        swap(arr, low, right);

        return low;
    }

    public static void swap (int [] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static int binarySearch(int [] arr, int target) {
        int low = 0;
        int high = arr.length - 1;
        int mid = 0;

        while ((low <= high)) {
            mid = low + (high - low) / 2;

            if (arr[mid] < target) {
                low = mid + 1;
            } else if (arr[mid] > target) {
                high = mid - 1;
            } else {
                return mid;
            }
        }

        return -1;
    }


    public static void main (String [] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = null;

        while ((str = br.readLine()) != null) {
            int n = Integer.parseInt(str);
            int [] arr = genRandom(n);
            System.out.println(Arrays.toString(arr));

            quickSort(arr, 0, arr.length - 1);
            System.out.println(Arrays.toString(arr));

            int target = Integer.parseInt(br.readLine());
            int idx = binarySearch(arr, target);
            System.out.println(idx);
        }
    }
}