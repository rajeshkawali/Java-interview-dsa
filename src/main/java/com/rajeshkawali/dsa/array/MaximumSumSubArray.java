package com.rajeshkawali.dsa.array;

// Given an integer array, find the maximum sum of any contiguous subarray.
public class MaximumSumSubArray {

	/*
     * Approach 1: Brute Force
     * - Generate all subarrays and compute their sums.
     * - Track the maximum sum found.
     * Time Complexity: O(n^2) (optimized with running sum; O(n^3) if recomputed each time)
     * Space Complexity: O(1)
     */
	public static int maxSubArrayBruteForce(int[] arr) {
		int n = arr.length;
		int maxSum = Integer.MIN_VALUE;
		for (int i = 0; i < n; i++) {
			int sum = 0;
			for (int j = i; j < n; j++) {
				sum += arr[j]; //sum = sum + arr[j];
				maxSum = Math.max(maxSum, sum);
			}
		}
		return maxSum;
	}

	/*
     * Approach 3: Dynamic Programming (DP Array)
     * - dp[i] = max subarray sum ending at index i.
     * - dp[i] = max(arr[i], arr[i] + dp[i-1]).
     * - Answer = max(dp[i]).
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
	public static int maxSubArrayDP(int[] arr) {
		int n = arr.length;
		int[] dp = new int[n];
		dp[0] = arr[0];
		int maxSum = dp[0];
		for (int i = 1; i < n; i++) {
			dp[i] = Math.max(arr[i], arr[i] + dp[i - 1]);
			maxSum = Math.max(maxSum, dp[i]);
		}
		return maxSum;
	}

	/*
     * Approach 4: Divide & Conquer
     * - Split array into two halves.
     * - Max subarray lies in left, right, or crossing mid.
     * - Recursively compute.
     * Time Complexity: O(n log n)
     * Space Complexity: O(log n) (recursion stack)
     */
	public static int maxSubArrayDivideConquer(int[] arr, int left, int right) {
		if (left == right) {
			return arr[left];
		}

		int mid = (left + right) / 2;
		int leftSum = maxSubArrayDivideConquer(arr, left, mid);
		int rightSum = maxSubArrayDivideConquer(arr, mid + 1, right);
		int crossSum = maxCrossingSum(arr, left, mid, right);
		return Math.max(Math.max(leftSum, rightSum), crossSum);
	}

	private static int maxCrossingSum(int[] arr, int left, int mid, int right) {
		int sum = 0, leftMax = Integer.MIN_VALUE, rightMax = Integer.MIN_VALUE;
		for (int i = mid; i >= left; i--) {
			sum += arr[i];
			leftMax = Math.max(leftMax, sum);
		}
		sum = 0;
		for (int i = mid + 1; i <= right; i++) {
			sum += arr[i];
			rightMax = Math.max(rightMax, sum);
		}
		return leftMax + rightMax;
	}
	
	/*
	 * Kadane's Algorithm:
	 * -------------------
	 * Problem: Find the maximum sum of any contiguous subarray.
	 *
	 * Logic:
	 * 1. Initialize two variables:
	 *    - maxSum: stores the maximum sum found so far.
	 *    - currentSum: stores the maximum sum ending at the current index.
	 * 2. Traverse the array:
	 *    - At each element arr[i], decide whether to:
	 *        a) Start a new subarray from arr[i], OR
	 *        b) Extend the previous subarray by adding arr[i].
	 *      → currentSum = max(arr[i], currentSum + arr[i])
	 *    - Update maxSum if currentSum is greater.
	 * 3. At the end, maxSum contains the maximum subarray sum.
	 *
	 * Complexity:
	 * - Time: O(n) → single pass through the array.
	 * - Space: O(1) → constant extra memory.
	 *
	 * Key Point:
	 * - Kadane’s Algorithm is optimal and widely used in interviews.
	 */
	public static int maxSubArrayKadane(int[] arr) {
	    int maxSum = arr[0];        // Initialize with first element
	    int currentSum = arr[0];    // Current subarray sum
	    for (int i = 1; i < arr.length; i++) {
	        // Either start new subarray at arr[i] or extend previous one
	        currentSum = Math.max(arr[i], currentSum + arr[i]);
	        // Update global maximum if needed
	        maxSum = Math.max(maxSum, currentSum);
	    }
	    return maxSum; // Final answer
	}


	public static void main(String[] args) {
		int[] arr = { -2, 1, -3, 4, -1, 2, 1, -5, 4 };
		System.out.println("================================================");
		System.out.println("Brute Force: " + maxSubArrayBruteForce(arr));
		System.out.println("================================================");
		System.out.println("Kadane's Algorithm: " + maxSubArrayKadane(arr));
		System.out.println("================================================");
		System.out.println("DP Array: " + maxSubArrayDP(arr));
		System.out.println("================================================");
		System.out.println("Divide & Conquer: " + maxSubArrayDivideConquer(arr, 0, arr.length - 1));
		System.out.println("================================================");
	}
}
