package leetcode.Medium;

/**
 * Created by tsf on 17-12-3.
 *
 * @Description convert string to integer
 */


public class StringToInteger {

    public int myAtoi(String str) {

        if (str.length() == 0) {
            return 0;
        }

        // check white space
        int i = 0;
        for (; i < str.length(); ) {
            if (str.charAt(i) == (' ')) {
                // do nothing
                i++;
            } else {
                break;
            }
        }

        // check positive (false) or negative (true)
        boolean flag = false;
        if (str.charAt(i) == '-' || str.charAt(i) == '+') {
            if (str.charAt(i) == '-') {
                flag = true;
            }
            i++;
        }

        // convert
        long result = 0;
        for (; i < str.length(); i++) {
            if (str.charAt(i) >= '0' && str.charAt(i) <= '9') {
                result =  (result * 10L + ((long) str.charAt(i) - 48L));
                // check range
                if (result > Integer.MAX_VALUE) {
                    break;
                }
                if (result < Integer.MIN_VALUE) {
                    break;
                }
            } else {
                break;
            }
        }

        result = flag ? -result : result;
        // double check range
        if (result > Integer.MAX_VALUE)
            result = Integer.MAX_VALUE;
        if (result < Integer.MIN_VALUE)
            result = Integer.MIN_VALUE;

        return (int) result;
    }


    public static void main(String[] args) {
        StringToInteger stringToInteger = new StringToInteger();
        int result = stringToInteger.myAtoi("-2147483648");
        System.out.println("result = " + result);
    }
}
