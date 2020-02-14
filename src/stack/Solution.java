package stack;

/**
 * @Author: ZhengCheng
 * @Date: created in 21:35  2020/2/10
 * @Annotation:LeetCode20题--有效的括号
 */
class Solution {
    public boolean isValid(String s) {
        ArrayStack<Character> stack = new ArrayStack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } else {
                //假设栈顶没有元素 即栈为空
                if (stack.isEmpty()) {
                    return false;
                }
                char topChar = stack.pop();
                if (c == ')' && topChar != '(') {
                    return false;
                }
                if (c == '}' && topChar != '{') {
                    return false;
                }
                if (c == ']' && topChar != '[') {
                    return false;
                }
            }
        }
        //此时要确认栈是否为空
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        String s = "(){}[]";
        System.out.println(new Solution().isValid(s));
    }
}
