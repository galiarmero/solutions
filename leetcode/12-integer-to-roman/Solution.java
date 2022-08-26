import java.util.Map;
import static java.util.Map.entry;

class Solution {
    public String intToRoman(int num) {
        int values[] = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String symbols[] = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

        StringBuilder roman = new StringBuilder();
        int i = 0;
        while (num > 0) {
            while (num >= values[i]) {
                num -= values[i];
                roman.append(symbols[i]);
            }
            i++;
        }

        return roman.toString();
    }

    public String intToRomanByPlaceValue(int num) {
        Map<Integer, String> symbols = Map.ofEntries(
            entry(1,    "I"),
            entry(4,    "IV"),
            entry(5,    "V"),
            entry(9,    "IX"),
            entry(10,   "X"),
            entry(40,   "XL"),
            entry(50,   "L"),
            entry(90,   "XC"),
            entry(100,  "C"),
            entry(400,  "CD"),
            entry(500,  "D"),
            entry(900,  "CM"),
            entry(1000, "M")
        );

        StringBuilder roman = new StringBuilder();
        int place = 1000;
        while (num > 0) {
            int digit = num / place;

            if (digit == 4 || digit == 9) {
                roman.append(symbols.get(digit * place));
            }
            else if (digit < 4) {
                roman.append(symbols.get(place).repeat(digit));
            }
            else {
                roman.append(symbols.get(place * 5));
                roman.append(symbols.get(place).repeat(digit % 5));
            }

            num -= (digit * place);
            place /= 10;
        }

        return roman.toString();
    }

    public static void main(String args[]) {
        Solution s = new Solution();

        assert s.intToRoman(3).equals("III");
        assert s.intToRoman(5).equals("V");
        assert s.intToRoman(50).equals("L");
        assert s.intToRoman(58).equals("LVIII");
        assert s.intToRoman(1994).equals("MCMXCIV");
        assert s.intToRoman(4).equals("IV");
        assert s.intToRoman(9).equals("IX");
        assert s.intToRoman(40).equals("XL");
        assert s.intToRoman(90).equals("XC");
        assert s.intToRoman(400).equals("CD");
        assert s.intToRoman(900).equals("CM");
        assert s.intToRoman(3999).equals("MMMCMXCIX");
    }
}
