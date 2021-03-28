package src.nowcoder.SwordOffer;

import java.util.Arrays;

/**
 * @author tsf
 * @date 21-03-25
 * @desp 剑指 Offer 05. 替换空格。
 * 题目描述
 * 请实现一个函数，把字符串 s 中的每个空格替换成"%20"。
 *
 * 示例 1：
 *
 * 输入：s = "We are happy."
 * 输出："We%20are%20happy."
 *
 * 限制：
 *
 * 0 <= s 的长度 <= 10000
 */

public class Offer05 {
    public String replaceSpace(String s) {
        String space = " ";
        String replaceElement = "%20";
        String res = s.replace(space, replaceElement);
//        String[] substr = s.split(" +");  // match multiple times
//        System.out.println(Arrays.toString(substr));
        System.out.println(res);
        return res;
    }

    public static void main(String[] args) {
        String s =  "We are  happy.";

        Offer05 offer05 = new Offer05();
        String res = offer05.replaceSpace(s);
        System.out.println(res);

    }
}
