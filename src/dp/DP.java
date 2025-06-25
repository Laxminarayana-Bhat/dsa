package dp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class DP {

    class Solution {
        public int fib(int n) {
            Integer[] memo = new Integer[n + 1];
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
            Integer[] memo = new Integer[n + 1];
            memo[1] = 1;
            memo[2] = 2;
            return helper(memo, n);
        }

        public static int helper(Integer[] memo, int n) {
            if (memo[n] != null) {
                return memo[n];
            }
            memo[n] = helper(memo, n - 1) + helper(memo, n - 2);
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
            int n = cost.length;
            int mincost[] = new int[n + 1];
            if (n == 0 || n == 1) {
                return 0;
            }
            for (int i = 2; i <= n; i++) {
                mincost[i] = Math.min((cost[i - 1] + mincost[i - 1]), (cost[i - 2] + mincost[i - 2]));
            }
            return mincost[n];
        }
    }

    class Solution3 {
        //house robber - end to start explore
        //Robber can rob the house in only 2 ways
        // - robbery of current house + loot from houses before the previous(n-2)
        // - loot from the previous house(n-1) robbery and any loot captured before that(recursive one)
        //rob(i) = Math.max(rob(i - 2) + currentHouseValue, rob(i - 1))

        //Recursive + memo (top-down)
        public int rob(int[] nums) {
            Integer[] memo = new Integer[nums.length + 1];
            return robber(nums, nums.length - 1, memo);
        }

        public static int robber(int[] nums, int n, Integer[] memo) {
            if (n < 0) {
                return 0;
            }
            if (memo[n] != null) {
                return memo[n];
            }
            memo[n] = Math.max(robber(nums, n - 1, memo), robber(nums, n - 2, memo) + nums[n]);
            return memo[n];
        }
    }

    //Iterative + memo (bottom-up)
    public static int robber(int[] nums, int n) {
        Integer[] memo = new Integer[nums.length + 1];
        memo[0] = 0;
        memo[1] = nums[0];
        for (int i = 2; i <= nums.length; i++) {
            memo[i] = Math.max(memo[i - 1], memo[i - 2] + nums[i - 1]);
        }
        return memo[nums.length];
    }

    //Iterative + 2 variables
    public int rob(int[] nums) {
        int v1 = 0;//for n-1
        int v2 = 0;//for n-2
        for (int i : nums) {
            int tmp = v1;
            v1 = Math.max(v1, v2 + i);//for every value check n-1 and current+n-2 and get max val
            v2 = tmp;//add n-1 to n-2 fr next loop
        }
        return v1;
    }


    //house robber II
    //here nums is circular so first and last element is adjecent
    //so we have to consider from 1st element to last-1 and 2nd element to last - and take the max to rob the max money
    // max( rob (0,n-2), rob (1,n-1) )
    class Solution4 {
        public int rob(int[] nums) {
            int n = nums.length;
            if (n == 1) return nums[0];
            Integer[] memo1 = new Integer[n + 1];
            Integer[] memo2 = new Integer[n + 1];
            return Math.max(robber(memo1, 0, n - 2, nums), robber(memo2, 1, n - 1, nums));
        }

        // from end , top to bottom with memo
        public static int robber(Integer[] memo, int start, int end, int[] nums) {
            if (end < start)
                return 0;
            if (memo[end] != null)
                return memo[end];
            memo[end] = Math.max(robber(memo, start, end - 1, nums), robber(memo, start, end - 2, nums) + nums[end]);
            return memo[end];
        }

        // from start, top to bottom with memo
        public static int robberFromStart(Integer[] memo, int start, int end, int[] nums) {
            if (end < start)
                return 0;
            if (memo[start] != null)
                return memo[start];
            memo[start] = Math.max(robber(memo, start + 1, end, nums), robber(memo, start + 2, end, nums) + nums[start]);
            return memo[start];
        }

        // memo + bottom up iterative
        private int robDP(int[] nums, int start, int end) {
            int n = end - start + 1;
            if (n == 0) return 0;
            if (n == 1) return nums[start];

            int[] dp = new int[n];
            dp[0] = nums[start];
            dp[1] = Math.max(nums[start], nums[start + 1]);

            for (int i = 2; i < n; i++) {
                dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[start + i]);
            }

            return dp[n - 1];
        }
    }
}

