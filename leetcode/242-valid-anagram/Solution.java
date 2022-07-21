class Solution {
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) return false;

        int[] occurrences = new int[26]; // size is the number of possible unique characters
        // Increase the occurences for characters in `s`
        // Decrease the occurences for characters in `t`
        for (int i = 0; i < s.length(); i++) {
            occurrences[s.charAt(i) - 'a']++;
            occurrences[t.charAt(i) - 'a']--;
        }

        // If the occurences of characters in `t` matches `s` (they are anagrams of each other),
        // the number of occurences will all end up at 0.
        for (int count : occurrences) if (count != 0) return false;
        return true;
    }

    public static void main(String args[]) {
        Solution s = new Solution();
        System.out.println(s.isAnagram("anagram", "nagaram"));
        System.out.println(s.isAnagram("rat", "car"));
    }
}
