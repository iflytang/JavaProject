package src.nowcoder.SwordOffer;

/**
 * @author tsf
 * @date 21-03-25
 * @desp
 * 剑指 Offer 53 - II. 0～n-1中缺失的数字
 * 一个长度为n-1的递增排序数组中的所有数字都是唯一的，并且每个数字都在范围0～n-1之内。
 * 在范围0～n-1内的n个数字中有且只有一个数字不在该数组中，请找出这个数字。
 *
 * 示例 1:
 *
 * 输入: [0,1,3]
 * 输出: 2
 * 示例 2:
 *
 * 输入: [0,1,2,3,4,5,6,7,9]
 * 输出: 8
 */

public class Offer53_2 {

    // 由于是有序的，分nums[i]=i和nums[i]<i的情况
    public int missingNumber(int[] nums) {

        int left = 0;
        int right = nums.length;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid]  == mid) {
                left = mid + 1;
                System.out.println("left: " + left + ", right: " + right);
            } else {
                right = mid - 1;
                System.out.println("right: " + right + ", left: " + left);
            }
        }

        return left;
    }

    public static void main(String[] args) {

        Offer53_2 offer53_2 = new Offer53_2();

        int [] nums = {0, 1, 2, 3, 4, 5, 6, 8, 9};
//        int [] nums = {2, 2, 2, 2};
        int target = 8;
        int ret = offer53_2.missingNumber(nums);
        System.out.println(ret);
        System.out.println("====================");
//        System.out.println(Arrays.binarySearch(nums, target));


    }
}





