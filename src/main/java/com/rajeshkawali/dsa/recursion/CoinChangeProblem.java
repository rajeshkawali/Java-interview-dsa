package com.rajeshkawali.dsa.recursion;

import java.util.Arrays;

public class CoinChangeProblem {

    /*
     * Problem:
     * --------
     * Given coins[] and an amount, find the minimum number of coins
     * required to make that amount. If not possible, return -1.
     *
     * Example:
     * coins = [1,2,5], amount = 11 → Output = 3 (11 = 5+5+1)
     *
     * Variants:
     * 1. Pure Recursion
     * 2. Recursion + Memoization (Top-Down DP)
     * 3. Iterative DP (Bottom-Up Tabulation)
     * 4. Space Optimized DP
     *
     * Time Complexity:
     * ----------------
     * - Pure Recursion: O(2^amount) (exponential, very slow)
     * - Memoization: O(amount * n) (n = number of coins)
     * - DP Tabulation: O(amount * n)
     * - Space Optimized: O(amount * n) time, O(amount) space
     *
     * Space Complexity:
     * -----------------
     * - Pure Recursion: O(amount) stack
     * - Memoization: O(amount)
     * - DP Tabulation: O(amount)
     * - Space Optimized: O(amount)
     */

    // 1. Pure Recursion
    public static int coinChangeRec(int[] coins, int amount) {
        if (amount == 0) return 0;
        if (amount < 0) return Integer.MAX_VALUE; // impossible
        int min = Integer.MAX_VALUE;
        for (int coin : coins) {
            int res = coinChangeRec(coins, amount - coin);
            if (res != Integer.MAX_VALUE) {
                min = Math.min(min, res + 1);
            }
        }
        return min;
    }

    // 2. Recursion + Memoization
    public static int coinChangeMemo(int[] coins, int amount, int[] memo) {
        if (amount == 0) return 0;
        if (amount < 0) return Integer.MAX_VALUE;
        if (memo[amount] != -1) return memo[amount];

        int min = Integer.MAX_VALUE;
        for (int coin : coins) {
            int res = coinChangeMemo(coins, amount - coin, memo);
            if (res != Integer.MAX_VALUE) {
                min = Math.min(min, res + 1);
            }
        }
        memo[amount] = min;
        return min;
    }

    // 3. Iterative DP (Bottom-Up Tabulation)
    public static int coinChangeDP(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1); // large value
        dp[0] = 0;

        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (i - coin >= 0) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }

    // 4. Space Optimized DP (same as tabulation, but dp array is minimal)
    public static int coinChangeOptimized(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (i - coin >= 0 && dp[i - coin] != Integer.MAX_VALUE) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }
        return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
    }

    public static void main(String[] args) {
        int[] coins = {1, 2, 5};
        int amount = 11;
        System.out.println("============================================");
        // 1. Pure Recursion
        int res1 = coinChangeRec(coins, amount);
        System.out.println("Pure Recursion: " + (res1 == Integer.MAX_VALUE ? -1 : res1));
        System.out.println("============================================");
        // 2. Memoization
        int[] memo = new int[amount + 1];
        Arrays.fill(memo, -1);
        int res2 = coinChangeMemo(coins, amount, memo);
        System.out.println("Memoization: " + (res2 == Integer.MAX_VALUE ? -1 : res2));
        System.out.println("============================================");
        // 3. Iterative DP
        System.out.println("DP Tabulation: " + coinChangeDP(coins, amount));
        System.out.println("============================================");
        // 4. Space Optimized DP
        System.out.println("Space Optimized DP: " + coinChangeOptimized(coins, amount));
        System.out.println("============================================");

    }
}

/*
Short Note:
Pure recursion → exponential, impractical for large amounts.
Memoization → avoids recomputation, O(amount * n).
DP Tabulation → bottom‑up, efficient and clear.
Space optimized → same as tabulation but minimal memory usage.
*/