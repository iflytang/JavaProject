package src.interview;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class LongestIntegrationLength {

    public static int getLongest(int[] arr){
        if(arr == null || arr.length == 0){
            return 0;
        }

        int maxLen = 0;
        HashSet<Integer> set = new HashSet<>();
        Map<Integer, Integer> hash = new HashMap<>();
        // 尝试以每个 i 开头的子数组
        for(int i = 0; i < arr.length; i++){
            int max = Integer.MIN_VALUE;
            int min = Integer.MAX_VALUE;
            for(int j = i; j < arr.length; j++){
                if(set.contains(arr[j])){
                    // 如果包含重复数字，则直接进行一下位作为数组的开头
                    break;
                }
                set.add(arr[j]);
                max = Math.max(max, arr[j]);
                min = Math.min(min, arr[j]);
                // 数组的个数为：j - i + 1，再减去1，即为：j - i
                if(max - min == j - i){
                    // 最大减最小等于当前数组的长度，说明每个数字之间的跨度都是1，可以整合
                    maxLen = Math.max(maxLen, j - i + 1);
                }
            }
            // 每次以一个新的数字作为子数组开头的时候，都要先清空set
            set.clear();
        }
        return maxLen;
    }

    public static void main(String[] args) {
        int[] arr = { 5, 5, 3, 2, 6, 4, 3 };  // 5, 3, 2, 6, 4
        System.out.println(getLongest(arr));  // 5
    }
}