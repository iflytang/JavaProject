package src.nowcoder.SwordOffer;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Locale;

/**
 * @author tsf
 * @date 21-03-25
 * @desp
 * 剑指 Offer 38. 字符串的排列
 * 输入一个字符串，打印出该字符串中字符的所有排列。
 *
 * 你可以以任意顺序返回这个字符串数组，但里面不能有重复元素。
 *
 * 示例:
 *
 * 输入：s = "abc"
 * 输出：["abc","acb","bac","bca","cab","cba"]
 *
 *
 * 限制：
 *
 * 1 <= s 的长度 <= 8
 */

public class Offer38 {

    ArrayList<String> res = new ArrayList<>();
    char [] chars;

    public String[] permutation(String s) {
        if (s.length() == 0) {
            return new String[0];
        }

        chars = s.toCharArray();
        dfs(0);

        return res.toArray(new String[res.size()]);
    }

    public void dfs(int x) {  // 排列树的回溯法
        if (x == chars.length - 1) {
            res.add(String.copyValueOf(chars));
//            System.out.println(res.get(res.size() - 1));
            return;
        }

        HashSet<Character> characters = new HashSet<>();
        for (int i = x; i < chars.length; i++) {
            if (characters.contains(chars[i])) {
                continue;
            }
            characters.add(chars[i]);

            swap(i, x);
            dfs(x + 1);
            swap(i, x);
        }
    }

    public void swap(int a, int b) {
        char temp = chars[b];
        chars[b] = chars[a];
        chars[a] = temp;
    }

    public static void main(String[] args) {

        Offer38 offer36 = new Offer38();

        String [] ret = offer36.permutation("abc");
        for (String element: ret) {
            System.out.println(element);
        }


    }
}





