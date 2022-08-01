import java.util.Stack;

class Solution {
    public boolean isValid(String s) {
        if (s.length() == 1) return false;

        Stack<Character> openingChars = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') {
                openingChars.push(c);
                continue;
            }

            if (openingChars.isEmpty()) return false;
            if (c == ')' && openingChars.pop() != '(') return false;
            if (c == '}' && openingChars.pop() != '{') return false;
            if (c == ']' && openingChars.pop() != '[') return false;
        }

        return openingChars.isEmpty();
    }

    public static void main(String args[]) {
        Solution s = new Solution();
        System.out.println(s.isValid("()"));
        System.out.println(s.isValid("()[]{}"));
        System.out.println(s.isValid("(]"));
        System.out.println(s.isValid("([]"));
        System.out.println(s.isValid("}["));
    }
}
