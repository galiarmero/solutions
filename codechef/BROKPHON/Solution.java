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

    public int countPlayersAtFault(int[] messages) {
        int atFaultPlayers = 0;

        if (messages[0] != messages[1])
            atFaultPlayers++;

        for (int i = 1; i <= messages.length - 2; i++) {
            // This player either misheard or misspoke. It is at fault!
            if (messages[i] != messages[i - 1] || messages[i] != messages[i + 1]) {
                atFaultPlayers++;
            }
        }

        if (messages[messages.length - 1] != messages[messages.length - 2])
            atFaultPlayers++;

        return atFaultPlayers;
    }

    private void processAndPrint(int[] messages) {
        System.out.println(countPlayersAtFault(messages));
    }

    public static void main(String[] args) throws java.lang.Exception {
        FastReader fr = new FastReader();
        Solution solution = new Solution();

        int T = fr.nextInt();
        while (T-- > 0) {
            int N = fr.nextInt();
            int[] messages = new int[N];
            for (int i = 0; i < N; i++)
                messages[i] = fr.nextInt();

            solution.processAndPrint(messages);
        }
    }
}

