package src.nowcoder.SwordOffer;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author tsf
 * @date 21-03-25
 * @desp
 * 剑指 Offer 45. 把数组排成最小的数
 * 输入一个非负整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。
 *
 * 示例 1:
 *
 * 输入: [10,2]
 * 输出: "102"
 * 示例 2:
 *
 * 输入: [3,30,34,5,9]
 * 输出: "3033459"
 *
 *
 * 提示:
 *
 * 0 < nums.length <= 100
 * 说明:
 *
 * 输出结果可能非常大，所以你需要返回一个字符串而不是整数
 * 拼接起来的数字可能会有前导 0，最后结果不需要去掉前导 0
 */

public class Offer45 {

    public String minNumber(int[] nums) {

        if (nums.length == 0) {
            return null;
        }

        String [] strs = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            strs[i] = String.valueOf(nums[i]);
        }

        quickSort(strs, 0, strs.length - 1);

        StringBuilder res = new StringBuilder();
        for (String str: strs) {
            res.append(str);
        }

        return res.toString();
    }

    public void quickSort(String [] strings, int low, int high) {
        if (low < high) {
            int partitionIdx = partition(strings, low, high);
            quickSort(strings, low, partitionIdx - 1);
            quickSort(strings, partitionIdx + 1, high);
        }
    }

    public int partition(String [] strings, int low, int high) { // 需排序规则的传递性证明：
        String pivot = strings[high];

        int left = low - 1;
        for (int i = low; i < high; i++) {
            if ((strings[i] + pivot).compareTo(pivot  + strings[i]) < 0) {  // if x+y < y+x, then x is in font of y.
                left++;
                // swap(left, i); // notice String is final
                String temp = strings[i];
                strings[i] = strings[left];
                strings[left] = temp;
            }
        }

        // swap(left, pivot);
        left++;
        String temp = strings[high];
        strings[high] = strings[left];
        strings[left] = temp;

        return left;
    }

    public String minNumber2(int[] nums) {

        if (nums.length == 0) {
            return null;
        }

        String [] strs = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            strs[i] = String.valueOf(nums[i]);
        }

//        Arrays.sort(strs, (x, y) -> (x + y).compareTo(y + x));
        Arrays.sort(strs, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return (o1 + o2).compareTo(o2 + o1);
            }
        });

        StringBuilder res = new StringBuilder();
        for (String str: strs) {
            res.append(str);
        }

        return res.toString();
    }

    public static void main(String[] args) {

        Offer45 offer44 = new Offer45();

        int [] inputs = {10, 2};
        String  ret = offer44.minNumber(inputs);
        System.out.println(ret);
        System.out.println("==========================");
        ret = offer44.minNumber2(inputs);
        System.out.println(ret);
        System.out.println("==========================\n");

        int [] inputs2 = {3, 30, 34, 5, 9};
        ret = offer44.minNumber(inputs2);
        System.out.println(ret);
        System.out.println("==========================");
        ret = offer44.minNumber2(inputs2);
        System.out.println(ret);
        System.out.println("==========================");
    }
}





