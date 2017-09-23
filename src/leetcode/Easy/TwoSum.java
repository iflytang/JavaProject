package leetcode.Easy;

import java.util.Arrays;

/**
 * Created by tsf on 17-9-18.
 */


public class TwoSum {

    public int[] twoSum(int[] nums, int target) {

        int temp = 0;
        int[] temp_array = new int[] {0, 0};
        int i = 0;
        int j = 0;
        for(i = 0; i < nums.length; i++) {
            for(j = i+1; j < nums.length; j++) {
                if(nums[i] + nums[j] == target) {
                   temp_array = new int[] {i,j};
                   break;
                }

            }
        }

       return temp_array;
    }

    public static void main(String[] args) {

        TwoSum t = new TwoSum();
        int[] nums = new int[] {2, 7, 11, 15};
        int[] returns = t.twoSum(nums, 9);
        System.out.println(Arrays.toString(returns));
    }
}
