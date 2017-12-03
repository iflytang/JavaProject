package leetcode.Easy;

/**
 * Created by tsf on 17-12-3.
 *
 * @Description Given a 32-bit signed integer, reverse digits of an integer.
 */


public class ReverseInteger {

    public int reverse(int x) {

        int result = 0;

        while (x != 0) {
            int num = x % 10;
            int newResult = result * 10 + num;
            // check
            if ((newResult - num) / 10 != result)
                return 0;
            result = newResult;
            x = x / 10;
        }

        return result;
    }

    public static void main(String[] args) {

        ReverseInteger reverseInteger = new ReverseInteger();
        int result = reverseInteger.reverse(1534236462);
        System.out.println("reversed result: " + result);
    }
}
