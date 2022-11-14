public class Solution {
    public String numberToWords(int number) {
        if (number == 0) return "zero";
        String[] below_twenty = new String[]{
            "", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine",
            "ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"
        };
        String[] tens = new String[]{
            "", "", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"
        };

        StringBuilder builder = new StringBuilder();

        int place = 1000;
        while (number >= 100) {
            int d = number / place;
            if (d == 0) {
                number %= place;
                place /= 10;
                continue;
            }

            String suffix;
            if (place >= 1000) suffix = " thousand";
            else suffix = " hundred";
            builder.append(below_twenty[d] + suffix + " ");
            number %= place;
            place /= 10;
        }

        while (number != 0) {
            if (number >= 20) {
                builder.append(tens[number / 10]);
                number %= 10;
                if (number > 0) {
                    builder.append("-");
                }
            } else {
                builder.append(below_twenty[number]);
                number = 0;
            }
        }

        return builder.toString().trim();
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        assert s.numberToWords(1234).equals("one thousand two hundred thirty-four");
        assert s.numberToWords(9999).equals("nine thousand nine hundred ninety-nine");
        assert s.numberToWords(3018).equals("three thousand eighteen");
        assert s.numberToWords(412).equals("four hundred twelve");
        assert s.numberToWords(2348).equals("two thousand three hundred forty-eight");
        assert s.numberToWords(6040).equals("six thousand forty");
        assert s.numberToWords(9).equals("nine");
        assert s.numberToWords(0).equals("zero");
        assert s.numberToWords(19).equals("nineteen");
    }
}
