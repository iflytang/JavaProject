package src.nowcoder.SwordOffer;

import java.util.*;

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

    ArrayList<String> res2 = new ArrayList<>();
//    char [] chars;

    public String[] permutation2(String s) {
        if (s.length() == 0) {
            return new String[0];
        }

        char [] chars2 = s.toCharArray();
        dfs2(chars2, 0);

        return res2.toArray(new String[res2.size()]);
    }

    public void dfs2(char [] chars2, int x) {  // 排列树的回溯法
        if (x == chars2.length - 1) {
            res2.add(String.copyValueOf(chars2));
//            System.out.println(res.get(res.size() - 1));
            return;
        }

        HashSet<Character> characters = new HashSet<>();
        for (int i = x; i < chars2.length; i++) {
            if (characters.contains(chars2[i])) {
                continue;
            }
            characters.add(chars2[i]);

            swap2(chars2, i, x);
            dfs2(chars2,x + 1);
            swap2(chars2, i, x);
        }
    }

    public void swap2(char [] chars, int a, int b) {
        char temp = chars[b];
        chars[b] = chars[a];
        chars[a] = temp;
    }

    public static void main(String[] args) {

        Offer38 offer36 = new Offer38();

        String [] ret = offer36.permutation("abb");
        for (String element: ret) {
            System.out.print(element + " ");
        }
        System.out.println("\n================");

        String [] ret2 = offer36.permutation2("abb");
        for (String element: ret2) {
            System.out.print(element + " ");
        }


    }
}





