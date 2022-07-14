import java.util.Set;
import java.util.HashSet;
import java.util.Arrays;

class Solution {
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> foundNums = new HashSet<>();
        for (int num : nums) {
            if (foundNums.contains(num)) return true;

            foundNums.add(num);
        }
        return false;
    }

    public boolean containsDuplicateMemoryEfficient(int[] nums) {
        Arrays.sort(nums);
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) return true;
        }
        return false;
    }
}
