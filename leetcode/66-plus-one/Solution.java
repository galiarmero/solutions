import java.util.Arrays;

class Solution {
    public int[] plusOne(int[] digits) {
        for (int i = digits.length - 1; i >= 0; i--) {
            int sum = (digits[i] + 1);
            digits[i] = sum % 10;

            if (sum < 10) return digits;
        }

        int[] carryOne = new int[digits.length + 1];
        carryOne[0] = 1; // Set the first element to 1. The rest of the digits are surely 0!
        return carryOne;
    }

    public static void main(String[] args) {
        Solution s = new Solution();

        assert Arrays.equals(s.plusOne(new int[]{1,2,3}), new int[]{1,2,4}); 
        assert Arrays.equals(s.plusOne(new int[]{4,3,2,1}), new int[]{4,3,2,2});
        assert Arrays.equals(s.plusOne(new int[]{9,9,9}), new int[]{1,0,0,0});
        assert Arrays.equals(s.plusOne(new int[]{9}), new int[]{1,0});
    }
}
