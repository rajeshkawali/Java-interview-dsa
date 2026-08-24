package com.rajeshkawali.dsa.recursion;

public class ClimbingStairsRecursion {

    /*
     * Problem:
     * --------
     * Count distinct ways to climb n stairs,
     * where each move can be 1 or 2 steps.
     *
     * Variants:
     * 1. Pure Recursion
     * 2. Recursion + Memoization (Top-Down DP)
     * 3. Iterative DP (Bottom-Up)
     * 4. Space Optimized DP
     */

    // 1. Pure Recursion
    // Time: O(2^n), Space: O(n) recursion stack
	public static int climbStairsRec(int n) {
		if (n <= 1) {
			return 1;
		}
		return climbStairsRec(n - 1) + climbStairsRec(n - 2);
	}

    // 2. Recursion + Memoization
    // Time: O(n), Space: O(n)
	public static int climbStairsMemo(int n, int[] memo) {
		if (n <= 1) {
			return 1;
		}
		if (memo[n] != 0) {
			return memo[n];
		}
		memo[n] = climbStairsMemo(n - 1, memo) + climbStairsMemo(n - 2, memo);
		return memo[n];
	}

    // 3. Iterative DP (Bottom-Up)
    // Time: O(n), Space: O(n)
	public static int climbStairsDP(int n) {
		if (n <= 1) {
			return 1;
		}
		int[] dp = new int[n + 1];
		dp[0] = dp[1] = 1;
		for (int i = 2; i <= n; i++) {
			dp[i] = dp[i - 1] + dp[i - 2];
		}
		return dp[n];
	}

    // 4. Space Optimized DP
    // Time: O(n), Space: O(1)
    public static int climbStairsOptimized(int n) {
        if (n <= 1) {
        		return 1;
        }
        int prev1 = 1, prev2 = 1;
        for (int i = 2; i <= n; i++) {
            int curr = prev1 + prev2;
            prev2 = prev1;
            prev1 = curr;
        }
        return prev1;
    }

    public static void main(String[] args) {
        int n = 5; // number of stairs
        System.out.println("============================================");
        System.out.println("Climb Stairs (Recursion): " + climbStairsRec(n));
        System.out.println("============================================");
        System.out.println("Climb Stairs (Memoization): " + climbStairsMemo(n, new int[n + 1]));
        System.out.println("============================================");
        System.out.println("Climb Stairs (DP): " + climbStairsDP(n));
        System.out.println("============================================");
        System.out.println("Climb Stairs (Optimized): " + climbStairsOptimized(n));
        System.out.println("============================================");
    }
}

/*
Short Note:
Pure Recursion → exponential, not efficient.
Memoization → top down DP, avoids recomputation.
Iterative DP → bottom up, builds solution step by step.
Space Optimized → only keeps last two results, most efficient. 
  
*/
