package com.rajeshkawali.dsa.recursion;

import java.util.Arrays;

public class CoinChange {

    /*
     * Problem:
     * --------
     * Count number of ways to make a given amount using coins[].
     * Unlimited supply of each coin.
     *
     * Variants:
     * 1. Pure Recursion (Include/Exclude)
     * 2. Recursion + Memoization (Top-Down DP)
     * 3. Iterative DP (Bottom-Up Tabulation)
     *
     * Time Complexity:
     * ----------------
     * - Pure Recursion: O(2^n) (exponential)
     * - Memoization: O(n * amount)
     * - DP Tabulation: O(n * amount)
     *
     * Space Complexity:
     * -----------------
     * - Pure Recursion: O(amount) stack
     * - Memoization: O(n * amount)
     * - DP Tabulation: O(n * amount)
     */

    // 1. Pure Recursion (Include/Exclude)
	public static int coinChangeRec(int[] coins, int amount, int index) {
        // Base cases
        if (amount == 0) {
        		return 1; // found valid combination
        }
        if (amount < 0 || index >= coins.length) {
        		return 0; // invalid
        }
        // Choice 1: include coin[index]
        int include = coinChangeRec(coins, amount - coins[index], index);
        // Choice 2: exclude coin[index]
        int exclude = coinChangeRec(coins, amount, index + 1);
        return include + exclude;
    }

    // 2. Recursion + Memoization
    public static int coinChangeMemo(int[] coins, int amount, int index, int[][] memo) {
        if (amount == 0) return 1;
        if (amount < 0 || index >= coins.length) return 0;
        if (memo[index][amount] != -1) return memo[index][amount];

        int include = coinChangeMemo(coins, amount - coins[index], index, memo);
        int exclude = coinChangeMemo(coins, amount, index + 1, memo);

        memo[index][amount] = include + exclude;
        return memo[index][amount];
    }

    // 3. Iterative DP (Bottom-Up Tabulation)
    public static int coinChangeDP(int[] coins, int amount) {
        int n = coins.length;
        int[][] dp = new int[n + 1][amount + 1];

        // Base case: amount = 0 → 1 way (choose nothing)
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 1;
        }

        for (int i = n - 1; i >= 0; i--) {
            for (int a = 1; a <= amount; a++) {
                int include = (a - coins[i] >= 0) ? dp[i][a - coins[i]] : 0;
                int exclude = dp[i + 1][a];
                dp[i][a] = include + exclude;
            }
        }
        return dp[0][amount];
    }

    public static void main(String[] args) {
        int[] coins = {1, 2, 5};
        int amount = 5;
        System.out.println("============================================");
        // 1. Pure Recursion
        System.out.println("Pure Recursion: " + coinChangeRec(coins, amount, 0));
        System.out.println("============================================");
        // 2. Memoization
        int[][] memo = new int[coins.length][amount + 1];
        for (int[] row : memo) Arrays.fill(row, -1);
        System.out.println("Memoization: " + coinChangeMemo(coins, amount, 0, memo));
        System.out.println("============================================");
        // 3. Iterative DP
        System.out.println("DP Tabulation: " + coinChangeDP(coins, amount));
        System.out.println("============================================");
    }
}

/*
Short Note:
Include/Exclude recursion → exponential, but conceptually simple.
Memoization → avoids recomputation, O(n * amount).
DP Tabulation → bottom‑up, efficient and clear.
*/