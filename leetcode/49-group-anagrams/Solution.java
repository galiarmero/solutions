import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> groups = new HashMap<>();
        for (String str : strs) {
            String key = getAnagramKey(str);
            if (!groups.containsKey(key)) {
                groups.put(key, new ArrayList<>());
            }
            groups.get(key).add(str);
        }
        return new ArrayList<>(groups.values());
    }

    private String getAnagramKey(String str) {
        char[] keyChars = new char[26];
        for (char c : str.toCharArray()) {
            keyChars[c - 'a']++;
        }
        return new String(keyChars);
    }

    public static void main(String args[]) {
        Solution s = new Solution();
        System.out.println(s.groupAnagrams(new String[]{"eat","tea","tan","ate","nat","bat"}));
    }
}
