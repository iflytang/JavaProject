package src.leetcode;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * @author tsf
 * @date 21-03-25
 * @desp https://leetcode-cn.com/problems/valid-parentheses/
 *
 * 20. 有效的括号
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
 *
 * 有效字符串需满足：
 *
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 *
 *
 * 示例 1：
 *
 * 输入：s = "()"
 * 输出：true
 * 示例 2：
 *
 * 输入：s = "()[]{}"
 * 输出：true
 * 示例 3：
 *
 * 输入：s = "(]"
 * 输出：false
 * 示例 4：
 *
 * 输入：s = "([)]"
 * 输出：false
 * 示例 5：
 *
 * 输入：s = "{[]}"
 * 输出：true
 *
 *
 * 提示：
 *
 * 1 <= s.length <= 104
 * s 仅由括号 '()[]{}' 组成
 */

public class ValidParentheses {

    public boolean isValid(String s) {

        // must be even
        int n = s.length();

        if (n % 2 == 1) {  // odd
            return false;
        }

        // use map and stack
        Map<Character, Character> hashMap = new HashMap<Character, Character>();
        hashMap.put(')', '(');
        hashMap.put(']', '[');
        hashMap.put('}', '{');

        Deque<Character> stack = new LinkedList<>();

        for (int i = 0; i < s.length(); i++) {

            char c = s.charAt(i);

            if (hashMap.containsKey(c)) { // ), ], }
                if (stack.isEmpty() || stack.peekFirst() != hashMap.get(c)) {  // peek()
                    return false;
                }
                stack.removeFirst();  // pop()

            } else {  // (, [, {
                stack.addFirst(c);
            }
        }

        return stack.isEmpty();
    }

    public static void main (String [] args) {
        ValidParentheses vp = new ValidParentheses();

        Boolean ret = vp.isValid("{[}](){}");
        System.out.println(ret);
    }
}
