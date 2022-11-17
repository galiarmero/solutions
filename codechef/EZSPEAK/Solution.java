/* package codechef; // don't place package name! */

import java.util.*;
import java.lang.*;
import java.io.*;

/* Name of the class has to be "Main" only if the class is public. */
class Solution {
    static class FastReader {
        BufferedReader br;
        StringTokenizer st;
  
        public FastReader()
        {
            br = new BufferedReader(
                new InputStreamReader(System.in));
        }
  
        String next()
        {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }
  
        int nextInt() { return Integer.parseInt(next()); }
  
        long nextLong() { return Long.parseLong(next()); }
  
        double nextDouble()
        {
            return Double.parseDouble(next());
        }
  
        String nextLine()
        {
            String str = "";
            try {
                if(st.hasMoreTokens()){
                    str = st.nextToken("\n");
                }
                else{
                    str = br.readLine();
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }
    }

    private final Set<Character> vowels = new HashSet<>(Arrays.asList('a', 'A', 'e', 'E', 'i', 'I', 'o', 'O', 'u', 'U'));

    public boolean isEasyToPronounce(String word) {
        int consonants = 0;

        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);   

            if (vowels.contains(c)) {
                consonants = 0;
            } else {
                consonants++;

                if (consonants == 4) return false;
            }
        }
        return true;
    }

    private void processAndPrint(String word) {
        System.out.println(isEasyToPronounce(word) ? "YES" : "NO");
    }

    public static void main(String[] args) throws java.lang.Exception {
        FastReader r = new FastReader();
        Solution solution = new Solution();
        int n = r.nextInt();

        while (n-- > 0) {
            r.nextInt();
            String word = r.nextLine();
            solution.processAndPrint(word);
        }
    }
}

