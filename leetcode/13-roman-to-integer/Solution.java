import java.util.Map;

class Solution {
    public int romanToInt(String s) {
        int[] intValues = new int[24];
        intValues['I' - 'A'] = 1;
        intValues['V' - 'A'] = 5;
        intValues['X' - 'A'] = 10;
        intValues['L' - 'A'] = 50;
        intValues['C' - 'A'] = 100;
        intValues['D' - 'A'] = 500;
        intValues['M' - 'A'] = 1000;

        int i = 0, len = s.length();
        int result = 0;

        while (i < len) {
            char c = s.charAt(i);
            int intVal = intValues[c - 'A'];

            if (c == 'I' || c == 'X' || c == 'C') {
                // Ignoring invalid scenarios here which is not
                // within the specified constraints (e.g. 'IC', 'XM')
                if (i + 1 < len && intVal < intValues[s.charAt(i + 1) - 'A']) {
                    result += intValues[s.charAt(i + 1) - 'A'] - intVal;
                    i += 2;
                    continue;
                }
            }

            result += intValues[c - 'A'];
            i++;
        }
        return result;
    }

    public int romanToIntUsingMap(String s) {
        Map<Character, Integer> intValues = Map.of(
            'I', 1,
            'V', 5,
            'X', 10,
            'L', 50,
            'C', 100,
            'D', 500,
            'M', 1000
        );
        int i = 0, len = s.length();
        int result = 0;

        while (i < len) {
            char c = s.charAt(i);
            int intVal = intValues.get(c);

            if (c == 'I' || c == 'X' || c == 'C') {
                // Ignoring invalid scenarios here which is not
                // within the specified constraints (e.g. 'IC', 'XM')
                if (i + 1 < len && intVal < intValues.get(s.charAt(i + 1))) {
                    result += intValues.get(s.charAt(i + 1)) - intVal;
                    i += 2;
                    continue;
                }
            }

            result += intValues.get(c);
            i++;
        }
        return result;
    }

    public static void main(String args[]) {
        Solution s = new Solution();
        assert s.romanToInt("III") == 3;
        assert s.romanToInt("LVIII") == 58;
        assert s.romanToInt("MCMXCIV") == 1994;
        assert s.romanToInt("MMMCMXCIX") == 3999;
        assert s.romanToInt("XC") == 90;
        assert s.romanToInt("XCIX") == 99;
    }
}
