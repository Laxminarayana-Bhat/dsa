package dp;

import java.util.HashSet;
    import java.util.Set;

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

    class Solution2 {
        public int climbStairs(int n) {
            Integer[] memo=new Integer[46];
            memo[1]=1;
            memo[2]=2;
            return helper(memo,n);
        }

        public static int helper(Integer[] memo,int n){
            if(memo[n]!=null){
                return memo[n];
            }
            memo[n]=helper(memo,n-1)+helper(memo,n-2);
            return memo[n];
        }
    }
}

