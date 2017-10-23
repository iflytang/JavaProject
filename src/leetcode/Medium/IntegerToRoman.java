package leetcode.Medium;

import java.util.Arrays;

/**
 * Created by tsf on 17-10-23.
 */

// convert int to roman num
public class IntegerToRoman {

    public String intToRoman(int num) {
        String roman[][] = {{"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"},
                            {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"},
                            {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"},
                            {"", "M", "MM", "MMM"}};
        int temp;
        int base = 0;    // the base of radix
        StringBuilder builder = new StringBuilder();  // store the roman number

        while (num != 0) {
            temp = num % 10;  // get the unit's digit in every cycle
            builder.insert(0, roman[base][temp]);
            base++;
            num = num / 10;
        }

        return builder.toString();
    }

    public static void main(String[] args) {
        IntegerToRoman test = new IntegerToRoman();
        String str = test.intToRoman(1000);
        System.out.println("int to roman: " + str);
    }
}
