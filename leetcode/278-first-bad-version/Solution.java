/* The isBadVersion API is defined in the parent class VersionControl.
      boolean isBadVersion(int version); */

public class Solution extends VersionControl {
    public int firstBadVersion(int n) {
        int left = 1, right = n;
        int mid;

        while (left < right) {
            mid = left + (right - left) / 2;
            if (isBadVersion(mid))
                right = mid;
            else
                left = mid + 1;
        }
        return left;
    }

    public static void main(String args[]) {
        Solution s = new Solution();
        assert s.firstBadVersion(6) == 4;
        assert s.firstBadVersion(7) == 4;
        assert s.firstBadVersion(8) == 4;
    }
}
