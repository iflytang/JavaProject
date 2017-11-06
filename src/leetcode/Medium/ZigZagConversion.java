package leetcode.Medium;

/**
 * Created by tsf on 17-11-6.
 */


public class ZigZagConversion {


    public String convert(String s, int numRows) {
        StringBuilder[]  temp = new StringBuilder[numRows];
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < numRows; i++) {
            temp[i] = new StringBuilder();
        }

        // store in temp Array
        int i = 0;
        while (i < s.length()) {
            // vertically scan
            for (int index = 0; index < numRows && i < s.length(); index++) {
                temp[index].append(s.charAt(i++));
            }
            // obliquely scan
            for (int index = numRows - 2; index >= 1 && i < s.length(); index--) {
                temp[index].append(s.charAt(i++));
            }
        }

        // get the result
        for (int index = 0; index < numRows; index++) {
            //System.out.println(temp[index].toString());
            result.append(temp[index]);
        }

        return result.toString();
    }

    public static void main(String[] args) {
        ZigZagConversion test = new ZigZagConversion();
        String s = test.convert("ABC", 2);
        System.out.println("the Zigzag conversion is: " + s);
    }
}
