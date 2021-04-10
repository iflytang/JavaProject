package src.nowcoder.SwordOffer;

/**
 * @author tsf
 * @date 21-03-25
 * @desp
 * 剑指 Offer 17. 打印从1到最大的n位数
 * 输入数字 n，按顺序打印出从 1 到最大的 n 位十进制数。比如输入 3，则打印出 1、2、3 一直到最大的 3 位数 999。
 *
 * 示例 1:
 *
 * 输入: n = 1
 * 输出: [1,2,3,4,5,6,7,8,9]
 *
 *
 * 说明：
 *
 * 用返回一个整数列表来代替打印
 * n 为正整数
 */


public class Offer17 {

    /* 没考虑大数限制 */
    public int[] printNumbers(int n) {

        int max_len = (int) Math.pow(10, n * 1.0);

        int [] res = new int[max_len-1];

        for (int i = 1; i < max_len; i++) {
            res[i-1] = i;
        }

        return res;
    }

    /* 方法一：模拟加法，每次末位加1，最高位溢出后跳出 */
    public void printNumbers1(int n) {
        char [] num = new char[n];

        for (int i = 0; i < num.length; i ++) {  // 一定要初始化
            num[i] = '0';
        }

        while (!myIncrementOne(num)) {
            myPrintBigNum(num);
        }
    }

    public boolean myIncrementOne(char [] num) {
        int nTakeOver = 0;
        boolean isOverflow = false;

        for (int i = num.length - 1; i >= 0; i--) {  
            int nSum = num[i] - '0' + nTakeOver;  // 当前数值

            if (i == num.length - 1) { //每次从最低位开始加1
                nSum++;
            }

            if (nSum >= 10) {  // 满十进位
                
                if (i == 0) {   // 到了最高位
                    isOverflow = true;
                    break;
                }

                // 进位
                nSum -= 10;
                nTakeOver = 1;
                num[i] = (char) ('0' + nSum);
            } else {  // 没有进位，不需要继续下去了
                num[i] = (char) ('0' + nSum);
                break;
            }
        }

        return isOverflow;
    }

    /* 方法二：考虑大数限制，全排列递归 */
    public void printNumbers2(int n) {
        char [] num = new char[n];
        for (int i = 0; i < 10; i++) {  // 第n位
            num[0] = (char) (i + '0');
            myBigNum(num, num.length, 0);
        }

    }

    /* 自己的打印方法 */
    public void myPrintBigNum(char [] num) {
        boolean isBegin0 = true;

        for (int i = 0; i < num.length; i++) {  // 第n-1位
            if ((i <= (num.length - 1)) && (num[i] == '0') && isBegin0) {
                continue;
            } else {
                isBegin0 = false;
            }
            System.out.print(num[i]);
        }

        System.out.println();
    }

    public void myBigNum(char [] num, int nLen, int idx) {
        if (idx == nLen - 1) {
            myPrintBigNum(num);
            return;
        }

        for (int i = 0; i < 10; i++) {
            num[idx + 1] = (char) (i + '0');
            myBigNum(num, nLen, idx + 1);
        }
    }

    public static void main(String[] args) {

        Offer17 offer17 = new Offer17();

        int n = 2;
        int [] ret = offer17.printNumbers(n);
        /*for (int i = 0; i < ret.length; i++) {
            System.out.printf("%d\t", ret[i]);

            if ((i + 1) % 10 == 0) {
                System.out.println();
            }
        }*/

        //int类型转char类型
        int number = 9;
        char cNumber = (char) (number + '0');
        System.out.println("Number " + number + " to char is:" + cNumber);

        //char类型转int类型
        char cNumber2 = '3';
        int number2 = cNumber2 - '0';
        System.out.println("Char " + cNumber2 + " to number is: " + number2);

        n = 2;
//        offer17.printNumbers2(n);
        offer17.printNumbers1(n);

    }
}
