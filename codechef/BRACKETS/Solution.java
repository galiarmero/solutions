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

    private int getMaxBalance(String sequence) {
        int maxBalance = 0;
        int balance = 0;
        for (int i = 0; i < sequence.length(); i++) {
            if (sequence.charAt(i) == '(')
                balance++;
            else
                balance--;

            if (balance > maxBalance)
                maxBalance = balance;
        }
        return maxBalance;
    }

    private String getShortenedValidParentheses(String sequence) {
        int maxBalance = getMaxBalance(sequence);
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < maxBalance; i++)
            builder.append("(");
        for (int i = 0; i < maxBalance; i++)
            builder.append(")");
        return builder.toString();
    }

    private void processAndPrint(String sequence) {
        System.out.println(getShortenedValidParentheses(sequence));
    }

    public static void main(String[] args) throws java.lang.Exception {
        FastReader fr = new FastReader();
        Solution solution = new Solution();

        int T = fr.nextInt();
        while (T-- > 0) {
            String sequence = fr.nextLine();
            solution.processAndPrint(sequence);
        }
    }
}
