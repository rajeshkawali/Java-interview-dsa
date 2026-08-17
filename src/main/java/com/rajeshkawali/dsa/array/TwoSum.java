package com.rajeshkawali.dsa.array;

import java.util.*;
/*
You are given an array of integers and a target sum.
The task is to find two numbers in the array whose sum equals the target.
Typically, you return their indices or the values.
*/
public class TwoSum {

    public static void main(String[] args) {
        int[] arr = {2, 3, 5, 4, 5, 3, 2};
        int target = 7;

        System.out.println("Brute Force:");
        System.out.println(Arrays.toString(findTwoSumBruteForce(arr, target)));
        System.out.println("================================================");

        System.out.println("HashMap Approach:");
        System.out.println(Arrays.toString(findTwoSumHashMap(arr, target)));
        System.out.println("================================================");

        System.out.println("Sorting + Two Pointer:");
        System.out.println(Arrays.toString(findTwoSumSorting(arr.clone(), target)));
        System.out.println("================================================");
    }

    /*
     * Approach 1: Brute Force
     * - Check all pairs (i, j).
     * - Return indices of first pair found.
     * Time: O(n^2), Space: O(1)
     */
    public static int[] findTwoSumBruteForce(int[] arr, int target) {
        int len = arr.length;
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                if (arr[i] + arr[j] == target) {
                    return new int[]{i, j}; // return indices
                }
            }
        }
        return new int[]{-1, -1}; // not found
    }

    /*
     * Approach 2: HashMap
     * - Store elements in map while traversing.
     * - For each element, check if target - element exists.
     * - Return indices when found.
     * Time: O(n), Space: O(n)
     */
    public static int[] findTwoSumHashMap(int[] arr, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            int complement = target - arr[i];
            if (map.containsKey(complement)) {
                return new int[]{map.get(complement), i};
            }
            map.put(arr[i], i);
        }
        return new int[]{-1, -1};
    }

    /*
     * Approach 3: Sorting + Two Pointer
     * - Sort array with original indices tracked.
     * - Use two pointers (low, high).
     * - If sum == target, return indices.
     * Time: O(n log n), Space: O(n) (for index tracking)
     */
    public static int[] findTwoSumSorting(int[] arr, int target) {
        int n = arr.length;
        int[][] nums = new int[n][2];
        for (int i = 0; i < n; i++) {
            nums[i][0] = arr[i]; // value
            nums[i][1] = i;      // original index
        }

        Arrays.sort(nums, Comparator.comparingInt(a -> a[0]));

        int left = 0, right = n - 1;
        while (left < right) {
            int sum = nums[left][0] + nums[right][0];
            if (sum == target) {
                return new int[]{nums[left][1], nums[right][1]};
            } else if (sum < target) {
                left++;
            } else {
                right--;
            }
        }
        return new int[]{-1, -1};
    }
}
