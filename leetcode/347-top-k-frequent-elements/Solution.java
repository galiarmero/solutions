import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> frequency = new HashMap<>();
        List<Integer> uniqueNums = new ArrayList<>();
        for (int num : nums) {
            int count = 0;
            if (!frequency.containsKey(num)) {
                uniqueNums.add(num);
            } else {
                count = frequency.get(num);
            }

            frequency.put(num, count + 1);
        }

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(k, (a, b) -> frequency.get(b) - frequency.get(a));
        for (Integer u : uniqueNums) {
            maxHeap.offer(u);
        }

        int[] kFrequent = new int[k];
        for (int i = 0; i < k; i++) {
            kFrequent[i] = maxHeap.poll();
        }

        return kFrequent;
    }

    public int[] topKFrequentSort(int[] nums, int k) {
        Map<Integer, Integer> frequency = new HashMap<>();
        List<Integer> uniqueNums = new ArrayList<>();
        for (int num : nums) {
            int count = 0;
            if (!frequency.containsKey(num)) {
                uniqueNums.add(num);
            } else {
                count = frequency.get(num);
            }

            frequency.put(num, count + 1);
        }
        Collections.sort(uniqueNums, (a, b) -> frequency.get(b) - frequency.get(a));

        int[] kFrequent = new int[k];
        for (int i = 0; i < k; i++) {
            kFrequent[i] = uniqueNums.get(i);
        }

        return kFrequent;
    }

    public static void main(String[] args) {
        Solution s = new Solution();

        assert Arrays.equals(s.topKFrequent(new int[]{1,1,1,2,2,3}, 2), new int[]{1, 2});
        assert Arrays.equals(s.topKFrequent(new int[]{1}, 1), new int[]{1});
    }
}
