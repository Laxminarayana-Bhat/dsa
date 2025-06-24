package dp;

import java.util.HashSet;
    import java.util.Set;

public class DP {

    class Solution {
        public int fib(int n) {
            Integer[] memo = new Integer[n+1];
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
            Integer[] memo=new Integer[n+1];
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

    class SolutionTopDown {
        public int minCostClimbingStairs(int[] cost) {
            Integer[] memo = new Integer[cost.length + 1];
            return helper(memo, cost.length, cost);
        }

        public static int helper(Integer[] memo, int n, int[] cost) {
            if (n <= 1)
                return 0;
            if (memo[n] != null)
                return memo[n];
            memo[n] = Math.min(helper(memo, n - 1, cost) + cost[n - 1],
                    helper(memo, n - 2, cost) + cost[n - 2]);//we should store it else whats the use of memo
            return memo[n];
        }
    }

    class SolutionBottomUp {
        public int minCostClimbingStairs(int[] cost) {
            int n=cost.length;
            int mincost[] = new int[n+1];
            if(n==0||n==1){
                return 0;
            }
            for(int i=2;i<=n;i++){
                mincost[i]=Math.min((cost[i-1]+mincost[i-1]),(cost[i-2]+mincost[i-2]));
            }
            return mincost[n];
        }
    }
}

