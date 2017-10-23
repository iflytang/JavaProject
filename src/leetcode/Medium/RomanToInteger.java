package leetcode.Medium;

/**
 * Created by tsf on 17-10-23.
 */


public class RomanToInteger {

    public int romanToInt(String s) {
        char tempChar;
        int sum = 0;
        for(int i = s.length()-1; i >= 0; i--) {
            tempChar = s.charAt(i);
            switch (tempChar) {
                case 'I':
                    sum += 1;
                    break;
                case 'V':  // if IV, add 4, otherwise add 5
                    if((i -1) >= 0 && s.charAt(i - 1) == 'I') {
                        sum += 4;
                        i--;
                    } else
                        sum += 5;
                    break;
                case 'X': // if IX, add 9, otherwise add 10
                    if((i -1) >= 0 && s.charAt(i - 1) == 'I') {
                        sum += 9;
                        i--;
                    } else
                        sum += 10;
                    break;
                case 'L': // if XL, add 40, otherwise add 50
                    if((i -1) >= 0 && s.charAt(i - 1) == 'X') {
                        sum += 40;
                        i--;
                    } else
                        sum += 50;
                    break;
                case 'C': // if XC, add 90, otherwise add 100
                    if((i -1) >= 0 && s.charAt(i - 1) == 'X') {
                        sum += 90;
                        i--;
                    } else
                        sum += 100;
                    break;
                case 'D': // if CD, add 400, otherwise add 500
                    if((i -1) >= 0 && s.charAt(i - 1) == 'C') {
                        sum += 400;
                        i--;
                    } else
                        sum += 500;
                    break;
                case 'M': // if CM, add 900, otherwise add 1000
                    if((i -1) >= 0 && s.charAt(i - 1) == 'C') {
                        sum += 900;
                        i--;
                    } else
                        sum += 1000;
                    break;
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        RomanToInteger test = new RomanToInteger();
        int num = test.romanToInt("CM");
        System.out.println("roman to int: " + num);
    }
}
