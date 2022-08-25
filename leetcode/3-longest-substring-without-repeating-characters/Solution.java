import java.util.HashMap;
import java.util.Map;

class Solution {
    public int lengthOfLongestSubstring(String s) {
        int maxLength = 0, currentLength = 0;

        // Map that would track the index where we last encountered a character
        Map<Character, Integer> lastIndices = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            // We've encountered this character before!
            if (lastIndices.containsKey(c)) {
                int lastIndex = lastIndices.get(c);

                if (lastIndex < i - currentLength) {
                    // If we encountered it _before_ we started the current substring,
                    // it is still non-repeating in our current substring
                    // so we count this character
                    currentLength++;
                } else {
                    // This means the last occurrence was counted in our current length
                    // so we start a new non-repeating sequence
                    // which begins from the index after the last occurrence
                    currentLength = i - lastIndex;
                }
            } else {
                // If we haven't encountered the character before, increment
                // the length of current substring
                currentLength++;
            }
            lastIndices.put(c, i);
            maxLength = Math.max(maxLength, currentLength);
        }

        return maxLength;
    }

    public static void main(String args[]) {
        Solution s = new Solution();
        assert s.lengthOfLongestSubstring("abcabcbb") == 3;
        assert s.lengthOfLongestSubstring("bbbbb") == 1;
        assert s.lengthOfLongestSubstring("pwwkew") == 3;
        assert s.lengthOfLongestSubstring("gag") == 2;
        assert s.lengthOfLongestSubstring("") == 0;
        assert s.lengthOfLongestSubstring("babcdefg") == 7;
        assert s.lengthOfLongestSubstring("bbaabcd") == 4;
        assert s.lengthOfLongestSubstring("bbaabcb") == 3;
        assert s.lengthOfLongestSubstring("bbawrtb") == 5;
    }
}
