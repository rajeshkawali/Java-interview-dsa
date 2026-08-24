package com.rajeshkawali.dsa.recursion;

public class HouseRobberRecursion {

    /*
     * Problem:
     * --------
     * House Robber problem using recursion.
     * You cannot rob two adjacent houses. Find the maximum money you can rob.
     *
     * Variants:
     * 1. Pure Recursion
     * 2. Recursion + Memoization (Top-Down DP)
     * 3. Iterative DP (Bottom-Up)
     * 4. Space Optimized DP
     *
     * Time Complexity:
     * ----------------
     * - Pure Recursion: O(2^n) (exponential)
     * - Memoization: O(n)
     * - Iterative DP: O(n)
     * - Space Optimized DP: O(n) time, O(1) space
     *
     * Space Complexity:
     * -----------------
     * - Pure Recursion: O(n) stack
     * - Memoization: O(n)
     * - Iterative DP: O(n)
     * - Space Optimized: O(1)
     */

    public static void main(String[] args) {
        int[] houses = {2, 7, 9, 3, 1};
        System.out.println("============================================");
        System.out.println("Pure Recursion: " + robRec(houses, 0));
        System.out.println("============================================");
        System.out.println("Memoization: " + robMemo(houses, 0, new int[houses.length]));
        System.out.println("============================================");
        System.out.println("Iterative DP: " + robDP(houses));
        System.out.println("============================================");
        System.out.println("Space Optimized DP: " + robOptimized(houses));
        System.out.println("============================================");
        System.out.println("Rob The House: " + robTheHouse(houses, 0));
        System.out.println("============================================");
    }

    // 1. Pure Recursion
    public static int robRec(int[] nums, int i) {
        if (i >= nums.length) return 0;
        int rob = nums[i] + robRec(nums, i + 2);
        int skip = robRec(nums, i + 1);
        return Math.max(rob, skip);
    }

    // 2. Recursion + Memoization
    public static int robMemo(int[] nums, int i, int[] memo) {
        if (i >= nums.length) return 0;
        if (memo[i] != -1) return memo[i];
        int rob = nums[i] + robMemo(nums, i + 2, memo);
        int skip = robMemo(nums, i + 1, memo);
        memo[i] = Math.max(rob, skip);
        return memo[i];
    }

    // 3. Iterative DP (Bottom-Up)
    public static int robDP(int[] nums) {
        if (nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < nums.length; i++) {
            dp[i] = Math.max(nums[i] + dp[i - 2], dp[i - 1]);
        }
        return dp[nums.length - 1];
    }

    // 4. Space Optimized DP
    public static int robOptimized(int[] nums) {
        if (nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        int prev2 = nums[0];
        int prev1 = Math.max(nums[0], nums[1]);
        for (int i = 2; i < nums.length; i++) {
            int curr = Math.max(nums[i] + prev2, prev1);
            prev2 = prev1;
            prev1 = curr;
        }
        return prev1;
    }
    
    /*
     * Approach:
     * ---------
     * At each index, you have two choices:
     * 1. Rob the current house → add nums[index] + recurse for index+2
     * 2. Skip the current house → recurse for index+1
     * Return the maximum of these two choices.
     *
     * Time Complexity:
     * ----------------
     * - Pure Recursion: O(2^n) (exponential, because each house has two choices)
     *
     * Space Complexity:
     * -----------------
     * - O(n) recursion stack depth (in worst case)
     */
    public static int robTheHouse(int[] nums, int index) {
        // Base case: if index goes beyond array length, no money can be robbed
        if (index >= nums.length) {
            return 0;
        }
        // Choice 1: Rob this house and move to index+2 (skip next house)
        int robCurrent = nums[index] + robTheHouse(nums, index + 2);
        // Choice 2: Skip this house and move to index+1
        int skipCurrent = robTheHouse(nums, index + 1);
        // Return the maximum of robbing or skipping
        return Math.max(robCurrent, skipCurrent);
    }

}
