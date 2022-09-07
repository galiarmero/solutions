class Solution {
    public int mySqrt(int x) {
        if (x < 2) return x;

        long left = 1, right = x;
        long midpoint;

        while (left < right) {
            midpoint = left + (right - left) / 2;

            if (midpoint * midpoint > x)
                right = midpoint;
            else
                left = midpoint + 1;
        }

        return (int) left - 1;
    }

    public static void main(String args[]) {
        Solution s = new Solution();
        assert s.mySqrt(1) == 1;
        assert s.mySqrt(4) == 2;
        assert s.mySqrt(8) == 2;
        assert s.mySqrt(9) == 3;
        assert s.mySqrt(100) == 10;
        assert s.mySqrt(361) == 19;
        assert s.mySqrt(501) == 22;
        assert s.mySqrt(2147483647) == 46340;
    }
}
