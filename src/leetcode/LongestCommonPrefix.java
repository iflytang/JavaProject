package src.leetcode;

/**
 * @author tsf
 * @date 21-03-25
 * @desp
 */

public class LongestCommonPrefix {

    // 方法一：横向扫描
    public String longestCommonPrefix(String [] strs) {
        // boundary check
        if (strs.length == 0) {
            return "";
        }

        // compare with each other
        String prefix = strs[0];
        for (int i = 1; i < strs.length; i++) {
            prefix = longestCommonPrefix(prefix, strs[i]);

            if (prefix.length() == 0) {  // no common prefix
                break;
            }
        }

        return prefix;
    }

    public String longestCommonPrefix(String str1, String str2) {
        int len = Math.min(str1.length(), str2.length());

        int index = 0;

        while (index < len && (str1.charAt(index) == str2.charAt(index))) {
            index++;
        }

        return str1.substring(0, index);
    }

    // 方法二：纵向扫描
    public String longestCommonPrefix2(String [] strs) {

        // boundary check
        if (strs.length == 0) {
            return "";
        }

        // choose any one as longest length
        int len = strs[0].length();

        int cols;
        for (cols = 0; cols < len; cols++) {

            char c = strs[0].charAt(cols);

            for (int rows = 1; rows < strs.length; rows++) {
                if (cols == strs[rows].length() || c != strs[rows].charAt(cols)) {
                    return strs[0].substring(0, cols);
                }
            }
        }

        return strs[0];
    }

    public static void main (String [] args) {

        LongestCommonPrefix lcp = new LongestCommonPrefix();

//        String [] str = {};
        String [] str = {"flower", "flow", "flight"};
        String ret = lcp.longestCommonPrefix(str);
        System.out.println(ret);

        String [] str2 = {"flower", "flow", "flower"};
        ret = lcp.longestCommonPrefix2(str2);
        System.out.println(ret);
    }
}
