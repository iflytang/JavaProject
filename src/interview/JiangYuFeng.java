package src.interview;

import java.util.ArrayList;
import java.util.Arrays;

public class JiangYuFeng {

    public static ArrayList<ArrayList<Integer>> getPeak(int [] nums, int idx) {

        int startIdx = Math.max(idx - 30, 0);

        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        boolean start = true;
        boolean end = false;
        ArrayList<Integer> interval = new ArrayList<>();
        for (int i = startIdx; i < idx; i++) {
            if (nums[i] != 0) {
                if (start) {
                    interval = new ArrayList<>();
                    start = false;
                    end = true;
                }
                interval.add((nums[i]));
//                interval.add(i);

            } else {
                start = true;

                if (end) {
                    res.add(interval);
                    System.out.println(Arrays.toString(interval.toArray()));
                    end = false;
                }
            }

        }

        return res;

    }

    public static void main (String [] args) {
        int [] nums = {0, 0, 0, 0, 0, 1, 2, 0, 0, 0,
                       3, 4, 5, 0, 0, 0, 0, 0, 0, 0,
                       7, 8, 9, 0, 0, 0, 0, 10, 0, 0,
                       0, 0
        };
        getPeak(nums, 31);
    }

}
