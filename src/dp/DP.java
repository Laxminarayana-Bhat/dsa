package dp;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

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

    class SolutionMinCoin {
        public int coinChange(int[] coins, int amount) {
            int[] memo = new int[amount + 1];
            if (amount < 1)
                return 0;
            memo[0] = 0;
            for (int amt = 1; amt <= amount; amt++) {
                memo[amt] = Integer.MAX_VALUE;
                for (int coin : coins) {
                    if (coin <= amt && memo[amt - coin] != Integer.MAX_VALUE) {
                        memo[amt] = Math.min(memo[amt]/* above we initialized */, memo[amt - coin] + 1);//+1 is required for below example
                    }
                }
            }
            //i = 1
            //Try coins [1,5,6,9]:
            //
            //1: valid → memo[1] = min(MAX, memo[0] + 1) = 0 + 1 = 1
            //
            //5,6,9: all > 1 → skip
            //
            //memo[1] = 1

            return memo[amount] > amount ? -1 : memo[amount];
        }

        public int coinChange2(int[] coins, int amount) {//faster solution
            int[] dp = new int[amount + 1];
            Arrays.fill(dp, amount + 1);  // initialize all as "infinity"
            dp[0] = 0;  // base case


            for (int coin : coins) {
                for (int i = coin; i <= amount; i++) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
            return dp[amount] > amount ? -1 : dp[amount];
        }
        // for [9] amt = 9
        // i = 9:
        //Coin 9 can be used:
        //
        //i - 9 = 0, and memo[0] = 0
        //
        //So:
        //memo[9] = Math.min(Integer.MAX_VALUE, memo[0] + 1)
        //→ memo[9] = 1
        //
        //✅ memo[9] = 1
    }

    abstract class Test {
        int a;

        public void display() {
            System.out.println("jshdkjfha");
        }

        public int calc(int a, int b) {
            return a;
        }
    }

    class Test2 extends Test {
        @Override
        public int calc(int a, int b) {
            return a + b;
        }

        @Override
        public void display() {
            System.out.println("override");
        }
    }

    static class Solutionabc {


        public static String encode(List<String> strs) {
            if (strs.isEmpty()) return "";
            String s = strs.toString();
            if (s.length() > 1)
                s = s.substring(1, s.length() - 1);
            s = s.replaceAll("\"", "");
            s=s+" ";
            return s;
        }

        public static List<String> decode(String str) {
            
            List<String> ans = new ArrayList<>();
            if (Objects.equals(str, "")){ans.add("");
            return ans;}
            String[] arr = str.split(",");
            for (String s : arr) {
                ans.add(s.trim());
            }
            return ans;
        }

        public static void main(String[] args) {
            System.out.println("encoded - " + encode(List.of("")));
            String enc = encode(List.of(""));
            System.out.println(decode(enc));

            System.out.println("encoded - " + encode(List.of()));
            enc = encode(List.of());
            System.out.println(decode(enc));
        }
    }


}

