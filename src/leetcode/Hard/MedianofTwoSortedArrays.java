package leetcode.Hard;

import java.util.*;

/**
 * Created by tsf on 17-10-24.
 */


public class MedianofTwoSortedArrays {

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        List<Integer> list = new ArrayList<>();
        double median = 0; // the median number of two nums[]
        int length = 0;  // the length of list

        for(int i = 0; i < nums1.length; i++) {
            list.add(nums1[i]);
        }

        for(int i = 0; i < nums2.length; i++) {
            list.add(nums2[i]);
        }

        // get sorted List
        Collections.sort(list, new Comparator<Integer>() {
            @Override
            public int compare(Integer t1, Integer t2) {
                return t1 - t2;
            }
        });

        length = list.size();
        if(length % 2 == 0) {
            median = (list.get(length/2) + list.get(length/2 - 1)) / 2.0;
        } else {
            median = list.get(length/2);
        }

        return median;
    }

    public static void main(String[] args) {
        MedianofTwoSortedArrays test = new MedianofTwoSortedArrays();
        int[] nums1 = {1, 2};
        int[] nums2 = {3, 4};
        double median = test.findMedianSortedArrays(nums1, nums2);
        System.out.println("the median of nums1 and nums2 is " + median);
    }
}
