package dp;

public class DP {

    class Solution {
        public int fib(int n) {
            Integer[] memo = new Integer[31];
            return fibHelper(n, memo);
        }

        private int fibHelper(int n, Integer[] memo) {
            if (n == 0 || n == 1) return n;
            if (memo[n] != null) return memo[n];
            memo[n] = fibHelper(n - 1, memo) + fibHelper(n - 2, memo);
            return memo[n];
        }
    }
}
