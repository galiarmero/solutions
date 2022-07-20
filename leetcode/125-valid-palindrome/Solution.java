class Solution {
    public boolean isPalindrome(String s) {
        int front = 0;
        int back = s.length() - 1;

        while (front < back) {
            if (!Character.isLetterOrDigit(s.charAt(front))) {
                front++;
                continue;
            }

            if (!Character.isLetterOrDigit(s.charAt(back))) {
                back--;
                continue;
            }

            if (Character.toLowerCase((s.charAt(front))) != Character.toLowerCase((s.charAt(back)))) {
                return false;
            }

            front++;
            back--;
        }

        return true;
    }

    public static void main(String args[]) {
        Solution s = new Solution();

        // System.out.println(s.isPalindrome("A man, a plan, a canal: Panama"));
        // System.out.println(s.isPalindrome("race a car"));
        System.out.println(s.isPalindrome("ab2BA"));

        return;
    }
}
