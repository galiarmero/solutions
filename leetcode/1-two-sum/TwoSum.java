import java.util.Map;
import java.util.HashMap;

class TwoSum {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> indexLookup = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (indexLookup.containsKey(target - nums[i])) {
                return new int[] { indexLookup.get(target - nums[i]), i };
            }
            indexLookup.put(nums[i], i);
        }
        return null;
    }
}