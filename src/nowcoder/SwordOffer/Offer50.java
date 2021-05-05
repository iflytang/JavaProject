package src.nowcoder.SwordOffer;

import jdk.internal.org.objectweb.asm.Handle;

import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * @author tsf
 * @date 21-03-25
 * @desp
 * 剑指 Offer 50. 第一个只出现一次的字符
 * 在字符串 s 中找出第一个只出现一次的字符。如果没有，返回一个单空格。 s 只包含小写字母。
 *
 * 示例:
 *
 * s = "abaccdeff"
 * 返回 "b"
 *
 * s = ""
 * 返回 " "
 *
 *
 * 限制：
 *
 * 0 <= s 的长度 <= 50000
 */

public class Offer50 {

    public char firstUniqChar(String s) {  // 根据HashMap实现

        char ret = ' ';

        if (s.length() == 0) {
            return ret;
        }

//        HashMap<Character, Integer> hashMap = new HashMap<>();
        HashMap<Character, Integer> hashMap = new LinkedHashMap<>();
        char [] chars = s.toCharArray();

        for (int i = 0; i < s.length(); i++) {

            if (hashMap.containsKey(chars[i])) {
                hashMap.put(chars[i], hashMap.get(chars[i]) + 1);
            } else {
                hashMap.put(chars[i], 1);
            }
        }

        /*for (int i = 0; i < s.length(); i++) {  // HashMap是无序的，LinkedHashMap有序
            if (hashMap.get(chars[i]) == 1) {
                ret = chars[i];
                break;
            }
        }*/

        for (Character key: hashMap.keySet()) {  // LinkedHashMap有序
            if (hashMap.get(key) == 1) {
                ret = key;
                break;
            }
        }

        return ret;
    }

    public char firstUniqChar2(String s) {  // 根据char数组实现，java中char 2字节
        int [] arr = new int [26];

        char [] chars = s.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            arr[chars[i] - 'a']++;
        }

        for (int i = 0; i < chars.length; i++) {
            if (arr[chars[i] - 'a']  == 1) {
                return chars[i];
            }
        }

        return ' ';
    }

    public static void main(String[] args) {

        Offer50 offer50 = new Offer50();

        String s = "abaccdeff";
        char ret = offer50.firstUniqChar(s);
        System.out.println(ret);
        System.out.println("==========================");

        s = "";
        ret = offer50.firstUniqChar(s);
        System.out.println(ret);
        System.out.println("==========================");

        s = "leetcode";
        ret = offer50.firstUniqChar(s);
        System.out.println(ret);
        System.out.println("==========================");

        s = "leetcode";
        ret = offer50.firstUniqChar2(s);
        System.out.println(ret);
        System.out.println("==========================");

    }
}





